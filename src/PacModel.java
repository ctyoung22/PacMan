import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;

public class PacModel {
    private final int tileSize = 32;
    private final int rowCount = 21;
    private final int columnCount = 19;
    private final int boardWidth = columnCount * tileSize;
    private final int boardHeight = rowCount * tileSize;

    private PacMan pacman;
    private GhostManager ghostManager;
    private ArrayList<Pickup> pickups = new ArrayList<>();
    private PickupFactory pickupFactory = new PickupFactory();
    
    
    public PacModel() {
        pacman = new PacMan(9 * tileSize, 15 * tileSize, tileSize, boardWidth, boardHeight); // create Pac
        ghostManager = new GhostManager(tileSize);
    }

    public PacMan getPacman() {
        return pacman;
    }

    public GhostManager getGhostManager() {
        return ghostManager;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

}
