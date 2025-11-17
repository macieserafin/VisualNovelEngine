package engine.core;

import engine.character.CharacterManager;
import engine.input.InputHandler;
import engine.story.blocks.Option;
import engine.story.controller.SceneController;
import engine.story.controller.StoryManager;
import engine.story.blocks.Action;
import engine.story.blocks.Block;
import engine.story.blocks.Choice;
import engine.story.jump.JumpTarget;
import engine.ui.ConsoleWindow;
import engine.ui.RenderManager;
import game.StoryInitializer;

import java.awt.*;

public class GameManager {

    private boolean running = true;
    private GameState gameState = GameState.PLAYING;

    private final ConsoleWindow console = new ConsoleWindow();

    private final StoryManager storyManager = new StoryManager();
    private SceneController sceneController;
    private RenderManager renderer;

    private CharacterManager characterManager = new CharacterManager();

    private Option chosenOption = null;
    private String pausedSceneId = null;
    private int pausedBlockIndex = -1;

    public void initialize() {

        console.setToolbarListener(new ConsoleWindow.ToolbarListener() {
            @Override public void onSave() { /* TODO */ }

            @Override public void onLoad() { /* TODO */ }

            @Override public void onSettings() { requestSettings(); }

            @Override public void onMenu() { requestMenu(); }

            @Override public void onExit() { onExitRequested(); }
        });

        InputHandler.initialize(console);

        console.showToolbar(false);
        stepMainMenu();
    }

    public void playStep() {
        switch (gameState) {
            case MAIN_MENU -> stepMainMenu();
            case SETTINGS  -> stepSettings();
            case CREATOR   -> stepCreator();
            case PLAYING   -> stepPlaying();
            case EXITING   -> running = false;
        }
    }

    private void stepMainMenu() {
        console.clear();
        console.println("=== VISUAL NOVEL ENGINE ===");
        console.println("1. Start Game");
        console.println("2. Exit");

        int choice = InputHandler.getChoice(1, 2);

        if (choice == 1) startGame();
        else onExitRequested();
    }

    private void stepSettings() {
        console.clear();
        console.println("=== SETTINGS ===");
        console.println("[1] Back");

        InputHandler.getChoice(1, 1);
        requestContinue();
    }

    private void stepCreator() {
        console.clear();
        console.println("=== What's your name? ===");

        String input = InputHandler.getString();
        createCharacter(input);
        requestContinue();
    }

    private void stepPlaying() {

        Block currentBlock = sceneController.getCurrentBlock();
        if (currentBlock == null) {
            console.printlnColored("No active scene — end of the game.", new Color(0, 255, 0));
            exit();
            return;
        }

        if (currentBlock instanceof Action action) {
            handleAction(action);
            sceneController.nextBlock();     // NOWA METODA
            return;
        }

        if (currentBlock instanceof Choice choice && choice.hasOptions()) {
            renderer.render(currentBlock);

            int choiceIndex = InputHandler.getChoice(1, choice.getOptions().size());
            chosenOption = choice.getOptions().get(choiceIndex - 1);

            applyChoice();
            return;
        }

        renderer.render(currentBlock);
        console.showPrompt("[Press Enter to continue...]");
        console.waitForEnter();
        console.hidePrompt();

        boolean moved = sceneController.nextBlock();     // ZWRACA false jeśli koniec sceny

        if (!moved) handleEndOfScene();
    }

    private void applyChoice() {
        sceneController.goToScene(chosenOption.getNextSceneId());
        chosenOption = null;
    }

    private void handleEndOfScene() {
        if (sceneController.isSceneEnded()) {
            console.printlnColored("[End of scene]", new Color(0, 255, 0));
            requestMenu();
        }
    }

    public void createCharacter(String name) {
        characterManager.createCharacter(name);
    }

    public void handleAction(Action action) {
        String name = action.getAction();

        switch (name) {
            case "open_creator" -> requestCreator();
            case "jump_scene" -> {
                String id = action.getParams().get("id");
                sceneController.goToScene(id);
            }
            case "jump" -> {
                JumpTarget target = action.getTarget();
                sceneController.goTo(target);
            }
            default -> System.out.println("Unknown action: " + name);
        }
    }

    public void startGame() {
        console.clear();
        console.showToolbar(true);
        console.printlnColored("[Starting new game...]\n", new Color(0, 255, 0));

        StoryInitializer.setup(storyManager);
        sceneController = new SceneController(storyManager);
        renderer = new RenderManager(console);

        gameState = GameState.PLAYING;
        running = true;
    }

    public void requestSettings() {
        pausedSceneId = sceneController.getCurrentScene().getId();
        pausedBlockIndex = sceneController.getCurrentBlockIndex();

        console.skipWaiting();
        gameState = GameState.SETTINGS;
        console.showToolbar(false);
    }

    public void requestCreator() {
        gameState = GameState.CREATOR;
        console.showToolbar(true);
        console.clear();
    }

    public void requestMenu() {
        console.skipWaiting();
        gameState = GameState.MAIN_MENU;
        console.showToolbar(false);
    }

    public void requestContinue() {
        if (pausedSceneId != null) {
            sceneController.goToScene(pausedSceneId);
            sceneController.setBlockIndex(pausedBlockIndex);
        }

        pausedSceneId = null;
        pausedBlockIndex = -1;

        console.clearInputQueue();
        console.clear();

        gameState = GameState.PLAYING;
        console.showToolbar(true);
    }

    public void onExitRequested() {
        console.println("");
        console.printlnColored("[Exiting game...]", new Color(0, 255, 0));
        shutdown();
        System.exit(0);
    }

    private void exit() {
        running = false;
        console.println("");
        console.printlnColored("Press Enter to close...", new Color(0, 255, 0));
        requestMenu();
    }

    public void shutdown() {
        console.println("");
        console.printlnColored("Shutting down...", new Color(0, 255, 0));
    }

    public boolean isRunning() {
        return running;
    }
}
