import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;

public class PacView extends BorderPane {
    private Group root = new Group();
    private GameMap gameMap;
    private TitleBox titleBox;
    private ScoreBox scoreBox = new ScoreBox();

    // Constructor
    public PacView(int tileSize, int rowCount, int columnCount) {
        gameMap = new GameMap(tileSize, rowCount, columnCount);
        root.getChildren().add(gameMap.getMazeView());
        root.getChildren().add(gameMap.getMapGroup());

        titleBox = new TitleBox(tileSize, columnCount);

        setTop(titleBox);
        setCenter(root);
        setBottom(scoreBox);
    }

    // Add Pacman and ghosts to the view
    public void addPacmanAndGhosts(PacMan pacman, GhostManager ghostManager) {
        root.getChildren().add(pacman.getPacSprite());
        root.getChildren().addAll(ghostManager.getGhostShapes());
    }

    // Render the game map with pickups
    public void renderMap(ArrayList<Pickup> pickups) {
        gameMap.loadMap(pickups);
    }

    // Check for pickups collisions and update the view
    public void removePickups(ArrayList<Pickup> pickups){
        gameMap.removePickups(pickups);
    }

    // Getters
    public Group getRoot() { return root; } // for Scene

    public GameMap getGameMap() { return gameMap; }

    public TitleBox getTitleBox() {
        return titleBox;
    }

    public ScoreBox getScoreBox() {
        return scoreBox;
    }
}
