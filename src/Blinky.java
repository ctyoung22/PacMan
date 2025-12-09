
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
//import map.GameMap;

public class Blinky extends Ghost {
    // PacMan pacman;
    GameMap map;

    public Blinky(int x, int y, int screenWidth, int screenHeight, int size, int spawnX , int spawnY) {
        super(x, y, size, screenWidth, screenHeight, Color.RED, spawnX , spawnY);
        
        frames = new Image[]{
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Blinky/Red_Ghost_Spr1.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Blinky/Red_Ghost_Spr2.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Blinky/Red_Ghost_Spr3.png").toExternalForm())
        };
        spriteView.setImage(frames[currentFrame]);
    }


    @Override
    public void chaseAI(PacMan pacman, GameMap map) {
        // Direct pursuit: Pac-Man’s current position
        moveToward(pacman.getX(), pacman.getY(), map);
    }

    @Override
    public void scatterAI(PacMan pacman, GameMap map) {
                // Scatter: move away from Pac-Man (simple version)
                int dx = this.getX() - pacman.getX();
                int dy = this.getY() - pacman.getY();
                int targetX = this.getX() + dx * 3;
                int targetY = this.getY() + dy * 3;

                if (!isBlocked(targetX, targetY, map)) {
                    moveToward(targetX, targetY, map);
                } else {
                    moveRandom(map);
              
                  }
    }


    }

    /*
      // Scatter: fixed top-right corner of the maze
                int targetX = map.getColumnCount() * size; // right edge
                int targetY = 0;                           // top edge
                moveToward(targetX, targetY, map);
                break;


     */



