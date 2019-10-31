package gui;

/**
 * Sample Skeleton for "AboutPane.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public class AboutPaneController extends Pane {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="AboutPaneFx"
    private TextArea AboutPaneFx; // Value injected by FXMLLoader

    @FXML // fx:id="AboutProjectPane"
    private AnchorPane AboutProjectPane; // Value injected by FXMLLoader


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert AboutPaneFx != null : "fx:id=\"AboutPaneFx\" was not injected: check your FXML file 'AboutPane.fxml'.";
        assert AboutProjectPane != null : "fx:id=\"AboutProjectPane\" was not injected: check your FXML file 'AboutPane.fxml'.";
        // Initialize your logic here: all @FXML variables will have been injected

        // TODO: 31/10/2019 add comments about game rules and assumptions

    }

}
