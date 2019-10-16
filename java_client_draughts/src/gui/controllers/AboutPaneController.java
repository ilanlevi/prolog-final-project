package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class AboutPaneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AboutProjectPane;


    @FXML
    void initialize() {
        assert AboutProjectPane != null : "fx:id=\"AboutProjectPane\" was not injected: check your FXML file 'AboutPane.fxml'.";


    }

}
