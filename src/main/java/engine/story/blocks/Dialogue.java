package engine.story.blocks;

public class Dialogue extends Block {
    private String speaker;

    public Dialogue(String speaker, String content) {
        super(content);
        this.speaker = speaker;
    }


    @Override
    public void display() {
        System.out.println(speaker + ": " + content);
    }
}
