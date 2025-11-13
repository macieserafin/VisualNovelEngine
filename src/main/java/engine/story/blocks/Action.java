package engine.story.blocks;

import java.util.Map;

public class Action extends Block {

    private final String action;
    private final Map<String, String> params;

    public Action(String action) {
        super(action);
        this.action = action;
        this.params = null;
    }

    public Action(String action, Map<String, String> params) {
        super(action);
        this.action = action;
        this.params = params;
    }

    public String getAction() {
        return action;
    }

    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public void display() {

    }
}
