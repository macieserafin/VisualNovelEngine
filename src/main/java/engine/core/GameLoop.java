package engine.core;

public class GameLoop {
    private final GameManager gameManager;
    private boolean running = true;

    public GameLoop(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void start() {
        System.out.println("GameLoop started!");

        gameManager.initialize();

        while (running && gameManager.isRunning()) {
            gameManager.render();
            gameManager.handleInput();
            gameManager.update();
        }

        gameManager.shutdown();
    }
}
