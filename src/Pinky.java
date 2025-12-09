
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



    @Override
    public void chaseAI(PacMan pacman, GameMap map) {
        int targetX = pacman.getX() + pacman.getVelocityX() * GameConstants.TILE_SIZE * 4; // 4 tiles ahead
        int targetY = pacman.getY() + pacman.getVelocityY() * GameConstants.TILE_SIZE * 4; // 4 tiles ahead
        moveToward(targetX, targetY, map); // move toward target
    }



    

}
