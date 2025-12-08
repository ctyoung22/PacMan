import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class SpecialPickup extends Pickup {

    public SpecialPickup() {
        super(7, "Special", 20);

        pickupImage = new Image(getClass().getResource("/Spr_Assets/Pickups/PowerPellet_Spr.png").toExternalForm());
        pickupView.setImage(pickupImage);
        updateImagePosition();
    }
}
