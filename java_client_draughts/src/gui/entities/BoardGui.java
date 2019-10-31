package gui.entities;

import entities.BoardTile;
import entities.Color;
import entities.Game;
import entities.Piece;
import javafx.scene.image.ImageView;


/**
 * A robust implementation of a GridPane containing cells of uniform dimensions
 * That is to say at every cell (i,j) the dimensions are x, y
 * This implementation has the advtange of:
 * Automatically initialzing the grid from the constructor
 * Automatic binding to the containing Scene for easy resizing
 * The ability to quickly add and remove nodes using the grid's coordinates as a parameter
 */
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
            System.out.println("blabla2");
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
        if(t.isEmpty() && )

//        clicked.getRectangle().setFill(BoardTileGui.rectangleColor(clicked.getRow(), clicked.getColumn()));
    }


//    public void onMouseClicked(Rectangle rectangle, int i, int j) {
//        BoardTile tile = Game.instance().getLatestGameState().getTile(i, j);
//
//        if (clicked1i == -1 && clicked1j == -1) { // first move
//            if (!tile.isEmpty()
//                    && tile.getPiece().getColor().equals(Game.instance().getLatestGameState().getPlayerToPlay())) {
//                clicked1i = i;
//                clicked1j = j;
//                rectangle.setFill(javafx.scene.paint.Color.GREEN);
//            }
//            return;
//        }
//
//        // second move
//        if (!tile.isEmpty() && tile.getTileColor().equals(Color.WHITE)) {
//            // not empty - reset values
//            getChildren().filtered(n -> n instanceof Rectangle).forEach(x -> {
//                int row = getRowIndex(x);
//                int col = getColumnIndex(x);
//                ((Rectangle) x).setFill(rectangleColor(row, col));
//            });
//            clicked1i
//                    = -1;
//            clicked1j = -1;
//        } else { // clicked on empty tile
//            GameState newGameState = new GameState(Game.instance().getLatestGameState());
//            BoardTile oldTile = newGameState.getTile(clicked1i, clicked1j);
//            getChildren().
//                    filtered(n -> n instanceof Rectangle && getColumnIndex(n) == clicked1j && getRowIndex(n) == clicked1i)
//                    .forEach(x -> ((Rectangle) x).setFill(rectangleColor(clicked1i, clicked1j)));
//            Piece newPiece = oldTile.getPiece();
//            Piece.markQueenIfNeeded(newPiece, i);
//            oldTile.setPiece(null);
//            newGameState.getTile(i, j).setPiece(newPiece);
//            // todo: check if ate other player + add multi eating
//            Game.instance().addNewState(newGameState);
//        }
//    }

    public Color getPlayerToPlay() {
        return playerToPlay;
    }

    public BoardGui setPlayerToPlay(Color playerToPlay) {
        this.playerToPlay = playerToPlay;
        return this;
    }
}

;