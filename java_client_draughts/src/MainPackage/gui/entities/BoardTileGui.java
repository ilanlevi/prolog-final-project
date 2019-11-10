package MainPackage.gui.entities;

import MainPackage.entities.Piece;
import MainPackage.tools.IconTools;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Similar to {@link MainPackage.entities.BoardTile} just for gui
 */
public class BoardTileGui {
    private Rectangle rectangle;
    private ImageView imageView;
    private int row, column;

    public BoardTileGui(int row, int column) {
        this.row = row;
        this.column = column;
        rectangle = new Rectangle();
        rectangle.setFill(rectangleColor(row, column));
    }

    public BoardTileGui setIcon(Piece piece) {
        if (piece == null) {
            imageView = null;
            return this;
        }
        Image image = null;
        try {
            image = new Image(IconTools.getIconForPiece(piece));
            imageView = new ImageView(image);
        } catch (Exception e) {
            System.err.println("Error while creating image!\n" + e.getMessage());
            imageView = null;
        }
        return this;
    }

    public static Color rectangleColor(int i, int j) {
        if ((i + j) % 2 == 0)
            return Color.MOCCASIN;
        return Color.MAROON;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public BoardTileGui setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
        return this;
    }

    public ImageView getImage() {
        return imageView;
    }

    public BoardTileGui setImage(ImageView imageView) {
        this.imageView = imageView;
        return this;
    }

    public int getRow() {
        return row;
    }

    public BoardTileGui setRow(int row) {
        this.row = row;
        return this;
    }

    public int getColumn() {
        return column;
    }

    public BoardTileGui setColumn(int column) {
        this.column = column;
        return this;
    }
}
