package engine.story.controller;

import engine.story.blocks.Block;
import engine.story.model.Scene;

public class SceneController {

    private final StoryManager storyManager;

    public SceneController(StoryManager storyManager) {
        this.storyManager = storyManager;
    }


    public Block getCurrentBlock() {
        return storyManager.getCurrentBlock();
    }

    public boolean nextBlock() {
        return storyManager.nextBlock();
    }

    public int getCurrentBlockIndex() {
        return storyManager.getCurrentBlockIndex();
    }

    public void setBlockIndex(int index) {
        storyManager.setPosition(
                storyManager.getCurrentDayIndex(),
                storyManager.getCurrentSectionIndex(),
                storyManager.getCurrentSceneIndex(),
                index
        );
    }


    public Scene getCurrentScene() {
        return storyManager.getCurrentScene();
    }

    public boolean isSceneEnded() {
        Scene scene = storyManager.getCurrentScene();
        if (scene == null) return true;

        int lastIndex = scene.getBlockCount() - 1;
        return storyManager.getCurrentBlockIndex() >= lastIndex;
    }

    public void goToScene(String sceneId) {
        storyManager.goToScene(sceneId);
    }

    public void resetBlockIndex() {
        storyManager.setPosition(
                storyManager.getCurrentDayIndex(),
                storyManager.getCurrentSectionIndex(),
                storyManager.getCurrentSceneIndex(),
                0
        );
    }
}
