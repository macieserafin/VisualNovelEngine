package game.stories;

import engine.story.*;
import java.util.HashMap;
import java.util.Map;

public class TestStory {
    public static Map<String, Scene> createScenes(){
        Map<String, Scene> scenes = new HashMap<>();

        Scene intro = new Scene("intro", "Day 0: You wake up in a small room. It's dark outside.");
        intro.addOption(new Option("Get out of bed", "dead"));
        intro.addOption(new Option("Go back to sleep", "alive"));

        Scene dead = new Scene("dead", "A demon bursts through the window and kills you instantly.");
        dead.setEndingScene(true);

        Scene alive = new Scene("alive", "You went back to sleep and survived the night.");
        alive.setEndingScene(true);

        scenes.put("intro", intro);
        scenes.put("dead", dead);
        scenes.put("alive", alive);



        return scenes;
    }
    public static String getStartScene(){
        return "intro";
    }
}
