
//import entity.PacMan;
//import entity.GhostManager;
//import map.GameMap;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;


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
      

    private AnimationTimer gameLoop;

    public PacController() {
        gameMap = new GameMap(tileSize, rowCount, columnCount);

        // spawn positions (not hard-coded in map)
        pacman = new PacMan(9 * tileSize, 15 * tileSize, tileSize, boardWidth, boardHeight); // create Pac
        ghostManager = new GhostManager(tileSize);

        root.getChildren().add(gameMap.getMapGroup());
        root.getChildren().add(pacman.getShape());
        root.getChildren().addAll(ghostManager.getGhostShapes());
    }

    // Start the game loop
    public void startGameLoop(Scene scene) { 
        scene.setOnKeyPressed(e -> pacman.handleInput(e.getCode())); // handle input

        gameLoop = new AnimationTimer() { // main game loop
            @Override
            public void handle(long now) { // called every frame
                pacman.move(gameMap); // update Pac
                ghostManager.update(pacman, gameMap); // update Ghosts
            }
        };
        gameLoop.start();
    }

    public Group getRoot() { return root; } // for Scene
    public int getBoardWidth() { return boardWidth; } // for Scene width
    public int getBoardHeight() { return boardHeight; } // for Scene height
}

