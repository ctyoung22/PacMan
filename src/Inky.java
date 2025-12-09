import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Inky extends Ghost {

    Blinky blinky; // reference to Blinky

    public Inky(int x, int y, int screenWidth, int screenHeight,  int size, int spawnX, int spawnY) {
        super(x, y, size, screenWidth, screenHeight,  Color.CYAN, spawnX , spawnY);
        frames = new Image[]{
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Inky/Blue_Ghost_Spr1.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Inky/Blue_Ghost_Spr2.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/Ghosts/Inky/Blue_Ghost_Spr3.png").toExternalForm())
        };
        spriteView.setImage(frames[currentFrame]);
    }


    @Override
    public void chaseAI(PacMan pacman, GameMap map) {
            // Simpler chase: offset target
            int targetX = pacman.getX() + GameConstants.TILE_SIZE * 2; // 2 tiles ahead
            int targetY = pacman.getY() + GameConstants.TILE_SIZE * 2; // 2 tiles ahead
            moveToward(targetX, targetY, map);
    }

    @Override
    public void scatterAI(PacMan pacman, GameMap map) {
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

