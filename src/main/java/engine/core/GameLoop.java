package engine.core;

import engine.input.InputHandler;
import engine.story.Option;
import engine.story.Scene;
import engine.story.StoryManager;
import engine.story.blocks.Block;
import engine.story.blocks.Choice;
import engine.ui.ConsoleWindow;
import engine.ui.RenderManager;
import game.stories.TestStory;

public class GameLoop {
    private boolean running = true;

    private final StoryManager storyManager = new StoryManager();
    private final ConsoleWindow console = new ConsoleWindow("Visual Novel Engine", 800, 600);
    private final RenderManager renderer = new RenderManager(console);


    private Scene currentScene;
    private int currentBlockIndex = 0;
    private Option chosenOption = null;

    public void start() {
        System.out.println("GameLoop started!");

        InputHandler.initialize(console);

        storyManager.addScene(TestStory.createScenes());
        storyManager.setActiveScene(TestStory.getStartScene());
        currentScene = storyManager.getActiveScene();


        while (running) {
            render();
            input();
            update();
        }
    }


    private void render() {
        if (currentScene == null) {
            console.println("No active scene — end of the game.");
            exit();
            return;
        }

        Block currentBlock = getCurrentBlock();
        if (currentBlock != null) {
            renderer.render(currentBlock);
        }
    }


    private void input() {
        Block currentBlock = getCurrentBlock();

        if (currentBlock instanceof Choice) {
            Choice choice = (Choice) currentBlock;

            if (choice.hasOptions()) {
                int choiceIndex = InputHandler.getChoice(1, choice.getOptions().size());
                chosenOption = choice.getOptions().get(choiceIndex - 1);
            }
        } else {

        }
    }

    private void update() {
        Block currentBlock = getCurrentBlock();


        if (chosenOption != null) {
            storyManager.setActiveScene(chosenOption.getNextSceneId());
            currentScene = storyManager.getActiveScene();
            currentBlockIndex = 0;
            chosenOption = null;
            return;
        }


        if (currentBlockIndex + 1 >= currentScene.getBlocks().size()) {
            if (currentScene.isEndingScene()) {
                console.println("[End of scene / day]");
                exit();
            } else {
                exit();
            }
        } else {

            currentBlockIndex++;
        }
    }

    private Block getCurrentBlock() {
        if (currentScene == null) return null;
        if (currentScene.getBlocks().isEmpty()) return null;
        if (currentBlockIndex < 0 || currentBlockIndex >= currentScene.getBlocks().size()) return null;
        return currentScene.getBlocks().get(currentBlockIndex);
    }



    private void exit() {
        running = false;
        console.println("Press Enter to close...");

        console.readLine();

        System.exit(0);
    }

}
