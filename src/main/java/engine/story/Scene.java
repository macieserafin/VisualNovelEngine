package engine.story;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private String id; //(np. "hotel_room_1")
    private String description; //in game
    private List<Option> options; //lista wyborów
    private boolean isEndingScene = false;

    public Scene(String id, String description) {
        this.id = id;
        this.description = description;
        this.options = new ArrayList<Option>();
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public void display() {
        System.out.println("\nScene [" + id + "]");
        System.out.println(description);
        System.out.println();
        for (Option option : options) {
            System.out.println(option);
        }
    }


    public boolean isEndingScene() {
        return isEndingScene;
    }

    public void setEndingScene(boolean endingScene) {
        this.isEndingScene = endingScene;
    }
}
