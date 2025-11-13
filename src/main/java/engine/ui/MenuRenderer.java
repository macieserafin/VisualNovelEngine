package engine.ui;

import engine.core.GameManager;
import engine.input.InputHandler;

public class MenuRenderer {

    private final ConsoleWindow console;
    private final GameManager gameManager;

    public MenuRenderer(ConsoleWindow console, GameManager gameManager) {
        this.console = console;
        this.gameManager = gameManager;
    }

    public void showMainMenu() {
        boolean inMenu = true;

        while (inMenu) {
            console.clear();
            console.println("=== VISUAL NOVEL ENGINE ===");
            console.println("1. Start Game");
            console.println("2. Exit");
            console.print("\nChoose an option [1-2]: ");

            int choice = InputHandler.getChoice(1, 2);

            switch (choice) {
                case 1 -> {
                    console.clear();
                    gameManager.startGame();
                    inMenu = false;
                }
                case 2 -> {
                    gameManager.onExitRequested();
                }
            }
        }
    }

}
