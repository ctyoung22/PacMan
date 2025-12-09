import javafx.scene.image.Image;

public class SpecialPickup extends Pickup {

    // Constructor
    public SpecialPickup(double centerX, double centerY) {
        super(centerX, centerY, 7, 20);

        pickupImage = new Image(getClass().getResource("/Spr_Assets/Pickups/PowerPellet_Spr.png").toExternalForm());
        pickupView.setImage(pickupImage);
        updateImagePosition();
    }
}
