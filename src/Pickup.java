import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Pickup extends Circle implements IPickupFlyweight {
    private boolean consumed;
    private String type;

    public Pickup(double centerX, double centerY, double radius, String type) {
        super(centerX, centerY, radius);
        this.consumed = false;
        this.type = type;
    }

    public boolean isConsumed() {
        return consumed;
    }

    @Override
    public void consumePickup() {
        this.consumed = true;
    }
}
