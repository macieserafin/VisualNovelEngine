package game.stories;

import engine.story.*;
import engine.story.blocks.*;

import java.util.HashMap;
import java.util.Map;

public class TestStory {

    public static Map<String, Scene> createScenes() {
        Map<String, Scene> scenes = new HashMap<>();

        // SCENE: intro
        Scene intro = new Scene("intro")
                .addBlock(new Narrative("The sun rises over the city. A new day begins..."))
                .addBlock(new Dialogue("Anna", "Get up, you overslept for work!"))
                .addBlock(new Monologue("Damn it... Monday again."));

        Choice choiceIntro = new Choice("What do you want to do?");
        choiceIntro.addOption(new Option("Get out of bed", "scene2"));
        choiceIntro.addOption(new Option("Stay a bit longer", "scene3"));
        intro.addBlock(choiceIntro);

        scenes.put(intro.getId(), intro);

        // SCENE: scene2 (ending)
        Scene scene2 = new Scene("scene2")
                .addBlock(new Narrative("You got up, got ready, and left the house."));
        scene2.setEndingScene(true);
        scenes.put(scene2.getId(), scene2);

        // SCENE: scene3 (ending)
        Scene scene3 = new Scene("scene3")
                .addBlock(new Narrative("You stayed a bit longer. The day started lazily."));
        scene3.setEndingScene(true);
        scenes.put(scene3.getId(), scene3);

        return scenes;

    }

    public static String getStartScene() {
        return "intro";
    }
}