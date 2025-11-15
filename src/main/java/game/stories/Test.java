package game.stories;

import engine.story.blocks.*;
import engine.story.blocks.Option;
import engine.story.model.Day;
import engine.story.model.Scene;
import engine.story.model.Section;
import engine.story.model.Story;

public class Test {

    public static Story createStory() {

        Story story = new Story();

        // DAY 1
        Day day1 = new Day(0);


        // SECTION 1 — INTRODUCTION
        Section intro = new Section(0);

        // ---------- SCENE: start ----------
        Scene start = new Scene("start");
        start.addBlock(new Narrative("Welcome to the Visual Novel Engine test."));
        start.addBlock(new Narrative("This is the introductory scene."));

        start.addBlock(new Dialogue("SYSTEM", "You will now test dialogues, choices and actions."));
        start.addBlock(new Monologue("Hmm... I wonder what will happen next."));

        Choice choiceStart = new Choice("Where do you want to go?");
        choiceStart.addOption(new Option("Proceed to Scene A", "sceneA"));
        choiceStart.addOption(new Option("Try Creator Test", "creatorTest"));
        choiceStart.addOption(new Option("Go to Ending", "end"));

        start.addBlock(choiceStart);

        intro.addScene(start);

        // ---------- SCENE: creatorTest ----------
        Scene creatorTest = new Scene("creatorTest");
        creatorTest.addBlock(new Narrative("Before continuing, you can optionally create your character."));
        creatorTest.addBlock(new Action("open_creator"));
        creatorTest.addBlock(new Narrative("Character created successfully!"));

        Choice creatorChoice = new Choice("What next?");
        creatorChoice.addOption(new Option("Return to Start", "start"));
        creatorChoice.addOption(new Option("Continue to Ending", "end"));

        creatorTest.addBlock(creatorChoice);

        intro.addScene(creatorTest);

        day1.addSection(intro);


        // SECTION 2
        Section branch = new Section(1);

        // ---------- SCENE: sceneA ----------
        Scene sceneA = new Scene("sceneA");
        sceneA.addBlock(new Narrative("You are now in Scene A."));
        sceneA.addBlock(new Dialogue("NPC", "Scene A contains dialogue, narrative and choices."));
        sceneA.addBlock(new Monologue("I should think carefully about the next step..."));

        Choice choiceA = new Choice("What do you want to do next?");
        choiceA.addOption(new Option("Go to Scene B", "sceneB"));
        choiceA.addOption(new Option("Return to Start", "start"));
        choiceA.addOption(new Option("Jump to Ending", "end"));

        sceneA.addBlock(choiceA);

        branch.addScene(sceneA);

        // ---------- SCENE: sceneB ----------
        Scene sceneB = new Scene("sceneB");
        sceneB.addBlock(new Narrative("Welcome to Scene B."));
        sceneB.addBlock(new Narrative("This scene is simpler and demonstrates branching paths."));

        Choice choiceB = new Choice("Choose your next destination:");
        choiceB.addOption(new Option("Back to Scene A", "sceneA"));
        choiceB.addOption(new Option("Go to Ending", "end"));

        sceneB.addBlock(choiceB);

        branch.addScene(sceneB);

        day1.addSection(branch);


        // SECTION 3
        Section ending = new Section(2);

        // ---------- SCENE: end ----------
        Scene end = new Scene("end");
        end.addBlock(new Narrative("=== END OF TEST ==="));
        end.addBlock(new Narrative("Thanks for testing the engine."));
        end.setEndingScene(true);

        ending.addScene(end);
        day1.addSection(ending);


        // ADD DAY TO STORY
        story.addDay(day1);

        return story;
    }

    public static String getStartScene() {
        return "start";
    }
}
