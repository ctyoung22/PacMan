import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage primaryStage) {
        PacModel model = new PacModel();
        PacView view = new PacView();
        PacController controller = new PacController(model, view);

        Scene scene = new Scene(view, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        view.requestFocus();
    }

    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
