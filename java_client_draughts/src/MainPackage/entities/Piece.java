package MainPackage.entities;

import java.util.Objects;

public class Piece {

    private Color color;
    private boolean queen;

    public Piece(Color color, boolean queen) {
        this(color);
        this.queen = queen;
    }

    public Piece clonePiece(){
        return new Piece(this.color,this.queen);
    }

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isQueen() {
        return queen;
    }

    public Piece setAsQueen() {
        // change can be only once a piece is a queen - and cannot go back
        queen = true;
        return this;
    }

    public static Piece markQueenIfNeeded(Piece piece, int row) {
        int boardSize = Game.instance().getSettings().getBoardSize();
        if (piece == null || row < 0 || row > boardSize)
            return piece;
        if (piece.getColor() == Color.WHITE && row == boardSize - 1)
            piece.setAsQueen();
        else if (piece.getColor() == Color.BLACK && row == 0)
            piece.setAsQueen();
        return piece;
    }

    public String toJson(int i, int j) {
        return "{\"i\":" + i + ",\"j\":" + j +
                ", \"color\":" + "\"" + color.getName() + "\"" + ",\"isQueen\":" + isQueen() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return queen == piece.queen &&
                color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, queen);
    }
}
