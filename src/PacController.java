import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

public class PacController {
    private PacModel model;
    private PacView view;
    private AnimationTimer gameLoop;

    public PacController(PacModel model, PacView view) {
        this.model = model;
        this.view = view;
        view.renderMap();

        view.addPacmanAndGhosts(model.getPacman(), model.getGhostManager());
    }

    // Start the game loop
    public void startGameLoop(Scene scene) { 
        scene.setOnKeyPressed(e -> model.getPacman().handleInput(e.getCode())); // handle input

        gameLoop = new AnimationTimer() { // main game loop
            private long lastTime = 0;

            @Override
            public void handle(long now) { // called every frame
                if(lastTime == 0){
                    lastTime = now;
                    return;
                }

                double time = (now - lastTime) / 1_000_000_000.0; // nanoseconds to seconds
                lastTime = now;

                model.getPacman().move(view.getGameMap()); // update Pac
                model.getPacman().updateAnimation(time);
                model.getGhostManager().update(model.getPacman(), view.getGameMap(), time); // update Ghosts
            }
        };
        gameLoop.start();
    }
}

