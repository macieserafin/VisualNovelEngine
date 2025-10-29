package engine.ui;

import engine.story.Option;
import engine.story.blocks.*;

import java.io.Console;

public class RenderManager {

    private final ConsoleWindow console;
    private static final int TYPE_SPEED_MS = 25;

    public RenderManager(ConsoleWindow console) {
        this.console = console;
    }

    public void render(Block block) {
        try {
            if (block instanceof Narrative n) {
                renderNarrative(n);
            } else if (block instanceof Dialogue d) {
                renderDialogue(d);
            } else if (block instanceof Monologue m) {
                renderMonologue(m);
            } else if (block instanceof Choice c) {
                renderChoice(c);
            } else {
                console.println("[Unknown block]");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void renderNarrative(Narrative block) throws InterruptedException {
        console.println("[Narration]");
        console.type(block.getContent(), TYPE_SPEED_MS);
        promptToContinue();
    }

    private void renderDialogue(Dialogue block) throws InterruptedException {
        console.print(block.getSpeaker() + ": ");
        console.type(block.getContent(), TYPE_SPEED_MS);
        promptToContinue();
    }

    private void renderMonologue(Monologue block) throws InterruptedException {
        console.println("[Thoughts]");
        console.type(block.getContent(), TYPE_SPEED_MS);
        promptToContinue();
    }

    private void renderChoice(Choice block) {
        if (block.getContent() != null && !block.getContent().isEmpty()) {
            console.println(block.getContent());
        }
        for (int i = 0; i < block.getOptions().size(); i++) {
            Option opt = block.getOptions().get(i);
            console.println((i + 1) + ". " + opt.getDescription());
        }
    }
    private void promptToContinue() throws InterruptedException {
        console.println("[Press Enter to continue...]");
        console.waitForEnter();
        console.println("");
    }
}
