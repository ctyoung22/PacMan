import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Pickup extends Circle{
    private boolean consumed;
    private int pointValue;
    private double x;
    private double y;
    protected Image pickupImage;
    protected ImageView pickupView;

    public Pickup(double centerX, double centerY, double radius, int pointValue) {
        super(centerX, centerY, radius);
        setCenterX(centerX);
        setCenterY(centerY);
        this.consumed = false;
        this.pointValue = pointValue;

        pickupView = new ImageView();
        pickupView.setFitHeight(32);
        pickupView.setFitWidth(32);

        updateImagePosition();
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void consumePickup() {
        this.consumed = true;
    }

    public ImageView getView(){
        return pickupView;
    }

    public Image getImage(){
        return pickupImage;
    }

    protected void updateImagePosition(){
        pickupView.setX(getCenterX() - pickupView.getFitWidth() / 2);
        pickupView.setY(getCenterY() - pickupView.getFitHeight() / 2);
    }

    public int getPointValue() {
        return pointValue;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
