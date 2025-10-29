package engine.input;

import engine.ui.ConsoleWindow;

public class InputHandler {

    private static ConsoleWindow console;

    public static void initialize(ConsoleWindow consoleWindow) {
        console = consoleWindow;
    }

    public static int getChoice(int min, int max) {
        if (console == null) {
            throw new IllegalStateException("Console window not initialized in InputHandler!");
        }

        int choice = -1;
        while (choice < min || choice > max) {
            try {
                String input = console.readLine();
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                console.println("Wpisz liczbę od " + min + " do " + max + ".");
            }
        }
        return choice;
    }
}
