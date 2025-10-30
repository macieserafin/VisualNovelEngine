package game;

import engine.core.GameLoop;
import engine.core.GameManager;
import engine.ui.ConsoleWindow;

import java.awt.*;



public class Main {
    public static void main(String[] args) {
        GameManager manager = new GameManager();
        GameLoop loop = new GameLoop(manager);
        loop.start();
    }
}
