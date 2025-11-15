package engine.story.model;

import java.util.ArrayList;
import java.util.List;

public class Day {

    private final int index; // np. 0 = day1
    private final List<Section> sections = new ArrayList<>();

    public Day(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public List<Section> getSections() {
        return sections;
    }

    public Section getSection(int sectionIndex) {
        if (sectionIndex < 0 || sectionIndex >= sections.size()) {
            return null;
        }
        return sections.get(sectionIndex);
    }

    public int getSectionCount() {
        return sections.size();
    }
}
