
/**
 * Sample Skeleton for "SettingPane.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package gui.controllers;

import consts.ErrorMessageConst;
import entities.Game;
import entities.GameSettings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;


public class SettingPaneController extends Pane {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private VBox MainPane;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="BoardSizeText"
    private TextField BoardSizeText; // Value injected by FXMLLoader

    @FXML // fx:id="ErrorMsgText"
    private Text ErrorMsgText; // Value injected by FXMLLoader

    @FXML // fx:id="LevelText"
    private TextField LevelText; // Value injected by FXMLLoader

    @FXML // fx:id="PawnsLinesText"
    private TextField PawnsLinesText; // Value injected by FXMLLoader

    @FXML // fx:id="SetButton"
    private Button SetButton; // Value injected by FXMLLoader

    private GameSettings settings = new GameSettings();


    @FXML
    private void handleSet() {
        ErrorMsgText.setText(ErrorMessageConst.EMPTY_MESSAGE);

        if (!validateTextIsNumber(LevelText.getText()) ||
                !validateTextIsNumber(BoardSizeText.getText()) ||
                !validateTextIsNumber(PawnsLinesText.getText())) {
            ErrorMsgText.setText(ErrorMessageConst.NOT_A_NUMBER_MESSAGE);
            return;
        }


        int level = Integer.parseInt(LevelText.getText());
        int boardSize = Integer.parseInt(BoardSizeText.getText());
        int linesOfPawns = Integer.parseInt(PawnsLinesText.getText());


        if (!settings.isLevelValid(level)) {
            ErrorMsgText.setText(ErrorMessageConst.LEVEL_ERROR_MESSAGE);
            return;
        } else {
            settings.setLevel(level);
        }

        if (!settings.isBoardSizeValid(boardSize)) {
            ErrorMsgText.setText(ErrorMessageConst.BOARD_SIZE_ERROR_MESSAGE);
            return;
        } else {
            settings.setBoardSize(boardSize);
        }

        if (!settings.isStartingLinesPawnsValid(linesOfPawns)) {
            ErrorMsgText.setText(ErrorMessageConst.PAWN_LINES_ERROR_MESSAGE);
            return;
        } else {
            settings.setStartingLinesPawns(linesOfPawns);
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "New settings means that the game will restart", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Are you sure?");
        alert.showAndWait();

        if (alert.getResult().equals(ButtonType.YES)) {
            Game.instance().setSettings(settings);
            Game.instance().resetGame();
        }
        System.out.println(settings);
    }


    private boolean validateTextIsNumber(String text) {
        return (!text.isEmpty() && text.matches("[0-9]*"));
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert BoardSizeText != null : "fx:id=\"BoardSizeText\" was not injected: check your FXML file 'SettingPane.fxml'.";
        assert ErrorMsgText != null : "fx:id=\"ErrorMsgText\" was not injected: check your FXML file 'SettingPane.fxml'.";
        assert LevelText != null : "fx:id=\"LevelText\" was not injected: check your FXML file 'SettingPane.fxml'.";
        assert PawnsLinesText != null : "fx:id=\"PawnsLinesText\" was not injected: check your FXML file 'SettingPane.fxml'.";
        assert SetButton != null : "fx:id=\"SetButton\" was not injected: check your FXML file 'SettingPane.fxml'.";
        assert MainPane != null : "fx:id=\"MainPane\" was not injected: check your FXML file 'SettingPane.fxml'.";

        MainPane.prefWidthProperty().bind(Main.scene.widthProperty());
        MainPane.prefHeightProperty().bind(Main.scene.heightProperty());

        // Initialize your logic here: all @FXML variables will have been injected
        LevelText.setText(Game.instance().getSettings().getLevel() + "");
        BoardSizeText.setText(Game.instance().getSettings().getBoardSize() + "");
        PawnsLinesText.setText(Game.instance().getSettings().getStartingLinesPawns() + "");
    }

}

