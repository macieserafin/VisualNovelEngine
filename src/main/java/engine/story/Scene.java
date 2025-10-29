package engine.story;

import engine.story.blocks.Block;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    private final String id;
    private final List<Block> blocks = new ArrayList<>();
    private boolean endingScene;

    public Scene(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Scene addBlock(Block block) {
        blocks.add(block);
        return this;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public boolean isEndingScene() {
        return endingScene;
    }

    public void setEndingScene(boolean endingScene) {
        this.endingScene = endingScene;
    }
}
