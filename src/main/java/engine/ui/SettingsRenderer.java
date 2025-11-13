package engine.ui;

import engine.core.GameManager;
import engine.input.InputHandler;

public class SettingsRenderer {

    private final ConsoleWindow console;
    private final GameManager gameManager;

    public SettingsRenderer(ConsoleWindow console, GameManager gameManager) {
        this.console = console;
        this.gameManager = gameManager;
    }

    public void showSettingsMenu() {
        boolean inSettings = true;

        while (inSettings) {
            console.clear();
            console.println("=== SETTINGS ===");
            console.println("");
            console.println("[1] -> Exit Settings...");


            int choice = InputHandler.getChoice(1, 1);

            switch (choice) {
                case 1 -> {
                    console.clear();
                    gameManager.requestContinue();
                    inSettings = false;
                }
            }
        }
    }
}
