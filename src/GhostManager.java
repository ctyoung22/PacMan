import javafx.scene.Node;
import java.util.ArrayList;
import java.util.List;

public class GhostManager {
    private List<Ghost> ghosts = new ArrayList<>();
    private int ghostSpeed = GameConstants.GHOST_SPEED;
    private long startTime;
    private long[] releaseDelays = {
    0,     // Blinky: immediate
    3000,  // Pinky: 3 seconds later
    6000,  // Inky: 6 seconds later
    9000   // Clyde: 9 seconds later
    };


    public GhostManager(int size) {
    Ghost blinky = new Blinky(9 * size, 7 * size, GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, GameConstants.TILE_SIZE);
    blinky.setVelocity(-ghostSpeed, 0); // start moving left
    ghosts.add(blinky);

    Ghost pinky = new Pinky(8 * size, 9 * size, GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, GameConstants.TILE_SIZE);
    pinky.setVelocity(ghostSpeed, 0); // start moving right
    ghosts.add(pinky);

    Ghost inky = new Inky(9 * size, 9 * size, GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, GameConstants.TILE_SIZE);
    inky.setVelocity(0, -ghostSpeed); // start moving down
    ghosts.add(inky);

    Ghost clyde = new Clyde(10 * size, 9 * size, GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, GameConstants.TILE_SIZE);
    clyde.setVelocity(-ghostSpeed, 0); // start moving up
    ghosts.add(clyde);

    startTime = System.currentTimeMillis();

    }
    /*  for testing all the methods ghost movement 
    // Update all ghosts
    public void update(PacMan pacman, GameMap map, double time) {
        for (Ghost g : ghosts) {
            //g.updateAI(pacman, map);
            g.moveRandom(map);
            g.updateAnimation(time);
            //g.move(map);
        }
    }
        */

    
    public void update(PacMan pacman, GameMap map, double time) {
        long elapsed = System.currentTimeMillis() - startTime;

        for (int i = 0; i < ghosts.size(); i++) {
            Ghost g = ghosts.get(i);

            // Release ghost when its delay has passed
            if (!g.isReleased() && elapsed >= releaseDelays[i]) {
                g.release();
            }

            if (!g.isReleased()) {
                // Before release: wander inside spawn
               g.moveRandom(map);
            } else {
                // After release: normal AI
                g.updateAI(pacman, map);
                g.move(map);
            }

            g.updateAnimation(time);
        }
    }





    // Get shapes of all ghosts for rendering
    public List<Node> getGhostShapes() {
        List<Node> nodes = new ArrayList<>();
        for (Ghost g : ghosts) nodes.add(g.getSprite());
        return nodes;
    }
}

