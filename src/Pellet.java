import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Pellet extends Pickup {

    public Pellet(double x, double y) {
        super(x, y, 4, "Normal");

        pickupImage = new Image(getClass().getResource("/Spr_Assets/Pickups/Pellet_Spr.png").toExternalForm());
        pickupView.setImage(pickupImage);
        updateImagePosition();
    }
}
