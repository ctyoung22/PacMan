import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Pellet extends Pickup {

    public Pellet(double centerX, double centerY) {
        super(centerX, centerY, 4, 10);

        pickupImage = new Image(getClass().getResource("/Spr_Assets/Pickups/Pellet_Spr.png").toExternalForm());
        pickupView.setImage(pickupImage);
        updateImagePosition();
    }
}
