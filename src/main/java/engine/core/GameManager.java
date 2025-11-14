package engine.core;

import engine.character.CharacterManager;
import engine.input.InputHandler;
import engine.story.Option;
import engine.story.SceneController;
import engine.story.StoryManager;
import engine.story.blocks.Action;
import engine.story.blocks.Block;
import engine.story.blocks.Choice;
import engine.ui.ConsoleWindow;
import engine.ui.MenuRenderer;
import engine.ui.RenderManager;
import engine.ui.SettingsRenderer;
import game.StoryInitializer;

import java.awt.*;

public class GameManager {
    private boolean running = true;
    private GameState gameState = GameState.PLAYING;

    private final ConsoleWindow console = new ConsoleWindow();

    private final StoryManager storyManager = new StoryManager();
    CharacterManager characterManager = new CharacterManager();

    private RenderManager renderer;
    private SceneController sceneController;

    MenuRenderer menuRenderer = new MenuRenderer(console, this);
    SettingsRenderer settingsRenderer = new SettingsRenderer(console, this);

    private Option chosenOption = null;

    public void initialize() {

        console.setToolbarListener(new ConsoleWindow.ToolbarListener() {
            @Override public void onSave() { /* TODO */ }

            @Override public void onLoad() { /* TODO */ }

            @Override public void onSettings() { requestSettings(); }

            @Override public void onMenu() { requestMenu(); }

            @Override public void onExit() {onExitRequested();}
        });


        InputHandler.initialize(console);

        console.showToolbar(false);
        menuRenderer.showMainMenu();
    }

    public void playStep() {
        Block currentBlock = sceneController.getCurrentBlock();

        if (currentBlock == null) {
            console.println("");
            console.printlnColored("No active scene — end of the game.", new Color(0, 255, 0));
            exit();
            return;
        }

        if (currentBlock instanceof Action action) {
            handleAction(action);
            sceneController.advanceBlock();
            return;
        }

        if (currentBlock instanceof Choice choice && choice.hasOptions()) {
            renderer.render(currentBlock);
            int choiceIndex = InputHandler.getChoice(1, choice.getOptions().size());
            chosenOption = choice.getOptions().get(choiceIndex - 1);
            return;
        }

        renderer.render(currentBlock);
        console.showPrompt("[Press Enter to continue...]");
        console.waitForEnter();
        console.hidePrompt();

        sceneController.advanceBlock();
    }

    public void update() {

        if (chosenOption != null) {
            sceneController.setScene(chosenOption.getNextSceneId());
            chosenOption = null;
            return;
        }

        if (sceneController.isSceneEnded()) {
            console.println("");
            console.printlnColored("[End of scene / day]", new Color(0, 255, 0));

            if (sceneController.getCurrentBlock() == null) {
                console.waitForEnter();
                exit();
            }
            return;
        }

    }

    public void createCharacter(String name) {
        characterManager.createCharacter(name);
    }

    public void handleAction(Action action) {
        String name = action.getAction();

        switch (name) {
            case "open_creator" -> requestCreator();
            case "jump_scene" -> sceneController.setScene(action.getParams().get("id"));
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


    public void requestSettings(){
        console.skipWaiting();
        gameState = GameState.SETTINGS;
        console.showToolbar(false);
    }

    public void requestCreator(){
        gameState = GameState.CREATOR;
        console.showToolbar(true);
    }

    public void requestMenu() {
        console.skipWaiting();
        gameState = GameState.MAIN_MENU;
        console.showToolbar(false);

    }

    public void requestContinue() {
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

    public GameState getState() {
        return gameState;
    }

    public ConsoleWindow getConsole() {
        return console;
    }

    public boolean isRunning() {
        return running;
    }
}
