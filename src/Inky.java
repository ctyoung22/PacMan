import javafx.scene.paint.Color;

public class Inky extends Ghost {

    Blinky blinky; // reference to Blinky

    public Inky(int x, int y, int screenWidth, int screenHeight,  int size) {
        super(x, y, size, screenWidth, screenHeight,  Color.CYAN);
    }

    @Override
    public void updateAI(PacMan pacman, GameMap map) {
        // Inky: complex targeting using "shadowing" Blinky 
        updateMode();

        if (currentMode == Mode.CHASE) { // chase mode
            // Simpler chase: offset target
            int targetX = pacman.getX() + GameConstants.TILE_SIZE * 2; // 2 tiles ahead
            int targetY = pacman.getY() + GameConstants.TILE_SIZE * 2; // 2 tiles ahead
            moveToward(targetX, targetY, map);
        } else if (currentMode == Mode.SCATTER) { // scatter mode
            int dx = this.getX() - pacman.getX();
            int dy = this.getY() - pacman.getY();
            int targetX = this.getX() + dx * 3;
            int targetY = this.getY() + dy * 3;

            if (!isBlocked(targetX, targetY, map)) { // if not blocked
                moveToward(targetX, targetY, map); // move toward target
            } else {
                moveRandom(map); // otherwise move randomly
            }
        }
    }


}

