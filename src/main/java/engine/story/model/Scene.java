package engine.story.model;

import engine.story.blocks.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scene {

    private final String id;
    private final List<Block> blocks = new ArrayList<>();
    private boolean endingScene;

    public Scene(String id) {
        this.id = id;
    }

    public Scene(String id, Block... blocks) {
        this.id = id;
        this.blocks.addAll(Arrays.asList(blocks));
    }

    public String getId() {
        return id;
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public Block getBlock(int index) {
        if (index < 0 || index >= blocks.size()) {
            return null;
        }
        return blocks.get(index);
    }

    public int getBlockCount() {
        return blocks.size();
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
