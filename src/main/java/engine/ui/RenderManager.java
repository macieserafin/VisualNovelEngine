package engine.ui;

import engine.story.blocks.Option;
import engine.story.blocks.*;
import engine.story.blocks.Choice;

import java.awt.*;

public class RenderManager {

    private final ConsoleWindow console;
    private static final int TYPE_SPEED_MS = 17;

    private static final Color COLOR_NARRATION = new Color(255, 255, 255);
    private static final Color COLOR_DIALOGUE_NAME = new Color(255, 190, 100);
    private static final Color COLOR_DIALOGUE_TEXT = new Color(240, 240, 240);
    private static final Color COLOR_MONOLOGUE = new Color(119, 119, 119);
    private static final Color COLOR_CHOICE = new Color(213, 255, 252);

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
            } else if (block instanceof Action a) {
                return;
            }else {
                console.println("[Unknown block]");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void renderNarrative(Narrative block) throws InterruptedException {
        console.println("");
        console.typeColored(block.getContent(), TYPE_SPEED_MS, COLOR_NARRATION);
        console.println("");
    }

    private void renderDialogue(Dialogue block) throws InterruptedException {
        console.println("");
        console.printColored(block.getSpeaker() + ": ", COLOR_DIALOGUE_NAME);
        console.typeColored(block.getContent(), TYPE_SPEED_MS, COLOR_DIALOGUE_TEXT);
        console.println("");
    }

    private void renderMonologue(Monologue block) throws InterruptedException {
        console.println("");
        console.printColored("❝ ", COLOR_MONOLOGUE);
        console.typeColored(block.getContent(), TYPE_SPEED_MS, COLOR_MONOLOGUE);
        console.printColored(" ❞", COLOR_MONOLOGUE);
        console.println("");
    }

    private void renderChoice(Choice block) {
        console.println("");
        if (block.getContent() != null && !block.getContent().isEmpty()) {
            console.printlnColored(block.getContent(), COLOR_CHOICE);
            console.println("");
        }
        for (int i = 0; i < block.getOptions().size(); i++) {
            Option opt = block.getOptions().get(i);
            console.printlnColored("  " + (i + 1) + ". " + opt.getDescription(), COLOR_CHOICE);
        }
    }

}
