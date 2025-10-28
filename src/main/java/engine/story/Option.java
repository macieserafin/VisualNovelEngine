package engine.story;

public class Option {
    private String description; // in game
    private String nextSceneId;

    public Option(String description, String nextSceneId) {
        this.description = description;
        this.nextSceneId = nextSceneId;
    }

    public String getDescription() {
        return description;
    }

    public String getNextSceneId() {
        return nextSceneId;
    }

    @Override
    public String toString() {
        return description;
    }
}
