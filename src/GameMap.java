import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int tileSize = 0;
    private int rowCount = 0;
    private int columnCount = 0;

    private Group mapGroup = new Group();
    private List<Rectangle> walls = new java.util.ArrayList<>(); // list of wall rectangles
    private ImageView mazeView;

    public GameMap(int tileSize, int rowCount, int columnCount) {
        this.tileSize = tileSize;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        createMapImage();
    }

    private String[] tileMap = {
        "XXXXXXXXXXXXXXXXXXX",
        "XppppppppXppppppppX",
        "XsXXpXXXpXpXXXpXXsX",
        "XpppppppppppppppppX",
        "XpXXpXpXXXXXpXpXXpX",
        "XppppXpppppppXppppX",
        "XXXXpXXXXpXXXXpXXXX",
        "OOOXpXpppppppXpXOOO",
        "XXXXpXpXX XXpXpXXXX",
        "O   pppX   Xppp   O",
        "XXXXpXpXXXXXpXpXXXX",
        "OOOXpXpppppppXpXOOO",
        "XXXXpXpXXXXXpXpXXXX",
        "XppppppppXppppppppX",
        "XpXXpXXXpXpXXXpXXpX",
        "XspXppppp pppppXpsX",
        "XXpXpXpXXXXXpXpXpXX",
        "XppppXpppXpppXppppX",
        "XpXXXXXXpXpXXXXXXpX",
        "XpppppppppppppppppX",
        "XXXXXXXXXXXXXXXXXXX"
    };

    public void loadMap(PickupFactory pickups) {
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                char tile = tileMap[r].charAt(c);
                int x = c * tileSize;
                int y = r * tileSize;

                if (tile == 'X') {
                    Rectangle wall = new Rectangle(x, y, tileSize, tileSize);
                    wall.setFill(Color.TRANSPARENT);
                    walls.add(wall);
                    mapGroup.getChildren().add(wall);
                }
                else if (tile == 'p') {
                    Pickup pellet = new Pellet();
                    //Pickup pellet = pickups.getPickup("Normal");
                    pellet.placePickup(x+16, y+16);
                    mapGroup.getChildren().add(pellet.getView());
                }
                else if (tile == 's') {
                    Pickup specialPickup = new SpecialPickup();
                    //Pickup specialPickup = pickups.getPickup("Special");
                    specialPickup.placePickup(x+16, y+16);
                    mapGroup.getChildren().add(specialPickup.getView());
                }
                // foods could be added here as small rectangles/circles
            }
        }
    }

    public void removePickups(PickupFactory pickupFactory) {
        for(Pickup pickup : pickupFactory.getAllPickups()) {
            if (pickup.isConsumed()) {
                mapGroup.getChildren().remove(pickup.getView());
            }
        }
    }

    public void createMapImage() {
        Image mazeImage = new Image(getClass().getResource("/Spr_Assets/Maze/Pacman_Map.png").toExternalForm());
        mazeView = new ImageView(mazeImage);
        mazeView.setX(0);
        mazeView.setY(0);

        mazeView.setFitWidth(tileSize * columnCount);
        mazeView.setFitHeight(tileSize * rowCount);
        mazeView.setPreserveRatio(true);
    }

    /* public class CollisionBox {
        public static boolean intersects(Shape a, Rectangle wall) {
            return a.getBoundsInParent().intersects(wall.getBoundsInParent());
        }
    } */

    /* public int getCornerX(String string) {
        if (string.equals("LEFT")) {
            return 0;
        } else if (string.equals("RIGHT")) {
            return (columnCount - 1) * tileSize;
        }
        return -1;
    }

    public int getCornerY(String string) {
        if (string.equals("TOP")) {
            return 0;
        } else if (string.equals("BOTTOM")) {
            return (rowCount - 1) * tileSize;
        }
        return -1;
    } */

    public Group getMapGroup() { return mapGroup; }

    public List<Rectangle> getWalls() { return walls; }    

    public ImageView getMazeView() { return mazeView; }
}

