package engine.core;

import engine.ui.ConsoleWindow;
import engine.ui.CreatorRenderer;
import engine.ui.MenuRenderer;
import engine.ui.SettingsRenderer;

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
                case PLAYING -> {
                    gameManager.playStep();
                    gameManager.update();
                }
                case MAIN_MENU -> {
                    MenuRenderer menu = new MenuRenderer(console, gameManager);
                    menu.showMainMenu();
                    gameManager.requestContinue();
                }
                case SETTINGS -> {
                    SettingsRenderer settings = new SettingsRenderer(console, gameManager);
                    settings.showSettingsMenu();
                    gameManager.requestContinue();
                }
                case CREATOR -> {
                    CreatorRenderer creator = new CreatorRenderer(console, gameManager);
                    creator.createCharacter();
                    gameManager.requestContinue();
                }
            }
        }

        gameManager.shutdown();
    }
}
