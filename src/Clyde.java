

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
//import map.GameMap;

public class Clyde extends Ghost {
    public Clyde(int x, int y, int screenWidth, int screenHeight, int size, int spawnX , int spawnY) {
        super(x, y, size, screenWidth,  screenHeight,  Color.ORANGE, spawnX , spawnY);
        frames = new Image[]{
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Clyde/Orange_Ghost_Spr1.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Clyde/Orange_Ghost_Spr2.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Clyde/Orange_Ghost_Spr3.png").toExternalForm())
        };
        spriteView.setImage(frames[currentFrame]);
    }

    /* 
    @Override
    public void updateAI(PacMan pacman, GameMap map) {
        // Clyde: chases Pac-Man until close, then scatters
        updateMode();

        if (currentMode == Mode.CHASE) {
            int dx = pacman.getX() - this.getX();
            int dy = pacman.getY() - this.getY();
            double distance = Math.abs(dx) + Math.abs(dy);

            if (distance >= GameConstants.TILE_SIZE * 8) {
                moveToward(pacman.getX(), pacman.getY(), map);
            } else {
                // Retreat if too close
                int retreatX = this.getX() - dx * 3;
                int retreatY = this.getY() - dy * 3;

                if (!isBlocked(retreatX, retreatY, map)) {
                    moveToward(retreatX, retreatY, map);
                } else {
                    moveRandom(map);
                }
            }
        } else if (currentMode == Mode.SCATTER) {
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
    } */

    @Override
    public void chaseAI(PacMan pacman, GameMap map) {
            int dx = pacman.getX() - this.getX();
            int dy = pacman.getY() - this.getY();
            double distance = Math.abs(dx) + Math.abs(dy);

            if (distance >= GameConstants.TILE_SIZE * 8) {
                moveToward(pacman.getX(), pacman.getY(), map);
            } else {
                // Retreat if too close
                int retreatX = this.getX() - dx * 3;
                int retreatY = this.getY() - dy * 3;

                if (!isBlocked(retreatX, retreatY, map)) {
                    moveToward(retreatX, retreatY, map);
                } else {
                    moveRandom(map);
                }
            }
    }

    @Override
    public void scatterAI(PacMan pacman, GameMap map) {
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
