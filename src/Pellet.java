import javafx.scene.image.Image;

public class Pellet extends Pickup {

    // Constructor
    public Pellet(double centerX, double centerY) {
        super(centerX, centerY, 4, 10);

        pickupImage = new Image(getClass().getResource("/Spr_Assets/Pickups/Pellet_Spr.png").toExternalForm());
        pickupView.setImage(pickupImage);
        updateImagePosition();
    }
}
