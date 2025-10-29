package engine.story.blocks;

public class Monologue extends Block {

    public Monologue(String content) {
        super(content);
    }
    @Override
    public void display() {
        System.out.println("~* " + content + " *~");
    }
}
