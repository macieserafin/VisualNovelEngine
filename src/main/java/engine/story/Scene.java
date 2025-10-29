package engine.story;

import engine.story.blocks.Block;

import java.util.ArrayList;
import java.util.List;

public class Scene extends Block {

    private String title;
    private List<Option> options = new ArrayList<>();

    private boolean isEndingScene = false;


    public Scene(String title, String text) {
        super(text);
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void addOption(Option option) {
        options.add(option);
    }


    public List<Option> getOptions() {
        return options;
    }


    @Override
    public void display() {
        System.out.println("\n== " + title + " ==");
        System.out.println(content);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i).getDescription());
        }
    }


    public boolean isEndingScene() {
        return isEndingScene;
    }

    public void setEndingScene(boolean endingScene) {
        this.isEndingScene = endingScene;
    }
}
