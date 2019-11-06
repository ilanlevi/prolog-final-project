package MainPackage.entities;

import java.util.Objects;

public class BoardTile {

    private Color tileColor;
    private Piece piece = null;

    public BoardTile(Color tileColor) {
        this.tileColor = tileColor;
    }

    public BoardTile(Color tileColor, Piece piece) {
        this.tileColor = tileColor;
        this.piece = piece;
    }

    public Color getTileColor() {
        return tileColor;
    }

    public Piece getPiece() {
        return piece;
    }

    /**
     * Set piece only if the tile color isn't white
     *
     * @param piece piece to set
     * @return this
     */
    public BoardTile setPiece(Piece piece) {
        if (this.tileColor != Color.WHITE) {
            this.piece = piece;
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardTile boardTile = (BoardTile) o;
        return tileColor == boardTile.tileColor &&
                Objects.equals(piece, boardTile.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tileColor, piece);
    }

    public boolean isEmpty() {
        return this.piece == null;
    }
}
