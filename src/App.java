import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        
        PacModel model = new PacModel();
        PacView view = new PacView(model.getTileSize(), model.getRowCount(), model.getColumnCount());
        PacController controller = new PacController(model, view);
        Scene scene = new Scene(view, model.getBoardWidth(), model.getBoardHeight() + 235);
        System.out.println("boardWidth=" + model.getBoardWidth() + " boardHeight=" + model.getBoardHeight());
        stage.setScene(scene);
        stage.setTitle("Pac-Man FX");
        stage.setResizable(false);
        stage.show();
        controller.startGameLoop(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


