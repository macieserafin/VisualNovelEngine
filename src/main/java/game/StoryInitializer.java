package game;

import engine.story.model.Story;
import engine.story.controller.StoryManager;
import game.stories.Test;

public class StoryInitializer {

    public static void setup(StoryManager storyManager) {

        // Story -> Day -> Section -> Scene -> Block
        Story story = Test.createStory();
        storyManager.setStory(story);

        // storyManager.goToScene(Test.getStartSceneId());
    }
}
