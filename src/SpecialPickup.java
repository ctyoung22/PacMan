import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class SpecialPickup extends Pickup {

    public SpecialPickup(double x, double y) {
        super(x, y, 7, "Special");

        Image pelletImage = new Image(getClass().getResource("/Spr_Assets/Pickups/PowerPellet_Spr.png").toExternalForm());
        ImageView pelletView = new ImageView(pelletImage);
        pelletView.setFitWidth(14);
        pelletView.setFitHeight(14);
    }
}
