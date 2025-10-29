package engine.story.blocks;

import engine.story.Option;
import java.util.ArrayList;
import java.util.List;

public class Choice extends Block {
    private final List<Option> options = new ArrayList<>();

    public Choice(String content) {
        super(content);
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public List<Option> getOptions() {
        return options;
    }

    public boolean hasOptions() {
        return !options.isEmpty();
    }

    @Override
    public void display() {
        if (content != null && !content.isEmpty()) {
            System.out.println(content);
        }
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i).getDescription());
        }
    }
}
