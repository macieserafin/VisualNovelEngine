package engine.ui;

import engine.core.GameManager;
import engine.input.InputHandler;
import engine.story.StoryManager;

public class CreatorRenderer {

    private final ConsoleWindow console;
    private final GameManager gameManager;

    public CreatorRenderer(ConsoleWindow console, GameManager gameManager) {
        this.console = console;
        this.gameManager = gameManager;
    }

    public void createCharacter() {
        boolean inCreator = true;

        while (inCreator) {
            console.clear();
            console.println("=== Whats ur name ? ===");
            console.println("");

            String input = InputHandler.getString();
            gameManager.createCharacter(input);

            console.clear();
            gameManager.requestContinue();
            inCreator = false;


        }
    }
}
