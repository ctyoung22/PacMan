import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.scene.Scene;

public class PacController {
    private PacModel model;
    private PacView view;
    private AnimationTimer gameLoop;

    public PacController(PacModel model, PacView view) {
        this.model = model;
        this.view = view;
        model.setMap(view.getGameMap());
        view.renderMap(model.getPickups());

        view.addPacmanAndGhosts(model.getPacman(), model.getGhostManager());
    }

    // Start the game loop
    public void startGameLoop(Scene scene) { 
        scene.setOnKeyPressed(e -> model.getPacman().handleInput(e.getCode())); // handle input

        gameLoop = new AnimationTimer() { // main game loop
            private long lastTime = 0;

            @Override
            public void handle(long now) { // called every frame

                if(model.isGameOver()){
                    stop();
                    return;
                }
                if(lastTime == 0){
                    lastTime = now;
                    return;
                }

                double time = (now - lastTime) / 1_000_000_000.0; // nanoseconds to seconds
                lastTime = now;

                model.movePacman(view.getGameMap()); // update Pac position and check pickups
                view.removePickups(model.getPickups());
                trackScore();
                model.getPacman().updateAnimation(time);
                model.getGhostManager().update(model.getPacman(), view.getGameMap(), time); // update Ghosts
            }
        };
        gameLoop.start();
    }

    public void trackScore() {
        IntegerProperty scoreProperty = model.getScore();
        scoreProperty.addListener((ov)-> {
            view.getScoreBox().updateScore(scoreProperty.getValue());
        } );
    }
}

