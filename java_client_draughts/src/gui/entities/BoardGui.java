package gui.entities;

import entities.*;
import javafx.scene.image.ImageView;
import tools.GameBoardTools;


public class BoardGui extends TrueGridPane {

    private Color playerToPlay;

    private int boardSize;

    private BoardTileGui[][] boardMatrix;
    private BoardTileGui clicked;

    public BoardGui(int boardSize) {
        super(boardSize, boardSize);
        this.boardSize = boardSize;
        playerToPlay = Color.WHITE;
        boardMatrix = new BoardTileGui[boardSize][boardSize];
        setTiles();
        setBoard();
    }

    public void refresh() {
        playerToPlay = Game.instance().getLatestGameState().getPlayerToPlay();
        setBoard();
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
                });
                boardMatrix[i][j] = tileGui;
            }
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
            } else {
                // reset click
                clicked.getRectangle().setFill(BoardTileGui.rectangleColor(clicked.getRow(), clicked.getColumn()));
                clicked = null;
            }
            return;
        }

        // else - a piece was picked already

        GameState currState = Game.instance().getLatestGameState();
        GameState newState = GameBoardTools.move(currState, clicked.getRow(), clicked.getColumn(), boardTileGui.getRow(), boardTileGui.getColumn());
        if (newState != null) {
            Game.instance().addNewState(newState);
            refresh();
        }


        clicked.getRectangle().setFill(BoardTileGui.rectangleColor(clicked.getRow(), clicked.getColumn()));
        clicked = null;


    }


    public Color getPlayerToPlay() {
        return playerToPlay;
    }

    public BoardGui setPlayerToPlay(Color playerToPlay) {
        this.playerToPlay = playerToPlay;
        return this;
    }
}

;