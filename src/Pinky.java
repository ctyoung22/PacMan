
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
//import map.GameMap;

public class Pinky extends Ghost {
    public Pinky(int x, int y, int screenWidth, int screenHeight, int size, int spawnX , int spawnY) {
        super(x, y, size, screenWidth, screenHeight, Color.PINK, spawnX , spawnY);
        frames = new Image[]{
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Pinky/Pink_Ghost_Spr1.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Pinky/Pink_Ghost_Spr2.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Pinky/Pink_Ghost_Spr3.png").toExternalForm())
        };
        spriteView.setImage(frames[currentFrame]);
    }

    /* 
    @Override
    public void updateAI(PacMan pacman, GameMap map) {
        // Pinky: targets 4 tiles ahead of Pac-Man
        updateMode();

        if (currentMode == Mode.CHASE) { // chase mode
            int targetX = pacman.getX() + pacman.getVelocityX() * GameConstants.TILE_SIZE * 4; // 4 tiles ahead
            int targetY = pacman.getY() + pacman.getVelocityY() * GameConstants.TILE_SIZE * 4; // 4 tiles ahead
            moveToward(targetX, targetY, map); // move toward target
        } else if (currentMode == Mode.SCATTER) {
            int dx = this.getX() - pacman.getX(); // calculate X distance
            int dy = this.getY() - pacman.getY(); // calculate Y distance
            int targetX = this.getX() + dx * 3; // target away from Pacman x-direction
            int targetY = this.getY() + dy * 3; // target away from Pacman y-direction

            if (!isBlocked(targetX, targetY, map)) { // if not blocked
                moveToward(targetX, targetY, map); // move toward target
            } else {
                moveRandom(map); // otherwise move randomly
            }
        }
    }*/

    @Override
    public void chaseAI(PacMan pacman, GameMap map) {
        int targetX = pacman.getX() + pacman.getVelocityX() * GameConstants.TILE_SIZE * 4; // 4 tiles ahead
        int targetY = pacman.getY() + pacman.getVelocityY() * GameConstants.TILE_SIZE * 4; // 4 tiles ahead
        moveToward(targetX, targetY, map); // move toward target
    }

    @Override
    public void scatterAI(PacMan pacman, GameMap map) {
             int dx = this.getX() - pacman.getX(); // calculate X distance
            int dy = this.getY() - pacman.getY(); // calculate Y distance
            int targetX = this.getX() + dx * 3; // target away from Pacman x-direction
            int targetY = this.getY() + dy * 3; // target away from Pacman y-direction

            if (!isBlocked(targetX, targetY, map)) { // if not blocked
                moveToward(targetX, targetY, map); // move toward target
            } else {
                moveRandom(map); // otherwise move randomly
            }
    }

    

}
