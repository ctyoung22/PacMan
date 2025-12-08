import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
//import map.GameMap;

public abstract class Character {
    protected int x, y, size;
    protected int velocityX = 0, velocityY = 0;
    protected int screenWidth; //  screen width
    protected int screenHeight; // screen height

    public Character(int x, int y, int size, int screenWidth, int screenHeight) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public abstract Shape getShape();

    public void move(GameMap map) {
        x += velocityX;
        y += velocityY;
    }

    protected boolean isBlocked(int nextX, int nextY, GameMap map) {  // check for wall collisions
        Circle testShape = new Circle(nextX + size/2, nextY + size/2, size/2); // test shape
        for (Rectangle wall : map.getWalls()) {
            if (CollisionBox.intersects(testShape, wall)) {
                return true;
            }
        }
        return false;
    }

    protected void applyTunnelWrap(int tunnelRowY) {
        if (y >= tunnelRowY && y < tunnelRowY + size) {
            if (x < 0) {
                x = screenWidth - size;
            } else if (x > screenWidth - size) {
                x = 0;
            }
        }
    }

    protected void updateShape(Circle shape) {
        shape.setCenterX(x + size/2);
        shape.setCenterY(y + size/2);
    }

    protected int[][] getDirections(int step) {
        return new int[][] {
            { step, 0 }, { -step, 0 },
            { 0, step }, { 0, -step }
        };
    }




    public int getX() { return x; }
    public int getY() { return y; }
    public int getVelocityX() { return velocityX; }
    public int getVelocityY() { return velocityY; }
    public int getSize() { return size; }


}


