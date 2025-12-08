import java.util.HashMap;
import java.util.Map;

public class PickupFactory {
    private Map<String, Pickup> pickupCache = new HashMap<>();

    public Pickup getPickup(String key) {
        if(pickupCache.containsKey(key)) {
            return pickupCache.get(key);
        } else {
            Pickup pickup = null;
            switch (key) {
                case "Normal":
                    pickup = new Pellet(0, 0);
                    break;
                case "Special":
                    pickup = new SpecialPickup(0, 0);
                    break;
            }
            if (pickup != null) {
                pickupCache.put(key, pickup);
            }
            return pickup;
        }
    }
}
