import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class SpecialPickup extends Pickup {

    public SpecialPickup(double x, double y) {
        super(x, y, 7, "Special");

        pickupImage = new Image(getClass().getResource("/Spr_Assets/Pickups/PowerPellet_Spr.png").toExternalForm());
        pickupView.setImage(pickupImage);
        updateImagePosition();
    }
}
