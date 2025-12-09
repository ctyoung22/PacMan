
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
//import map.GameMap;

public class Blinky extends Ghost {
    // PacMan pacman;
    GameMap map;

    // Constructor
    public Blinky(int x, int y, int screenWidth, int screenHeight, int size) {
        super(x, y, size, screenWidth, screenHeight, Color.RED);
        
        frames = new Image[]{
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Blinky/Red_Ghost_Spr1.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Blinky/Red_Ghost_Spr2.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Blinky/Red_Ghost_Spr3.png").toExternalForm())
        };
        spriteView.setImage(frames[currentFrame]);
    }

    // AI behavior
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
