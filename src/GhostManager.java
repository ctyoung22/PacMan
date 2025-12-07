import javafx.scene.Node;
import java.util.ArrayList;
import java.util.List;

public class GhostManager {
    private List<Ghost> ghosts = new ArrayList<>();
    private int ghostSpeed = GameConstants.GHOST_SPEED;

    public GhostManager(int size) {
    Ghost blinky = new Blinky(9 * size, 9 * size, GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, GameConstants.TILE_SIZE);
    blinky.setVelocity(-ghostSpeed, 0); // start moving left
    ghosts.add(blinky);

    Ghost pinky = new Pinky(8 * size, 9 * size, GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, GameConstants.TILE_SIZE);
    pinky.setVelocity(ghostSpeed, 0); // start moving right
    ghosts.add(pinky);

    Ghost inky = new Inky(10 * size, 9 * size, GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, GameConstants.TILE_SIZE);
    inky.setVelocity(0, ghostSpeed); // start moving down
    ghosts.add(inky);

    Ghost clyde = new Clyde(11 * size, 9 * size, GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, GameConstants.TILE_SIZE);
    clyde.setVelocity(0, -ghostSpeed); // start moving up
    ghosts.add(clyde);


    }
    // Update all ghosts
    public void update(PacMan pacman, GameMap map) {
        for (Ghost g : ghosts) {
            g.moveRandom(map);
            g.updateAI(pacman, map);
           // g.move(map);
        }
    }

    // Get shapes of all ghosts for rendering
    public List<Node> getGhostShapes() {
        List<Node> nodes = new ArrayList<>();
        for (Ghost g : ghosts) nodes.add(g.getShape());
        return nodes;
    }
}

