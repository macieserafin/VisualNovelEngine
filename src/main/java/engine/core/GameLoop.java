package engine.core;

public class GameLoop {

    private final GameManager gameManager;

    public GameLoop(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void start() {
        System.out.println("GameLoop started!");

        gameManager.initialize();

        while (gameManager.isRunning()) {
            gameManager.playStep();
        }

        gameManager.shutdown();
    }
}
