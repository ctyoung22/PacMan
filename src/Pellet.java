import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Pellet extends Pickup {

    public Pellet(double x, double y) {
        super(x, y, 5, "Normal");

        Image pelletImage = new Image(getClass().getResource("/Spr_Assets/Pickups/Pellet_Spr.png").toExternalForm());
        ImageView pelletView = new ImageView(pelletImage);
        pelletView.setFitWidth(10);
        pelletView.setFitHeight(10);

    }
}
