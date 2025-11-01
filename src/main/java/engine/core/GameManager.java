package engine.core;

import engine.input.InputHandler;
import engine.story.Option;
import engine.story.SceneController;
import engine.story.StoryManager;
import engine.story.blocks.Block;
import engine.story.blocks.Choice;
import engine.ui.ConsoleWindow;
import engine.ui.RenderManager;
import game.StoryInitializer;

import java.awt.*;

public class GameManager {
    private boolean running = true;

    private final StoryManager storyManager = new StoryManager();
    private final ConsoleWindow console = new ConsoleWindow("Visual Novel Engine", 1000, 1000);
    private final RenderManager renderer = new RenderManager(console);
    private SceneController sceneController;

    private Option chosenOption = null;

    public void initialize() {

        console.setToolbarListener(new ConsoleWindow.ToolbarListener() {
            @Override public void onSave() { /* TODO */ }

            @Override public void onLoad() { /* TODO */ }

            @Override public void onSettings() { /* TODO */ }

            @Override public void onMenu() { /* TODO */ }

            @Override public void onExit() {onExitRequested();}
        });

        InputHandler.initialize(console);
        StoryInitializer.setup(storyManager);
        sceneController = new SceneController(storyManager);
    }

    public void render() {
        Block currentBlock = sceneController.getCurrentBlock();
        if (currentBlock != null) {
            renderer.render(currentBlock);
        } else {
            console.println("");
            console.printlnColored("No active scene — end of the game.", new Color(0, 255, 0));
            exit();
        }
    }

    public void handleInput() {
        Block currentBlock = sceneController.getCurrentBlock();

        if (currentBlock instanceof Choice choice && choice.hasOptions()) {
            int choiceIndex = InputHandler.getChoice(1, choice.getOptions().size());
            chosenOption = choice.getOptions().get(choiceIndex - 1);
        } else {
            console.showPrompt("[Press Enter to continue...]");
            console.waitForEnter();
            console.hidePrompt();
        }
    }

    public void update() {
        if (chosenOption != null) {
            sceneController.setScene(chosenOption.getNextSceneId());
            chosenOption = null;
            return;
        }

        if (sceneController.isSceneEnded()) {
            if (sceneController.isEndingScene()) {
                console.println("");
                console.printlnColored("[End of scene / day]", new Color(0, 255, 0));
                exit();
            } else {
                exit();
            }
        } else {
            sceneController.advanceBlock();
        }
    }

    public boolean isRunning() {
        return running;
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
        console.readLine();
        shutdown();
        System.exit(0);
    }

    public void shutdown() {
        console.println("");
        console.printlnColored("Shutting down...", new Color(0, 255, 0));
    }
}
