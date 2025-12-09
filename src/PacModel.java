import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;

public class PacModel {
    private final int tileSize = 32;
    private final int rowCount = 21;
    private final int columnCount = 19;
    private final int boardWidth = columnCount * tileSize;
    private final int boardHeight = rowCount * tileSize;
    private IntegerProperty score = new SimpleIntegerProperty(0);

    private PacMan pacman;
    private GhostManager ghostManager;
    private GameMap map;
    private ArrayList<Pickup> pickups = new ArrayList<>();

    
    
    public PacModel() {
        pacman = new PacMan(9 * tileSize, 15 * tileSize, tileSize, boardWidth, boardHeight); // create Pac
        ghostManager = new GhostManager(tileSize);
    }

    public void setMap(GameMap map) {
        this.map = map;
        ghostManager.initGhosts(map, tileSize);
    }

    /* public void updatePosition() {
        pacman.move(map);
        ghostManager.updateGhosts(map, pacman);
    } */

    public void movePacman(GameMap map) {
        if(gameOver){
            return; // nothing happens after game over
        }
        pacman.move(map);
        for(Pickup pickup : pickups) {
            if (!pickup.isConsumed() && pacman.getPacSprite().getBoundsInParent().intersects(pickup.getBoundsInParent())) {
                pickup.consumePickup();
                score.set(score.get() + pickup.getPointValue());
                if(pickup instanceof SpecialPickup) {
                    //TODO Make pacman a little faster and able to eat ghosts for a short time
                    pacman.activatePowerForm(); // pacman gets faster and timer starts

                    //ghost go into frightened mode
                    ghostManager.frightenAll();
                }
            }
        }

        // --- Ghost collision ---
        for (Ghost g : ghostManager.getGhosts()) {
            if (pacman.getPacSprite().getBoundsInParent().intersects(g.getSprite().getBoundsInParent())) {
                if (g.getMode() == Ghost.Mode.FRIGHTENED) {
                    // Pac-Man eats ghost → ghost becomes eyes
                    g.setMode(Ghost.Mode.EYES);
                    score.set(score.get() + 200); // award points (scale up if multiple ghosts eaten)
                    System.out.println("Ghost eaten! Switching to EYES mode.");
                } else if (g.getMode() == Ghost.Mode.CHASE || g.getMode() == Ghost.Mode.SCATTER) {
                    // Pac-Man dies
                    pacman.loseLife(); // keeps track of lives 
                    if(pacman.getLives() <= 0){
                        setGameOver();
                    }else{

                        pacman.resetPosition();
                        ghostManager.resetGhostsToSpawn();
                        System.out.println("Pac-Man hit by ghost! Life lost.");
                    }
                }
                // If ghost is in EYES mode, ignore collision (they’re harmless)
            }
        } 

    }

    public boolean gameOver = false;

    public boolean isGameOver(){
        return gameOver;
    }

    public void setGameOver(){
        gameOver = true;
    }


    

    public PacMan getPacman() {
        return pacman;
    }

    public ArrayList<Pickup> getPickups() {
        return pickups;
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

    public IntegerProperty getScore() {
        return score;
    }

}
