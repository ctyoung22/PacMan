import javafx.scene.layout.BorderPane;

public class FieldPane extends BorderPane{
    Pacman pac;
    Maze maze;

    public FieldPane(){
        pac = new Pacman();
        getChildren().add(pac);
    }

    public void updatePacPostition(double pacX, double pacY){
        pac.setTranslateX(pacX);
        pac.setTranslateY(pacY);
    }
}
