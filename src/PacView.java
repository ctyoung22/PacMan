import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class PacView extends StackPane{
    FieldPane pacPane;
    Maze maze;

    public PacView(){
        pacPane = new FieldPane();
        pacPane.setMinHeight(800);
        maze = new Maze();
        maze.setupMazeBorder();        

        HBox topStatusPane = new HBox();
        topStatusPane.setMaxHeight(25);
        Label scoreLabel = new Label("Score: 0");
        topStatusPane.getChildren().add(scoreLabel);
        topStatusPane.setStyle("-fx-background-color: #c2c2c2ff");

        HBox bottomStatusPane = new HBox();
        bottomStatusPane.setMaxHeight(25);
        Label livesLabel = new Label("Lives 3");
        bottomStatusPane.getChildren().add(livesLabel);

        getChildren().addAll(maze, pacPane,topStatusPane, bottomStatusPane);
        setAlignment(topStatusPane, Pos.TOP_CENTER);
        setAlignment(bottomStatusPane, Pos.BOTTOM_CENTER);
        setAlignment(maze, Pos.CENTER);
        maze.setTranslateY(25);
        setAlignment(pacPane, Pos.CENTER);
    }

    public void updateView(double pacX, double pacY){
        pacPane.updatePacPostition(pacX, pacY);
    }

    public FieldPane getGamePane(){
        return this.pacPane;
    }

}
