package engine.story.controller;

import engine.story.blocks.Block;
import engine.story.model.Day;
import engine.story.model.Scene;
import engine.story.model.Section;
import engine.story.model.Story;

import java.util.HashMap;
import java.util.Map;

public class StoryManager {

    private Story story;

    private final Map<String, Scene> sceneIndex = new HashMap<>();

    private int currentDayIndex = 0;
    private int currentSectionIndex = 0;
    private int currentSceneIndex = 0;
    private int currentBlockIndex = 0;

    public void setStory(Story story) {
        this.story = story;
        rebuildSceneIndex();
        resetPosition();
    }

    private void rebuildSceneIndex() {
        sceneIndex.clear();
        if (story == null) return;

        for (Day day : story.getDays()) {
            if (day == null) continue;
            for (Section section : day.getSections()) {
                if (section == null) continue;
                for (Scene scene : section.getScenes()) {
                    if (scene == null) continue;
                    String id = scene.getId();
                    if (id != null && !id.isEmpty()) {
                        sceneIndex.put(id, scene);
                    }
                }
            }
        }
    }

    private void resetPosition() {
        currentDayIndex = 0;
        currentSectionIndex = 0;
        currentSceneIndex = 0;
        currentBlockIndex = 0;
    }


    public Scene getCurrentScene() {
        Day day = story != null ? story.getDay(currentDayIndex) : null;
        if (day == null) return null;

        Section section = day.getSection(currentSectionIndex);
        if (section == null) return null;

        return section.getScene(currentSceneIndex);
    }

    public Block getCurrentBlock() {
        Scene scene = getCurrentScene();
        if (scene == null) return null;

        return scene.getBlock(currentBlockIndex);
    }

    public boolean nextBlock() {
        Scene scene = getCurrentScene();
        if (scene == null) return false;

        if (currentBlockIndex + 1 < scene.getBlockCount()) {
            currentBlockIndex++;
            return true;
        }
        return false;
    }



    public void goToScene(String sceneId) {
        Scene target = sceneIndex.get(sceneId);
        if (target == null || story == null) {
            return;
        }

        for (int d = 0; d < story.getDayCount(); d++) {
            Day day = story.getDay(d);
            if (day == null) continue;

            for (int s = 0; s < day.getSectionCount(); s++) {
                Section section = day.getSection(s);
                if (section == null) continue;

                for (int sc = 0; sc < section.getSceneCount(); sc++) {
                    Scene scene = section.getScene(sc);
                    if (scene == target) {
                        currentDayIndex = d;
                        currentSectionIndex = s;
                        currentSceneIndex = sc;
                        currentBlockIndex = 0;
                        return;
                    }
                }
            }
        }
    }


    public void setPosition(int dayIndex, int sectionIndex, int sceneIndex, int blockIndex) {
        this.currentDayIndex = dayIndex;
        this.currentSectionIndex = sectionIndex;
        this.currentSceneIndex = sceneIndex;
        this.currentBlockIndex = blockIndex;
    }

    public int getCurrentDayIndex() {
        return currentDayIndex;
    }

    public int getCurrentSectionIndex() {
        return currentSectionIndex;
    }

    public int getCurrentSceneIndex() {
        return currentSceneIndex;
    }

    public int getCurrentBlockIndex() {
        return currentBlockIndex;
    }
}
