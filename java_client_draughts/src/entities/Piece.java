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


}
