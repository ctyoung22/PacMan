
import javafx.scene.paint.Color;
//import map.GameMap;

public class Pinky extends Ghost {
    public Pinky(int x, int y, int screenWidth, int screenHeight, int size) {
        super(x, y, size, screenWidth, screenHeight, Color.PINK);
    }

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
    }

}
