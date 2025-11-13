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
            }
        }
        return choice;
    }

    public static String getName(){

        if (console == null) {
            throw new IllegalStateException("Console window not initialized in InputHandler!");
        }

        return console.readLine();
    }

    public static String getString() {
        if (console == null) {
            throw new IllegalStateException("Console window not initialized in InputHandler!");
        }

        String input = "";

        while (true) {
            input = console.readLine();

            if (isValidName(input)) {
                return input;
            }

            console.println("Invalid name! Use only letters, 3–10 characters.");
            console.println("Try again:");
        }
    }

    private static boolean isValidName(String name) {
        if (name == null) return false;

        name = name.trim();

        if (name.length() < 3 || name.length() > 10) {
            return false;
        }

        return name.matches("[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż]+");
    }


}
