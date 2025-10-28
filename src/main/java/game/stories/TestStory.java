package game.stories;

import engine.story.*;
import java.util.HashMap;
import java.util.Map;

public class TestStory {
    public static Map<String, Scene> createScenes(){
        Map<String, Scene> scenes = new HashMap<>();

        Scene intro = new Scene("intro", "Dzien 0: Budzisz sie w malym pokoju. Za oknem ciemno. ");
        intro.addOption(new Option("Wstan z lozka", "dead"));
        intro.addOption(new Option("Spij dalej", "alive"));

        Scene dead = new Scene("dead", "Za okna wyskoczyl demon i cie zabil");

        Scene alive = new Scene("alive", "Poszedles dalej spac i przezyles noc");

        scenes.put("intro", intro);
        scenes.put("dead", dead);
        scenes.put("alive", alive);

        return scenes;
    }
    public static String getStartScene(){
        return "intro";
    }
}
