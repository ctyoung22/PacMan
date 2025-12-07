

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.util.List;

public abstract class Ghost extends Character {
    private Circle shape;
    protected boolean aiActive = false; // starts with random movement first 
    protected int aiDelay = GameConstants.GHOST_AI_DELAY; // delay before AI activates
    private int ghostSpeed = GameConstants.GHOST_SPEED; // movement speed

    protected enum Mode { SCATTER, CHASE, FRIGHTENED, EYES } // Ghost modes
    protected Mode currentMode = Mode.SCATTER; // starting mode
    protected long modeTimer = System.currentTimeMillis(); // timer for mode switching

    // For Animation frames
    protected Image[] frames;
    protected int currentFrame = 0;
    // for Animation timing
    protected double frameTime = 0.15; // seconds between frames
    protected double frameTimer = 0.0;
    protected ImageView spriteView; // for rendering the sprite

  

    public Ghost(int x, int y, int size,int screenWidth, int screenHeight, Color color) {
        super(x, y, size,  screenWidth,  screenHeight);
        shape = new Circle(x + size/2, y + size/2, size/2, color);

        spriteView = new ImageView();
        spriteView.setFitWidth(size);
        spriteView.setFitHeight(size);
        spriteView.setX(x);
        spriteView.setY(y);

        setVelocity(ghostSpeed, 0);
    }

    // get the shape for rendering
    @Override
    public Circle getShape() { return shape; }

    // Each ghost will override this with its own AI
    public abstract void updateAI(PacMan pacman, GameMap map);

    public void setVelocity(int vx, int vy) {
         this.velocityX = vx;
         this.velocityY = vy;
 }
 

    protected void updateMode() { // switch between SCATTER and CHASE modes
    long elapsed = System.currentTimeMillis() - modeTimer;
    if (currentMode == Mode.SCATTER && elapsed > GameConstants.GHOST_SCATTER_TIME) { // 7 seconds scatter
        currentMode = Mode.CHASE;
        modeTimer = System.currentTimeMillis();
    } else if (currentMode == Mode.CHASE && elapsed > GameConstants.GHOST_CHASE_TIME) { // 20 seconds chase
        currentMode = Mode.SCATTER;
        modeTimer = System.currentTimeMillis();
    }
}


    // standard movement with collision detection
    @Override 
    public void move(GameMap map) {
        int nextX = x + velocityX;
        int nextY = y + velocityY;

        if (!isBlocked(nextX, nextY, map)) {
            x = nextX;
            y = nextY;
        } else {
            pickRandomDirection(map);
        }

        applyTunnelWrap(GameConstants.TUNNEL_ROW * size);
        updateShape(shape);

        spriteView.setX(x);
        spriteView.setY(y);
    }



        // Move ghost toward a target tile (targetX, targetY)
        protected void moveToward(int targetX, int targetY, GameMap map) {
        int[][] directions = getDirections(ghostSpeed);

        int bestVX = velocityX;
        int bestVY = velocityY;
        double bestDistance = Double.MAX_VALUE;

        for (int[] dir : directions) {
            int testVX = dir[0], testVY = dir[1];
            int nextX = x + testVX, nextY = y + testVY;

            if (!isBlocked(nextX, nextY, map)) {
                double distance = Math.abs(targetX - nextX) + Math.abs(targetY - nextY);
                if (distance < bestDistance) {
                    bestDistance = distance;
                    bestVX = testVX;
                    bestVY = testVY;
                }
            }
        }

        setVelocity(bestVX, bestVY);
    }



    public void moveRandom(GameMap map) {
        boolean atIntersection = (x % size == 0) && (y % size == 0);
        if (atIntersection && Math.random() < 0.7) {
            pickRandomDirection(map);
        }

        int nextX = x + velocityX;
        int nextY = y + velocityY;

        if (!isBlocked(nextX, nextY, map)) {
            x = nextX;
            y = nextY;
        } else {
            pickRandomDirection(map);
        }

        applyTunnelWrap(9 * size);
        updateShape(shape);

        spriteView.setX(x);
        spriteView.setY(y);
    }



    // Random direction picker
    private void pickRandomDirection(GameMap map) {
        int currentVX = velocityX;
        int currentVY = velocityY;

        int[][] directions = getDirections(ghostSpeed);

        List<int[]> validDirs = new ArrayList<>();

        for (int[] dir : directions) {
            if (dir[0] == -currentVX && dir[1] == -currentVY) continue;
            int nextX = x + dir[0], nextY = y + dir[1];
            if (!isBlocked(nextX, nextY, map)) {
                validDirs.add(dir);
            }
        }

        if (!validDirs.isEmpty()) {
            int[] chosen = validDirs.get((int)(Math.random() * validDirs.size()));
            setVelocity(chosen[0], chosen[1]);
        } else {
            // fallback: reverse if boxed in
            setVelocity(-currentVX, -currentVY);
        }
    }

    public ImageView getSprite(){
        return spriteView;
    }

    public void updateAnimation(double time){
        if (frames == null || frames.length == 0){
            return;
        }

        frameTimer += time;
        if(frameTimer >= frameTime){
            frameTimer -= frameTime;
            currentFrame = (currentFrame + 1) % frames.length;

            spriteView.setImage(frames[currentFrame]);

        }

    }


}





