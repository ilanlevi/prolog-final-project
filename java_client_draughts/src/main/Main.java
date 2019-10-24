package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Scene scene;
    public static Pane MainPane;
//    private ArrayList

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/fxml/MainPane.fxml"));
        stage.setTitle("Draughts");
        scene = new Scene(root, 900, 750);
        stage.setScene(scene);
        stage.show();
    }
}
