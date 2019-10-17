package gui.controllers;

/**
 * Sample Skeleton for "RestartGameAlert.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;


public class RestartGameAlertController extends Alert {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="actionParent"
    private HBox actionParent; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="detailsLabel"
    private Label detailsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="messageLabel"
    private Label messageLabel; // Value injected by FXMLLoader

    @FXML // fx:id="okButton"
    private Button okButton; // Value injected by FXMLLoader

    @FXML // fx:id="okParent"
    private HBox okParent; // Value injected by FXMLLoader

    private ButtonType selected;

    public RestartGameAlertController(ButtonType selected) {
        super(AlertType.CONFIRMATION);
        this.selected = selected;
    }


    @FXML protected void handleCancelButton(ActionEvent event) {
        this.selected = ButtonType.CANCEL;
    }

    @FXML protected void handleOkButton(ActionEvent event) {
        this.selected = ButtonType.APPLY;
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert actionParent != null : "fx:id=\"actionParent\" was not injected: check your FXML file 'RestartGameAlert.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'RestartGameAlert.fxml'.";
        assert detailsLabel != null : "fx:id=\"detailsLabel\" was not injected: check your FXML file 'RestartGameAlert.fxml'.";
        assert messageLabel != null : "fx:id=\"messageLabel\" was not injected: check your FXML file 'RestartGameAlert.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'RestartGameAlert.fxml'.";
        assert okParent != null : "fx:id=\"okParent\" was not injected: check your FXML file 'RestartGameAlert.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected

    }

}
