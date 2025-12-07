
import javafx.scene.paint.Color;
//import map.GameMap;

public class Blinky extends Ghost {
    // PacMan pacman;
    GameMap map;

    public Blinky(int x, int y, int screenWidth, int screenHeight, int size) {
        super(x, y, size, screenWidth, screenHeight, Color.RED);
    }

    @Override
    public void updateAI(PacMan pacman, GameMap map) {
        // Blinky: directly chases Pac-Man
        updateMode();

        if (currentMode == Mode.CHASE) {
            moveToward(pacman.getX(), pacman.getY(), map);
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
    }



}
