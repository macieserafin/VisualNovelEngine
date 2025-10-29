package engine.story.blocks;

public class Narrative extends Block {
    public Narrative(String content) {
        super(content);
    }

    @Override
    public void display() {
        System.out.println(content);
    }
}
