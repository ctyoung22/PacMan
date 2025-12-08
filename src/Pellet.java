import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Pellet extends Pickup {

    public Pellet() {
        super(4, "Normal", 10);

        pickupImage = new Image(getClass().getResource("/Spr_Assets/Pickups/Pellet_Spr.png").toExternalForm());
        pickupView.setImage(pickupImage);
        updateImagePosition();
    }
}
