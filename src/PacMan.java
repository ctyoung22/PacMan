
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PacMan extends Character {
    private Circle shape;
    private int speed = GameConstants.PACMAN_SPEED; // movement speed
    private char direction = 'R'; // current direction
    private char nextDirection = ' '; // queued direction

    private ImageView pacSprite;
    private Map<java.lang.Character, Image[]> animations = new HashMap<>();
    private Image[] currentDirectionFrames;
    private int currentFrame = 0;
    private double frameTime = 0.08;
    private double frameTimer = 0.0;

    private PacModel model;

    private boolean poweredUp = false;
    private long timer = 0; 
    private int superSpeed = GameConstants.PACMAN_SUPERSPEED; // hes faster 
    private int powerTimer = GameConstants.POWER_DURATION;


    public PacMan(int x, int y, int size, int screenWidth, int screenHeight) {
        super(x, y, size, screenWidth, screenHeight);
        shape = new Circle(x + size / 2, y + size / 2, size / 2, Color.YELLOW); // create Pac hit box

        pacSprite = new ImageView();
        pacSprite.setFitWidth(size);
        pacSprite.setFitHeight(size);
        pacSprite.setX(x);
        pacSprite.setY(y);

        // RIGHT Frames
        Image[] rFrames = new Image[]{
            new Image(getClass().getResource("/Spr_Assets/PacMan/RIGHT/Pacman_RIGHT_Spr1.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/PacMan/RIGHT/Pacman_RIGHT_Spr2.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/PacMan/RIGHT/Pacman_RIGHT_Spr3.png").toExternalForm())
        };

        // LEFT Frames
        Image[] lFrames = new Image[]{
            new Image(getClass().getResource("/Spr_Assets/PacMan/LEFT/Pacman_LEFT_Spr1.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/PacMan/LEFT/Pacman_LEFT_Spr2.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/PacMan/LEFT/Pacman_LEFT_Spr3.png").toExternalForm())
        };

        // DOWN Frames
        Image[] dFrames = new Image[]{
            new Image(getClass().getResource("/Spr_Assets/PacMan/DOWN/Pacman_DOWN_Spr1.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/PacMan/DOWN/Pacman_DOWN_Spr2.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/PacMan/DOWN/Pacman_DOWN_Spr3.png").toExternalForm())
        };

        // UP Frames
        Image[] uFrames = new Image[]{
            new Image(getClass().getResource("/Spr_Assets/PacMan/UP/Pacman_UP_Spr1.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/PacMan/UP/Pacman_UP_Spr2.png").toExternalForm()),
            new Image(getClass().getResource("/Spr_Assets/PacMan/UP/Pacman_UP_Spr3.png").toExternalForm())
        };

        animations.put('R', rFrames);
        animations.put('L', lFrames);
        animations.put('D', dFrames);
        animations.put('U', uFrames);

        currentDirectionFrames = animations.get(direction);

        pacSprite.setImage(currentDirectionFrames[0]);
    }

    public void handleInput(KeyCode code) { // queue direction change
        if (code == KeyCode.UP) { // up arrow key
            nextDirection = 'U';
        } else if (code == KeyCode.DOWN) {// down arrow key
            nextDirection = 'D';
        } else if (code == KeyCode.LEFT) { // left arrow key
            nextDirection = 'L';
        } else if (code == KeyCode.RIGHT) { // right arrow key
            nextDirection = 'R';
        }
    }

    @Override
    public void move(GameMap map) { // movement with collision detection
        if (nextDirection != ' ') { // if a direction change is queued
            int testVX = 0, testVY = 0; // test velocities
            switch (nextDirection) { // determine test velocities
                case 'U':
                    testVX = 0;
                    testVY = -speed;
                    break; // up
                case 'D':
                    testVX = 0;
                    testVY = speed;
                    break; // down
                case 'L':
                    testVX = -speed;
                    testVY = 0;
                    break; // left
                case 'R':
                    testVX = speed;
                    testVY = 0;
                    break; // right
            }

            int nextX = x + testVX; // calculate next position in the x direction
            int nextY = y + testVY; // calculate next position in the y direction

            if (!isBlocked(nextX, nextY, map)) { // change direction if not blocked
                direction = nextDirection; // commit to new direction
                currentDirectionFrames = animations.get(direction);
                currentFrame = 0;
                frameTimer = 0.0;
                if(currentDirectionFrames != null && currentDirectionFrames.length > 0){
                    pacSprite.setImage(currentDirectionFrames[0]);
                }

                velocityX = testVX;
                velocityY = testVY;
                nextDirection = ' '; // clear queued direction

                updatePowerMode(); // check if power mode has expired 
            }
            
        }

        int newX = x + velocityX; // calculate next position in the x direction
        int newY = y + velocityY; // calculate next position in the y direction

        if (!isBlocked(newX, newY, map)) { // move if not blocked
            x = newX;
            y = newY;
        }

        applyTunnelWrap(GameConstants.TUNNEL_ROW * size); // tunnel wrap
        updateShape(shape);

        pacSprite.setX(x);
        pacSprite.setY(y);
    }

    public void setSpeed( int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return speed;
    }

    public void activatePowerForm(){
        setSpeed(superSpeed); //slightly faster
        timer = System.currentTimeMillis();
    }

    public void updatePowerMode(){
        if(isPoweredUp() && System.currentTimeMillis() - timer > powerTimer){
            setSpeed(GameConstants.PACMAN_SPEED); // goes back to normal after time limit reached 
        }
    }

    public boolean isPoweredUp(){
        return speed > GameConstants.PACMAN_SPEED;
    }

    @Override
    public Circle getShape() {
        return shape;
    }

    public ImageView getPacSprite(){
        return pacSprite;
    }

    public void updateAnimation(double time){ 
        if (currentDirectionFrames == null || currentDirectionFrames.length == 0){
            return;
        }

        frameTimer += time;
        if(frameTimer >= frameTime){
            frameTimer -= frameTime;
            currentFrame = (currentFrame + 1) % currentDirectionFrames.length;

            pacSprite.setImage(currentDirectionFrames[currentFrame]);

        }

    }

    private int lives = 3;

    public void loseLife() {
        lives--;
        if (lives <= 0) {
            System.out.println("Game Over!");
            // trigger game over state
        } else {
            System.out.println("Lives remaining: " + lives);
        }
    }

    public int getLives(){
        return lives;
    }

    public void resetPosition() {
        this.x = 9 * size; // starting tile
        this.y = 15 * size;
        pacSprite.setX(x);
        pacSprite.setY(y);
        updateShape(getShape());
    }


}
