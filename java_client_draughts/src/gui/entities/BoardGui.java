package gui.entities;

import entities.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import main.Main;
import tools.BoardTools;
import tools.GameBoardTools;


public class BoardGui extends TrueGridPane {

    private Color playerToPlay;

    private int boardSize;
    private boolean isStrikeMove = false;

    private BoardTileGui[][] boardMatrix;
    private BoardTileGui clicked;

    public BoardGui(int boardSize) {
        super(boardSize, boardSize);
        this.boardSize = boardSize;
        boardMatrix = new BoardTileGui[boardSize][boardSize];
        setPlayerToPlay(Color.WHITE);
        setTiles();
        setBoard();
    }

    public void refresh() {
        setPlayerToPlay(Game.instance().getLatestGameState().getPlayerToPlay());
        setBoard();
    }

    public void bindGame(){
        Game.instance().setBoardGui(this);
    }


    /**
     * Sets the board with Checkers in accordance with the standard format of play
     */
    public void setBoard() {
        getChildren().removeIf(x -> x instanceof ImageView);
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Piece piece = Game.instance().getLatestGameState().getTile(i, j).getPiece();
                boardMatrix[i][j].setIcon(piece);
                ImageView imageView = boardMatrix[i][j].getImage();
                if (imageView != null) {
                    imageView.fitWidthProperty().bind(cellWidthProperty());
                    imageView.fitHeightProperty().bind(cellHeightProperty());
                    int finalI = i;
                    int finalJ = j;
                    imageView.setOnMouseClicked(event -> {
                        onMouseClicked(boardMatrix[finalI][finalJ]);
                        setBoard();
                    });
                    add(imageView, j, i);
                    imageView.toFront();
                }
            }
        }

    }


    /**
     * Sets the board with tiles of alternating Color as is standard
     */
    private void setTiles() {
        for (int j = 0; j < boardSize; j++) {
            for (int i = 0; i < boardSize; i++) {
                BoardTileGui tileGui = new BoardTileGui(i, j);
                tileGui.getRectangle().widthProperty().bind(cellWidthProperty());
                tileGui.getRectangle().heightProperty().bind(cellHeightProperty());

                add(tileGui.getRectangle(), j, i);

                tileGui.getRectangle().setOnMouseClicked(event -> {
                    onMouseClicked(tileGui);
                    setBoard();
                    checkIfDone();
                });
                boardMatrix[i][j] = tileGui;
            }
        }
    }

    public void checkIfDone(){
        if(BoardTools.isDone(Game.instance().getLatestGameState())){
            int index = Game.instance().numberOfMoves();
            Color playerWon = Game.instance().getMove(index - 2).getPlayerToPlay();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Game is over!\nPlayer won: " + playerWon.name() + "\nRestart game",
                    ButtonType.OK);
            alert.setHeaderText("Game over");
            alert.showAndWait();
            Game.instance().resetGame();
        }
    }

    public void onMouseClicked(BoardTileGui boardTileGui) {
        BoardTile t = Game.instance().getLatestGameState().getTile(boardTileGui.getRow(), boardTileGui.getColumn());

        if (t == null || t.getTileColor().equals(Color.WHITE)) { // clicked on white, return
            return;
        }

        if (clicked == null) {
            if (!t.isEmpty() && t.getPiece().getColor().equals(Game.instance().getLatestGameState().getPlayerToPlay())) {
                clicked = boardTileGui;
                clicked.getRectangle().setFill(javafx.scene.paint.Color.GREEN);
            }
            return;
        }

        // else - a piece was picked already

        GameState currState = Game.instance().getLatestGameState();
        GameState newState = GameBoardTools.move(currState, clicked.getRow(), clicked.getColumn(), boardTileGui.getRow(), boardTileGui.getColumn());
        if (newState != null) {
            Color old = getPlayerToPlay();
            Game.instance().addNewState(newState);
            refresh();
            if(old == getPlayerToPlay()){
                boardTileGui.getRectangle().setFill(javafx.scene.paint.Color.GREEN);
                clicked.getRectangle().setFill(BoardTileGui.rectangleColor(clicked.getRow(), clicked.getColumn()));
                clicked = boardTileGui;
                isStrikeMove = true;
                return;
            }
            isStrikeMove = false;
        }

        if(!isStrikeMove) {
            clicked.getRectangle().setFill(BoardTileGui.rectangleColor(clicked.getRow(), clicked.getColumn()));
            clicked = null;
        }
    }


    public Color getPlayerToPlay() {
        return playerToPlay;
    }

    public BoardGui setPlayerToPlay(Color playerToPlay) {
        this.playerToPlay = playerToPlay;
        Main.playerTurn.setFill(playerToPlay.equals(Color.WHITE) ? javafx.scene.paint.Color.WHITE : javafx.scene.paint.Color.BLACK);
        return this;
    }
}

;