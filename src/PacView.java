import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;

public class PacView extends BorderPane {
    private Group root = new Group();
    private GameMap gameMap;

    public PacView(int tileSize, int rowCount, int columnCount) {
        gameMap = new GameMap(tileSize, rowCount, columnCount);
        root.getChildren().add(gameMap.getMazeView());
        root.getChildren().add(gameMap.getMapGroup());
    }

    public void addPacmanAndGhosts(PacMan pacman, GhostManager ghostManager) {
        root.getChildren().add(pacman.getPacSprite());
        root.getChildren().addAll(ghostManager.getGhostShapes());
    }

    public void renderMap(PickupFactory pickups) {
        gameMap.loadMap(pickups);
    }

    public void removePickups(PickupFactory pickupFactory){
        gameMap.removePickups(pickupFactory);
    }

    public Group getRoot() { return root; } // for Scene

    public GameMap getGameMap() { return gameMap; }
}
