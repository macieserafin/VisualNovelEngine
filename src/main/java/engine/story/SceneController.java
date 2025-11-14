package engine.story;

import engine.story.blocks.Block;

public class SceneController {
    private final StoryManager storyManager;
    private Scene currentScene;
    private int currentBlockIndex = 0;


    public SceneController(StoryManager storyManager) {
        this.storyManager = storyManager;
        this.currentScene = storyManager.getActiveScene();
    }

    public void setScene(String sceneId) {
        storyManager.setActiveScene(sceneId);
        currentScene = storyManager.getActiveScene();
        currentBlockIndex = 0;
    }

    public Block getCurrentBlock() {
        if (currentScene == null) return null;
        if (currentScene.getBlocks().isEmpty()) return null;
        if (currentBlockIndex < 0 || currentBlockIndex >= currentScene.getBlocks().size()) return null;
        return currentScene.getBlocks().get(currentBlockIndex);
    }

    public void advanceBlock() {
        currentBlockIndex++;
    }

    public boolean isSceneEnded() {
        return currentScene != null && currentBlockIndex >= currentScene.getBlocks().size();
    }

    public int getCurrentBlockIndex() {
        return currentBlockIndex;
    }

    public void setBlockIndex(int index) {
        if (index < 0) index = 0;

        if (currentScene != null) {
            int max = currentScene.getBlocks().size() - 1;
            if (index > max) index = max;
        }

        this.currentBlockIndex = index;
    }

    public boolean isEndingScene() {
        return currentScene != null && currentScene.isEndingScene();
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void reset() {
        currentBlockIndex = 0;
    }
}
