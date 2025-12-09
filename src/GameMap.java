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

    // Constructor
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

    // Load map from tileMap and create walls and pickups
    public void loadMap(ArrayList<Pickup> pickups) {
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
                    Pickup pellet = new Pellet(x+16, y+16);
                    pickups.add(pellet);
                    mapGroup.getChildren().add(pellet.getView());
                }
                else if (tile == 's') {
                    Pickup specialPickup = new SpecialPickup(x+16, y+16);
                    pickups.add(specialPickup);
                    mapGroup.getChildren().add(specialPickup.getView());
                }
            }
        }
    }

    // Place a pickup on the map at specified coordinates
    public void placePickup(Pickup pickup, double x, double y) {
        pickup.setCenterX(x);
        pickup.setCenterY(y);
        pickup.updateImagePosition();
        mapGroup.getChildren().add(pickup.getView());
    }

    // Remove consumed pickups from the map
    public void removePickups(ArrayList<Pickup> pickups) {
        for(Pickup pickup : pickups) {
            if (pickup.isConsumed()) {
                mapGroup.getChildren().remove(pickup.getView());
            }
        }
    }

    // Create the maze image and set its properties
    public void createMapImage() {
        Image mazeImage = new Image(getClass().getResource("/Spr_Assets/Maze/Pacman_Map.png").toExternalForm());
        mazeView = new ImageView(mazeImage);
        mazeView.setX(0);
        mazeView.setY(0);

        mazeView.setFitWidth(tileSize * columnCount);
        mazeView.setFitHeight(tileSize * rowCount);
        mazeView.setPreserveRatio(true);
    }

    // Getters
    public Group getMapGroup() { return mapGroup; }

    public List<Rectangle> getWalls() { return walls; }    

    public ImageView getMazeView() { return mazeView; }
}

