


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        
        PacController controller = new PacController();
        Scene scene = new Scene(controller.getRoot(), controller.getBoardWidth(), controller.getBoardHeight());
        System.out.println("boardWidth=" + controller.getBoardWidth() + " boardHeight=" + controller.getBoardHeight());
        stage.setScene(scene);
        stage.setTitle("Pac-Man FX");
        stage.show();
        controller.startGameLoop(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


