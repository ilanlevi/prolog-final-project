package entities;

public class BoardTile {

    private Color tileColor;
    private Piece piece;

    public BoardTile(Color tileColor) {
        this.tileColor = tileColor;
    }

    public Color getTileColor() {
        return tileColor;
    }

    public Piece getPiece() {
        return piece;
    }

    /**
     * Set piece only if the tile color isn't white
     * @param piece
     * @return
     */
    public BoardTile setPiece(Piece piece) {
        if(this.tileColor != Color.WHITE) {
            this.piece = piece;
        }
        return this;
    }

    public boolean isEmpty(){
        return this.piece == null;
    }
}
