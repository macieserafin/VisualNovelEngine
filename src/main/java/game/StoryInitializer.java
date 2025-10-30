package game;

import engine.story.StoryManager;
import engine.story.Scene;
import game.stories.TestStory;

public class StoryInitializer {
    public static void setup(StoryManager storyManager) {
        storyManager.addScene(TestStory.createScenes());
        storyManager.setActiveScene(TestStory.getStartScene());
    }

    public boolean isEndScene(Scene scene) {
        return scene.isEndingScene();
    }
}