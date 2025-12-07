
//import entity.PacMan;
//import entity.GhostManager;
//import map.GameMap;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class PacController {
    private final int rowCount = 21;
    private final int columnCount = 19;
    private final int tileSize = 32;
    private final int boardWidth = columnCount * tileSize;
    private final int boardHeight = rowCount * tileSize;

    private Group root = new Group();
    private GameMap gameMap;
    private PacMan pacman;
    private GhostManager ghostManager;
    
    private ImageView mazeView;

    private AnimationTimer gameLoop;

    public PacController() {
        gameMap = new GameMap(tileSize, rowCount, columnCount);

        // spawn positions (not hard-coded in map)
        pacman = new PacMan(9 * tileSize, 15 * tileSize, tileSize, boardWidth, boardHeight); // create Pac
        ghostManager = new GhostManager(tileSize);
        createMapImage();
        root.getChildren().add(gameMap.getMapGroup());
        root.getChildren().add(mazeView);
        root.getChildren().add(pacman.getPacSprite());
        root.getChildren().addAll(ghostManager.getGhostShapes());
    }

    // Start the game loop
    public void startGameLoop(Scene scene) { 
        scene.setOnKeyPressed(e -> pacman.handleInput(e.getCode())); // handle input

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

                pacman.move(gameMap); // update Pac
                pacman.updateAnimation(time);
                ghostManager.update(pacman, gameMap, time); // update Ghosts
            }
        };
        gameLoop.start();
    }

    private void createMapImage(){
        Image mazeImage = new Image(getClass().getResource("/Spr_Assets/Maze/Pacman_Map.png").toExternalForm());
        mazeView = new ImageView(mazeImage);
        mazeView.setX(0);
        mazeView.setY(0);

        mazeView.setFitWidth(boardWidth);
        mazeView.setFitHeight(boardHeight);
        mazeView.setPreserveRatio(true);
    }

    public Group getRoot() { return root; } // for Scene
    public int getBoardWidth() { return boardWidth; } // for Scene width
    public int getBoardHeight() { return boardHeight; } // for Scene height
}

