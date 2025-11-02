package engine.core;

import engine.ui.ConsoleWindow;
import engine.ui.MenuManager;

import java.io.Console;

public class GameLoop {
    private final GameManager gameManager;
    private final ConsoleWindow console;
    private boolean running = true;

    public GameLoop(GameManager gameManager) {
        this.gameManager = gameManager;
        this.console = gameManager.getConsole();
    }

    public void start() {
        System.out.println("GameLoop started!");
        gameManager.initialize();

        while (running) {
            GameState state = gameManager.getState();

            switch (state) {
                case MAIN_MENU -> {
                    MenuManager menu = new MenuManager(console, gameManager);
                    menu.showMainMenu();
                    gameManager.requestContinue();
                }
                case PLAYING -> {
                    gameManager.render();
                    gameManager.handleInput();
                    gameManager.update();
                }
            }
        }

        gameManager.shutdown();
    }
}
