package engine.story;

import java.util.HashMap;
import java.util.Map;

public class StoryManager {
    private Map<String, Scene> scenes = new HashMap<>();
    private Scene activeScene;

    public void addScene(Map<String, Scene> sceneMap) {
        scenes.putAll(sceneMap);
    }

    public void setActiveScene(String id){
        activeScene = scenes.get(id);
    }

    public Scene getActiveScene(){
        return activeScene;
    }

    public void performChoice(int choice) {
        if (activeScene == null) { return; }

        if (choice < 1 || choice > activeScene.getOptions().size()) {
            System.out.println("Invalid choice");
            return;
        }

        Option selected = activeScene.getOptions().get(choice - 1);
        String nextSceneId = selected.getNextSceneId();
        activeScene = scenes.get(nextSceneId);

        if(activeScene == null){
            System.out.println("No scene with id " + nextSceneId);
        }

    }
}
