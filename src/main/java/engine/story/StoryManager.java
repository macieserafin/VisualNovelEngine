package engine.story;

import java.util.HashMap;
import java.util.Map;

public class StoryManager {

    private Map<String, Scene> scenes;
    private Scene activeScene;

    public void addScene(Map<String, Scene> scenes) {
        this.scenes = scenes;
    }

    public void setActiveScene(String sceneId) {
        if (scenes == null) return;
        this.activeScene = scenes.get(sceneId);
    }

    public Scene getActiveScene() {
        return activeScene;
    }
}
