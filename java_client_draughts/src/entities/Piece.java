package entities;

public class Piece {

    private Color color;
    private boolean queen;

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


}
