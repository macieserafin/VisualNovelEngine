package engine.story.model;

import java.util.ArrayList;
import java.util.List;

public class Section {

    private final int index; // np. 0 = section1
    private final List<Scene> scenes = new ArrayList<>();

    public Section(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public Scene getScene(int sceneIndex) {
        if (sceneIndex < 0 || sceneIndex >= scenes.size()) {
            return null;
        }
        return scenes.get(sceneIndex);
    }

    public int getSceneCount() {
        return scenes.size();
    }
}
