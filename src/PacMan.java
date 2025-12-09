
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


    // Constructor
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

    // Handle keyboard input for direction changes
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

    // Move PacMan with collision detection
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

    // Update PacMan animation frames
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

    // Getters
    @Override
    public Circle getShape() {
        return shape;
    }

    public ImageView getPacSprite(){
        return pacSprite;
    }



}
