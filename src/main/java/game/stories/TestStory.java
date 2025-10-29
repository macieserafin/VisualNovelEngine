package game.stories;

import engine.story.*;
import engine.story.blocks.*;

import java.util.HashMap;
import java.util.Map;

public class TestStory {

    public static Map<String, Scene> createScenes() {
        Map<String, Scene> scenes = new HashMap<>();

        // === SCENE: morning ===
        Scene morning = new Scene("morning")
                .addBlock(new Narrative("The sun rises over the quiet city. You wake up to the sound of your alarm clock."))
                .addBlock(new Dialogue("You", "Ugh... another day. Should I get up or stay a bit longer?"));

        Choice choiceMorning = new Choice("What do you do?");
        choiceMorning.addOption(new Option("Get up and start the day", "kitchen"));
        choiceMorning.addOption(new Option("Stay in bed for a while", "overslept"));
        morning.addBlock(choiceMorning);
        scenes.put(morning.getId(), morning);

        // === SCENE: kitchen ===
        Scene kitchen = new Scene("kitchen")
                .addBlock(new Narrative("You walk to the kitchen. The smell of coffee fills the air."))
                .addBlock(new Dialogue("You", "Alright, breakfast time."))
                .addBlock(new Monologue("I really need to get to work on time today..."));

        Choice choiceKitchen = new Choice("What do you want for breakfast?");
        choiceKitchen.addOption(new Option("Make coffee and toast", "bus_stop"));
        choiceKitchen.addOption(new Option("Just grab a banana and run", "bus_stop"));
        kitchen.addBlock(choiceKitchen);
        scenes.put(kitchen.getId(), kitchen);

        // === SCENE: overslept ===
        Scene overslept = new Scene("overslept")
                .addBlock(new Narrative("You close your eyes again. The minutes pass quickly..."))
                .addBlock(new Dialogue("You", "Just five more minutes..."))
                .addBlock(new Narrative("...An hour later, you wake up in panic."))
                .addBlock(new Monologue("Shit! I’m late again!"));
        overslept.setEndingScene(true);
        scenes.put(overslept.getId(), overslept);

        // === SCENE: bus_stop ===
        Scene busStop = new Scene("bus_stop")
                .addBlock(new Narrative("You reach the bus stop just in time. The cold morning air bites your face."))
                .addBlock(new Dialogue("Old Lady", "Good morning, young man! Always rushing, huh?"))
                .addBlock(new Dialogue("You", "Yeah, that’s life..."))
                .addBlock(new Monologue("She’s right. Maybe I should slow down a bit."));

        Choice choiceBus = new Choice("You see two buses arriving. Which one do you take?");
        choiceBus.addOption(new Option("Take the usual one to work", "office"));
        choiceBus.addOption(new Option("Hop on the mysterious empty bus", "mystery_bus"));
        busStop.addBlock(choiceBus);
        scenes.put(busStop.getId(), busStop);

        // === SCENE: office ===
        Scene office = new Scene("office")
                .addBlock(new Narrative("You arrive at the office, just in time."))
                .addBlock(new Dialogue("Boss", "Morning! You’re on time for once."))
                .addBlock(new Dialogue("You", "Trying to change that habit."))
                .addBlock(new Monologue("Another day, another battle."));
        office.setEndingScene(true);
        scenes.put(office.getId(), office);

        // === SCENE: mystery_bus ===
        Scene mysteryBus = new Scene("mystery_bus")
                .addBlock(new Narrative("You sit down in the empty bus. The doors close silently."))
                .addBlock(new Dialogue("Driver", "No destination today. Only discovery."))
                .addBlock(new Monologue("What the hell does that mean...?"))
                .addBlock(new Narrative("The bus drives into the fog, and everything fades to white..."));
        mysteryBus.setEndingScene(true);
        scenes.put(mysteryBus.getId(), mysteryBus);

        return scenes;
    }

    public static String getStartScene() {
        return "morning";
    }
}