package engine.core;

import engine.input.InputHandler;
import engine.story.Option;
import engine.story.Scene;
import engine.story.StoryManager;
import engine.ui.ConsoleWindow;
import game.GameManager;
import game.stories.TestStory;

public class GameLoop {
    private boolean running = true;
    private StoryManager storyManager = new StoryManager();
    private GameManager gameManager = new GameManager();
    ConsoleWindow console = new ConsoleWindow("Visual Novel Engine", 800, 600);

    private int playerChoice = -1;

    public void start(){


        System.out.println("GameLoop started!");

        gameManager.setupGame(storyManager);

        while (running){
            render();
            input();
            update();
        }

        System.out.println("GameLoop Ended!");
    }
    private void render() {
        Scene current = storyManager.getActiveScene();

        if (current == null) {
            console.println("No active scene found!");
            stop();
            return;
        }

        console.clear();
        console.println(current.getContent());

        if (gameManager.isEndScene(current)) {
            console.println("\n[End of the game]");
            console.println("Press ENTER to exit...");
            console.readLine();

            stop();
            console.close();
            System.exit(0);
            return;
        }

        if (current.getOptions().isEmpty()) {
            console.println("\n[No options found!]");
            stop();
            return;
        }

        int i = 1;
        for (var option : current.getOptions()) {
            console.println(i + ". " + option.getDescription());
            i++;
        }
    }

    private void input(){
        String input = console.readLine();

        try {
            playerChoice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            console.println("Please enter a valid number!");
            playerChoice = -1;
        }
    }
    private void update(){
        if(playerChoice == -1){
            stop();
            return;
        }

        storyManager.performChoice(playerChoice);

    }

    public void stop(){
        running = false;
    }



}
