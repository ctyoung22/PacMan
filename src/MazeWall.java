import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MazeWall extends Rectangle{
    double locX;
    double locY;

    public MazeWall(){
        super(40, 40, Color.BLUE);
    }

    public double getLocX(){
        return locX;
    }

    public double getLocY(){
        return locY;
    }

}
