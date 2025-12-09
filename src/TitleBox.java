import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TitleBox extends HBox {

    Label scoreLabel;

    // Constructor
    public TitleBox(int tileSize, int columnCount) {
        Image titleImage = new Image(getClass().getResource("/Spr_Assets/PacManFX_Title.png").toExternalForm());
        ImageView titleView = new ImageView(titleImage);
        titleView.setFitWidth(tileSize * columnCount);
        titleView.setFitHeight(200);
        titleView.setPreserveRatio(true);
        setStyle("-fx-background-color: black;");

        getChildren().add(titleView);
        setAlignment(Pos.CENTER);
    }

    // Update the score display
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    // Getter
    public Label getScoreLabel() {
        return scoreLabel;
    }


}