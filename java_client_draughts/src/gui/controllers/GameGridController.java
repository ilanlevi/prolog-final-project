package gui.controllers;

import entities.Game;
import gui.entities.BoardGui;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Sample Skeleton for "GameGrid.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

public class GameGridController extends AnchorPane {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private BoardGui grid; // Value injected by FXMLLoader

    @FXML  // fx:id="GameAnchorPane"
    private AnchorPane GameAnchorPane;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert GameAnchorPane != null : "fx:id=\"GameAnchorPane\" was not injected: check your FXML file 'GameGrid.fxml'.";
        // Initialize your logic here: all @FXML variables will have been injected
        int boardSize = Game.instance().getSettings().getBoardSize();
        grid = new BoardGui(boardSize);
        GameAnchorPane.getChildren().clear();
        GameAnchorPane.getChildren().add(grid);
//        System.out.println("GameAnchorPane: width = " + GameAnchorPane.getWidth() + " height = " + GameAnchorPane.getHeight());
        grid.cellWidthProperty().bind(Main.MainPane.widthProperty().divide(boardSize));
        grid.cellHeightProperty().bind(Main.MainPane.heightProperty().divide(boardSize));

        grid.setVisible(true);
        grid.setGridLinesVisible(true);
        grid.bindGame();
        grid.setBoard();


    }



}

