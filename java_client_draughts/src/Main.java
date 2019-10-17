import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

//    private ArrayList

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gui/fxml/MainPane.fxml"));

        stage.setTitle("Draughts");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }
}
