package gui.controllers;
/**
 * Sample Skeleton for "MainPane.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

import java.net.URL;
        import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class MainFrameController  {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="mainPane"
    private Pane mainPane; // Value injected by FXMLLoader

    @FXML
    private void handleAbout(ActionEvent event){
        try {
            mainPane.getChildren().set(0, FXMLLoader.load(getClass().getResource("/gui/fxml/AboutPane.fxml")));
        }catch (Exception e){
            System.err.println("About window load error!\n" + e.getMessage());
        }
    }

    @FXML
    private void handleSettings(ActionEvent event) {
        try {
            mainPane.getChildren().set(0, FXMLLoader.load(getClass().getResource("/gui/fxml/SettingPane.fxml")));
        } catch (Exception e) {
            System.err.println("Settings window load error!\n" + e.getMessage());
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'MainPane.fxml'.";
        mainPane.autosize();
        mainPane.getChildren().add(new Pane());
        // Initialize your logic here: all @FXML variables will have been injected

    }

}
