import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class PacView extends BorderPane {
    private Group root = new Group();
    private GameMap gameMap;
    private PacModel model;
    private TitleBox titleBox;
    private ScoreBox scoreBox = new ScoreBox();

    public PacView(int tileSize, int rowCount, int columnCount) {
        this.gameMap = new GameMap(tileSize, rowCount, columnCount);
        root.getChildren().add(gameMap.getMazeView());
        root.getChildren().add(gameMap.getMapGroup());

        titleBox = new TitleBox(tileSize, columnCount);

        setTop(titleBox);
        setCenter(root);
        setBottom(scoreBox);
    }

    public void addPacmanAndGhosts(PacMan pacman, GhostManager ghostManager) {
        root.getChildren().add(pacman.getPacSprite());
        root.getChildren().addAll(ghostManager.getGhostShapes());
    }

    public void renderMap(ArrayList<Pickup> pickups) {
        gameMap.loadMap(pickups);
        
    }

    public void removePickups(ArrayList<Pickup> pickups){
        gameMap.removePickups(pickups);
    }

    // adds the game over text when the fail state is reached
    public void addGameOverPane() {
        StackPane gameOverPane = new StackPane();
        gameOverPane.setPrefSize(100, 100);
        Label gameOverLabel = new Label("Game Over");
        gameOverLabel.setStyle("-fx-font-size: 48px; -fx-text-fill: red;");
        gameOverPane.getChildren().add(gameOverLabel);
        setCenter(gameOverPane);
        gameOverPane.toFront();
    }

    public Group getRoot() { return root; } // for Scene

    public GameMap getGameMap() { return gameMap; }

    public TitleBox getTitleBox() {
        return titleBox;
    }

    public ScoreBox getScoreBox() {
        return scoreBox;
    }
}
