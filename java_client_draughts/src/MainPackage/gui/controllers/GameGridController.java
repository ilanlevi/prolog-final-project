package MainPackage.gui.controllers;

import MainPackage.Main;
import MainPackage.entities.Game;
import MainPackage.gui.entities.BoardGui;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

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

    @FXML  // fx:id="GameAnchorPane"
    private AnchorPane GameAnchorPane;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert GameAnchorPane != null : "fx:id=\"GameAnchorPane\" was not injected: check your FXML file 'GameGrid.fxml'.";
        int boardSize = Game.instance().getSettings().getBoardSize();
        BoardGui grid = new BoardGui(boardSize);
        GameAnchorPane.getChildren().clear();
        GameAnchorPane.getChildren().add(grid);
        grid.cellWidthProperty().bind(Main.MainPane.widthProperty().divide(boardSize));
        grid.cellHeightProperty().bind(Main.MainPane.heightProperty().divide(boardSize));

        grid.setVisible(true);
        grid.setGridLinesVisible(true);
        grid.bindGame();
        grid.setBoard();
    }
}

