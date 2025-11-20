import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

    public void start(Stage primaryStage){
        PacModel model = new PacModel();
        PacView view = new PacView();
        PacController controller = new PacController(model, view);

        Scene mainScene = new Scene(view, 760, 900);
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        view.requestFocus();
    }
    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
