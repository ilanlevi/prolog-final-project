package gui.controllers;
/**
 * Sample Skeleton for "MainPane.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

import consts.AboutPageConst;
import entities.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import main.Main;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.exit;


public class MainFrameController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private Rectangle playerTurn;

    @FXML // fx:id="mainPane"
    private Pane mainPane; // Value injected by FXMLLoader

    @FXML
    private void handleAbout(ActionEvent event) {
        try {
            mainPane.getChildren().clear();
            mainPane.getStyleClass().add("browser");
            WebView webView = new WebView();

            webView.prefWidthProperty().bind(Main.scene.widthProperty());
            webView.prefHeightProperty().bind(Main.scene.heightProperty());
            WebEngine webEngine = webView.getEngine();
            webEngine.load(URL.class.getResource(AboutPageConst.HTML_ROUTE).toExternalForm());
            webView.applyCss();
            mainPane.getChildren().add(0, webView);
        } catch (Exception e) {
            System.err.println("About window load error!\n" + e.getMessage());
        }
    }

    @FXML
    private void handleSettings(ActionEvent event) {
        try {
            showWindow("/gui/fxml/SettingPane.fxml");
        } catch (Exception e) {
            System.err.println("Settings window load error!\n" + e.getMessage());
        }
    }

    @FXML
    private void handleUndo(ActionEvent event) {
        Game.instance().goBackState();
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'MainPane.fxml'.";
        assert playerTurn != null : "fx:id=\"playerTurn\" was not injected: check your FXML file 'MainPane.fxml'.";
        Main.playerTurn = playerTurn;
        Main.MainPane = mainPane;
        playerTurn.widthProperty().bind(mainPane.widthProperty());
        createNewGame(new ActionEvent());
    }

    public void createNewGame(ActionEvent event) {
        try {
            // TODO: 18/10/2019 Change string to constst
            showWindow("/gui/fxml/GameGrid.fxml");
            Game.instance().resetGame();
        } catch (Exception e) {
            System.err.println("Board window load error!\n" + e.getMessage());
        }
    }

    private void showWindow(String pathToScene) throws java.io.IOException {
        mainPane.getChildren().clear();
        mainPane.getChildren().add(0, FXMLLoader.load(getClass().getResource(pathToScene)));
    }

    public void showGame(ActionEvent event){
        try {
            showWindow("/gui/fxml/GameGrid.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeApp(ActionEvent event) {
        exit(0);
    }
}
