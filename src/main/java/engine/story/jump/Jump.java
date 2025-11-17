package engine.story.jump;

public class Jump {

    public static JumpTarget toDay(int day) {
        return new JumpTarget(day, -1, -1, -1);
    }

    public static JumpTarget toSection(int day, int section) {
        return new JumpTarget(day, section, -1, -1);
    }

    public static JumpTarget toScene(int day, int section, int scene) {
        return new JumpTarget(day, section, scene, -1);
    }

    public static JumpTarget toBlock(int day, int section, int scene, int block) {
        return new JumpTarget(day, section, scene, block);
    }
}
