import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Pickup extends Circle implements IPickupFlyweight {
    private boolean consumed;
    private String type;
    protected Image pickupImage;
    protected ImageView pickupView;

    public Pickup(double centerX, double centerY, double radius, String type) {
        super(centerX, centerY, radius);
        this.consumed = false;
        this.type = type;

        pickupView = new ImageView();
        pickupView.setFitHeight(32);
        pickupView.setFitWidth(32);

        updateImagePosition();
    }

    public boolean isConsumed() {
        return consumed;
    }

    @Override
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
    
}
