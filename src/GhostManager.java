import javafx.scene.Node;
import java.util.ArrayList;
import java.util.List;

public class GhostManager {
    private List<Ghost> ghosts = new ArrayList<>();
    private int ghostSpeed = GameConstants.GHOST_SPEED;
    private GameMap map;
    private long startTime;
    private long[] releaseDelays = {
    0,     // Blinky: immediate
    3000,  // Pinky: 3 seconds later
    6000,  // Inky: 6 seconds later
    9000   // Clyde: 9 seconds later
    };

    private long frightenedActivated;
    private long frightDuration = GameConstants.FRIGHTENED_DURATION;
    private boolean isfrighten = false;

    public GhostManager(int size) {
    }

    public void initGhosts(GameMap map, int size){
    // spawn coordinates for ghost house
    int spawnX = map.getGhostSpawnX();
    int spawnY = map.getGhostSpawnY();


    Ghost blinky = new Blinky(9 * size, 7 * size, GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, GameConstants.TILE_SIZE, spawnX, spawnY);
    blinky.setVelocity(-ghostSpeed, 0); // start moving left
    ghosts.add(blinky);
   

    Ghost pinky = new Pinky(8 * size, 9 * size, GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, GameConstants.TILE_SIZE, spawnX, spawnY);
    pinky.setVelocity(ghostSpeed, 0); // start moving right
    ghosts.add(pinky);

    Ghost inky = new Inky(9 * size, 9 * size, GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, GameConstants.TILE_SIZE, spawnX, spawnY);
    inky.setVelocity(0, -ghostSpeed); // start moving down
    ghosts.add(inky);

    Ghost clyde = new Clyde(10 * size, 9 * size, GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, GameConstants.TILE_SIZE,spawnX, spawnY);
    clyde.setVelocity(-ghostSpeed, 0); // start moving up
    ghosts.add(clyde);

    // sets spawn for each ghost
    for(Ghost g : ghosts) {
        g.setSpawn(spawnX, spawnY);
    }

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

        if(isfrighten && System.currentTimeMillis() - frightenedActivated >= frightDuration){
            resetModes();
            isfrighten = false;
        }
    }

        // --- NEW: helper to frighten all ghosts ---
    public void frightenAll() {
        for (Ghost g : ghosts) {
            g.setMode(Ghost.Mode.FRIGHTENED);
            

        }
        frightenedActivated = System.currentTimeMillis();
        isfrighten = true;
        System.out.println("Ghosts frightened!");
    }

    // --- NEW: reset frightened ghosts when power mode ends ---
    public void resetModes() {
        for (Ghost g : ghosts) {
            if (g.getMode() == Ghost.Mode.FRIGHTENED) {
                g.setMode(Ghost.Mode.CHASE); // or SCATTER depending on cycle
            }
        }
        System.out.println("ghosts reset to normal mode");
    }


    public List<Ghost> getGhosts(){
        return ghosts;
    }

    // Get shapes of all ghosts for rendering
    public List<Node> getGhostShapes() {
        List<Node> nodes = new ArrayList<>();
        for (Ghost g : ghosts) nodes.add(g.getSprite());
        return nodes;
    }

    public void resetGhostsToSpawn() {
        int spawnX = map.getGhostSpawnX();
        int spawnY = map.getGhostSpawnY();
        for (Ghost g : ghosts) {
            g.setPosition(spawnX, spawnY);
            g.setMode(Ghost.Mode.SCATTER);
             // restart cycle


        }
    }


}

