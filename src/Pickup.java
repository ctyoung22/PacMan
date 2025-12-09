import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public abstract class Pickup extends Circle{
    private boolean consumed;
    private int pointValue;
    private double x;
    private double y;
    protected Image pickupImage;
    protected ImageView pickupView;

    // Constructor
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

    // Check if the pickup has been consumed
    public boolean isConsumed() {
        return consumed;
    }

    // Mark the pickup as consumed
    public void consumePickup() {
        this.consumed = true;
    }

    // Update the image position to match the pickup's center
    protected void updateImagePosition(){
        pickupView.setX(getCenterX() - pickupView.getFitWidth() / 2);
        pickupView.setY(getCenterY() - pickupView.getFitHeight() / 2);
    }

    // Getters
    public ImageView getView(){
        return pickupView;
    }

    public Image getImage(){
        return pickupImage;
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
