
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PacMan extends Character {
    private Circle shape;
    private int speed = 2; // movement speed
    private char direction = 'R'; // current direction
    private char nextDirection = ' '; // queued direction

    public PacMan(int x, int y, int size, int screenWidth, int screenHeight) {
        super(x, y, size, screenWidth, screenHeight);
        shape = new Circle(x + size / 2, y + size / 2, size / 2, Color.YELLOW); // create Pac
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
    }

    @Override
    public Circle getShape() {
        return shape;
    }

}
