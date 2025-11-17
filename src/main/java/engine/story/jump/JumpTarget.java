package engine.story.jump;

public class JumpTarget {

    public final int day;
    public final int section;
    public final int scene;
    public final int block;

    public JumpTarget(int day, int section, int scene, int block) {
        this.day = day;
        this.section = section;
        this.scene = scene;
        this.block = block;
    }

    public boolean isJumpToDay() { return day >= 0 && section < 0; }
    public boolean isJumpToSection() { return day >= 0 && section >= 0 && scene < 0; }
    public boolean isJumpToScene() { return day >= 0 && section >= 0 && scene >= 0 && block < 0; }
    public boolean isJumpToBlock() { return day >= 0 && section >= 0 && scene >= 0 && block >= 0; }
}
