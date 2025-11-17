package engine.story.blocks;

import engine.story.jump.JumpTarget;

import java.util.Map;

public class Action extends Block {

    private final String action;
    private final Map<String, String> params;
    private final JumpTarget target;

    public Action(String action) {
        super("action");
        this.action = action;
        this.params = null;
        this.target = null;
    }

    public Action(String action, Map<String, String> params) {
        super("action");
        this.action = action;
        this.params = params;
        this.target = null;
    }

    public Action(JumpTarget target) {
        super("action");
        this.action = "jump";
        this.params = null;
        this.target = target;
    }

    public String getAction() {
        return action;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public JumpTarget getTarget() {
        return target;
    }

    @Override
    public void display() {
        // Action typically does not render any text
    }
}
