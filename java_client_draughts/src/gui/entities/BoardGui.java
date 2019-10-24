package gui.entities;

import entities.*;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.shape.Rectangle;
import tools.IconTools;


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
    private DoubleBinding radiusBinding;

    private int Clicked1i = -1, Clicked1j = -1;
    private int Clicked2i = -1, Clicked2j = -1;

    public BoardGui(int boardSize) {
        super(boardSize, boardSize);
        this.boardSize = boardSize;
        playerToPlay = Color.WHITE;
        setTiles();
//        setBoard();
    }


    /**
     * Sets the board with Checkers in accordance with the standard format of play
     */
    public void setBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Piece piece = Game.instance().getLatestGameState().getTile(i, j).getPiece();
                setIcon(piece, i, j);
            }
        }
    }

    private void setIcon(Piece piece, int i, int j) {
        getChildren()
                .removeIf(node -> (getRowIndex(node) == i) && (getColumnIndex(node) == j) && (node instanceof ImageView));
        if (piece == null)
            return;
        Image image = null;
        try {
            image = new Image(IconTools.getIconForPiece(piece));
            ImageView imageView = new ImageView(image);
            imageView.fitWidthProperty().bind(cellWidthProperty());
            imageView.fitHeightProperty().bind(cellHeightProperty());
            add(imageView, j, i);
            imageView.toFront();
        } catch (Exception e) {
            System.err.println("Error while creating image!\n" + e.getMessage());
            return;
        }
//
//        image.toFront();
//        image.setOnMouseClicked(event -> getChildren().forEach(x -> {
//                    if ((getRowIndex(x) == i) && (getColumnIndex(x) == j) && (x instanceof ImageView))
//                        x.setBlendMode(BlendMode.BLUE);
//                }
//        ));
//        image.toFront();
//        image.toBack();
    }


//        for (Checker checker : (List<Checker>) ((List) getChildren().filtered((x) -> x.getClass() == Checker.class))) {
//            setHalignment(checker, HPos.CENTER);
//            setValignment(checker, VPos.CENTER);
//            radiusBinding = (DoubleBinding) new When(cellWidthProperty().lessThan(cellHeightProperty()))
//                    .then(cellWidthProperty().multiply(.5 * .8))
//                    .otherwise(cellHeightProperty().multiply(.5 * .8)
//                    );
//            //bind the radius of the checker to .8 * width of it's container
//            checker.radiusProperty().bind(radiusBinding);
//        }
//
//    }

    /**
     * Sets the board with tiles of alternating Color as is standard
     */
    private void setTiles() {
        for (int j = 0; j < boardSize; j++) {
            for (int i = 0; i < boardSize; i++) {
                Rectangle tmp = new Rectangle();
                tmp.widthProperty().bind(cellWidthProperty());
                tmp.heightProperty().bind(cellHeightProperty());
                if ((i + j) % 2 == 0) {
                    tmp.setFill(javafx.scene.paint.Color.MOCCASIN);
                } else {
                    tmp.setFill(javafx.scene.paint.Color.MAROON);
                }

                add(tmp, j, i);
                int finalI = i;
                int finalJ = j;
                tmp.setOnMouseClicked(event -> {
                    onMouseClicked(tmp, finalI, finalJ);
                    setBoard();
                });
                tmp.toBack();
            }
        }
    }


    public static javafx.scene.paint.Color rectangleColor(int i, int j) {
        if ((i + j) % 2 == 0)
            return javafx.scene.paint.Color.MOCCASIN;
        return javafx.scene.paint.Color.MAROON;
    }

    public void onMouseClicked(Rectangle rectangle, int i, int j) {
        BoardTile tile = Game.instance().getLatestGameState().getTile(i, j);

        if (Clicked1i == -1 && Clicked1j == -1) { // first move
            if(!tile.isEmpty() && tile.getPiece().getColor().equals(Game.instance().getLatestGameState().getPlayerToPlay())){
                Clicked1i = i;
                Clicked1j = j;
                rectangle.setFill(javafx.scene.paint.Color.GREEN);
            }
            return;
        }

        // second move
        if (!tile.isEmpty()) {
            // not empty - reset values
                getChildren().filtered(n -> n instanceof Rectangle).forEach(x -> {
                    int row = getRowIndex(x);
                    int col = getColumnIndex(x);
                    ((Rectangle) x).setFill(rectangleColor(row, col));
                });
                Clicked1i = -1;
                Clicked1j = -1;
        } else { // clicked on empty tile
            GameState newGameState = new GameState(Game.instance().getLatestGameState());
            BoardTile oldTile = newGameState.getTile(Clicked1i, Clicked1j);
            Piece newPiece = oldTile.getPiece();
            Piece.markQueenIfNeeded(newPiece, i);
            oldTile.setPiece(null);
            newGameState.getTile(i, j).setPiece(newPiece);
            // todo: check if ate other player + add multi eating
            Game.instance().addNewState(newGameState);
        }
    }

        public Color getPlayerToPlay () {
            return playerToPlay;
        }

        public BoardGui setPlayerToPlay (Color playerToPlay){
            this.playerToPlay = playerToPlay;
            return this;
        }
    }

