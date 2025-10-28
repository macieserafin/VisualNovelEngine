package engine.core;

import engine.input.InputHandler;
import engine.story.Option;
import engine.story.Scene;
import engine.story.StoryManager;
import game.stories.TestStory;

public class GameLoop {
    private boolean running = true;
    private StoryManager storyManager = new StoryManager();

    private int playerChoice = -1;

    public void start(){
        System.out.println("GameLoop started!");

        storyManager.addScene(TestStory.createScenes());
        storyManager.setActiveScene(TestStory.getStartScene());

        while (running){
            render();
            input();
            update();
        }

        System.out.println("GameLoop Ended!");
    }
    private void render(){
        Scene current = storyManager.getActiveScene();

        if(current == null){
            System.out.println("No scene found!");
            stop();
            return;
        }

        current.display();

        if (current.getOptions().isEmpty()) {
            System.out.println("No options found!");
            stop();
        }

    }

    private void input(){
//        String command = InputHandler.getInput("Wpisz komende ('exit') aby zakonczyc...");
//        if(command.equals("exit")){
//            stop();
//        }

        Scene current = storyManager.getActiveScene();

        if(current == null || current.getOptions().isEmpty()){
            playerChoice = -1;
            return;
        }

        playerChoice = InputHandler.getChoice(1, current.getOptions().size());
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
