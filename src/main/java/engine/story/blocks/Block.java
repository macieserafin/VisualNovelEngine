package engine.story.blocks;

public abstract class Block {
    protected String content;

    public Block(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public abstract void display();
}
