import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CollisionBox {
        
  // Circle vs Rectangle collision check
    public static boolean intersects(Circle circle, Rectangle wall) {
        double circleLeft   = circle.getCenterX() - circle.getRadius();  // left edge of circle
        double circleRight  = circle.getCenterX() + circle.getRadius();  // right edge of circle
        double circleTop    = circle.getCenterY() - circle.getRadius(); // top edge of circle
        double circleBottom = circle.getCenterY() + circle.getRadius(); // bottom edge of circle

        double wallLeft   = wall.getX();  // left edge of wall
        double wallRight  = wall.getX() + wall.getWidth(); // right edge of wall
        double wallTop    = wall.getY(); // top edge of wall
        double wallBottom = wall.getY() + wall.getHeight();    // bottom edge of wall

        return circleLeft < wallRight && // check for overlap
               circleRight > wallLeft && // horizontal overlap
               circleTop < wallBottom && // vertical overlap
               circleBottom > wallTop; // vertical overlap  
    }


}
