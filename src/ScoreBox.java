import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ScoreBox extends HBox {
    private Label scoreLabel;

    public ScoreBox() {
        scoreLabel = new Label("Score: 0");
        scoreLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");
        setStyle("-fx-background-color: black;");
        setAlignment(Pos.CENTER);
        getChildren().add(scoreLabel);
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}