package game.stories;

import engine.story.*;
import engine.story.blocks.*;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static Map<String, Scene> createScenes() {
        Map<String, Scene> scenes = new HashMap<>();

        // === SCENE: start ===
        Scene start = new Scene("start")
                .addBlock(new Narrative("TEST: Narrative block"))
                .addBlock(new Dialogue("SYSTEM", "TEST: Dialogue block"))
                .addBlock(new Monologue("TEST: Monologue block"));

        Choice choiceStart = new Choice("TEST: Choice block");
        choiceStart.addOption(new Option("Go to scene A", "sceneA"));
        choiceStart.addOption(new Option("Go to Creator Test", "creatorTest"));   // <-- NOWE
        choiceStart.addOption(new Option("Go to ending", "end"));
        start.addBlock(choiceStart);
        scenes.put(start.getId(), start);

        // === SCENE: sceneA ===
        Scene sceneA = new Scene("sceneA")
                .addBlock(new Narrative("SCENE A - narrative"))
                .addBlock(new Dialogue("NPC", "SCENE A - dialogue"))
                .addBlock(new Monologue("SCENE A - monologue"));

        Choice choiceA = new Choice("Choose next test scene");
        choiceA.addOption(new Option("Go to scene B", "sceneB"));
        choiceA.addOption(new Option("Return to start", "start"));
        choiceA.addOption(new Option("Go to ending", "end"));
        sceneA.addBlock(choiceA);
        scenes.put(sceneA.getId(), sceneA);

        // === SCENE: sceneB ===
        Scene sceneB = new Scene("sceneB")
                .addBlock(new Narrative("SCENE B - only narrative for simplicity"));

        Choice choiceB = new Choice("Next step?");
        choiceB.addOption(new Option("Back to scene A", "sceneA"));
        choiceB.addOption(new Option("Go to ending", "end"));
        sceneB.addBlock(choiceB);
        scenes.put(sceneB.getId(), sceneB);

        // ================================
        // === SCENE: creatorTest
        // ================================
        Scene creatorTest = new Scene("creatorTest")
                .addBlock(new Narrative("CREATOR TEST: Soon you will create your character."))
                .addBlock(new Action("open_creator"))
                .addBlock(new Narrative("CREATOR TEST: Character created successfully!"));

        Choice creatorChoice = new Choice("Where now?");
        creatorChoice.addOption(new Option("Back to start", "start"));
        creatorChoice.addOption(new Option("Go to ending", "end"));
        creatorTest.addBlock(creatorChoice);

        scenes.put(creatorTest.getId(), creatorTest);

        // === SCENE: end ===
        Scene end = new Scene("end")
                .addBlock(new Narrative("END SCENE - test ending"));
        end.setEndingScene(true);
        scenes.put(end.getId(), end);

        return scenes;
    }

    public static String getStartScene() {
        return "start";
    }
}
