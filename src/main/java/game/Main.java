package game;

import engine.core.GameLoop;
import game.stories.TestStory;


public class Main {
    public static void main(String[] args) {

        TestStory.testBlocks();

        GameLoop gameLoop = new GameLoop();
        gameLoop.start();


    }
}
