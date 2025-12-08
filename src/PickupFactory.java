import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PickupFactory {
    private Map<String, Pickup> pickupCache = new HashMap<>();
    private ArrayList<Pickup> pickups = new ArrayList<>();

    public Pickup getPickup(String key) {
        if(pickupCache.containsKey(key)) {
            return pickupCache.get(key);
        } else {
            Pickup pickup = null;
            switch (key) {
                case "Normal":
                    pickup = new Pellet();
                    pickups.add(pickup);
                    break;
                case "Special":
                    pickup = new SpecialPickup();
                    pickups.add(pickup);
                    break;
            }
            if (pickup != null) {
                pickupCache.put(key, pickup);
            }
            return pickup;
        }
    }

    public ArrayList<Pickup> getAllPickups() {
        return pickups;
    }
}
