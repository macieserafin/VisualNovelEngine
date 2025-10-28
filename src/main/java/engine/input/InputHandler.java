package engine.input;

import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getInput(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine();
    }

    public static int getChoice(int min, int max) {

        while (true) {
            String input = scanner.nextLine();

        try{
            int choice = Integer.parseInt(input);

            if (choice >= min && choice <= max) {
            return choice;
            } else {
                System.out.println("Please enter a number between " + min + " and " + max);
            }
        }catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }

        }
    }

}
