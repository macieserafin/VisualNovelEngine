package engine.story.model;

import java.util.ArrayList;
import java.util.List;

public class Story {

    private final List<Day> days = new ArrayList<>();

    public void addDay(Day day) {
        days.add(day);
    }

    public List<Day> getDays() {
        return days;
    }

    public Day getDay(int index) {
        if (index < 0 || index >= days.size()) {
            return null;
        }
        return days.get(index);
    }

    public int getDayCount() {
        return days.size();
    }
}
