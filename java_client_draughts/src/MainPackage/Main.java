package MainPackage;

import MainPackage.consts.PagesPathConst;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Scene scene;
    public static Pane MainPane;
    public static Rectangle playerTurn;

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(PagesPathConst.GAME_BOARD_PATH));
        stage.setTitle("Draughts");
        scene = new Scene(root, 900, 750);
        stage.setScene(scene);
        stage.show();
    }
}
