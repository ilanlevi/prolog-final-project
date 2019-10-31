package entities;

import tools.BoardTools;

public class GameState {

    private BoardTile[][] tiles;
    private Color playerToPlay;

    public GameState(GameState toCopy) {
        this.playerToPlay = toCopy.getPlayerToPlay();
        this.tiles = toCopy.getTiles().clone();
    }

    public GameState(Color playerToPlay, BoardTile[][] tiles) {
        this.playerToPlay = playerToPlay;
        this.tiles = tiles;
    }

    public GameState(Game game) {
        this(Color.WHITE, tools.BoardTools.generateNewBoard(game.getSettings()));
    }

    public BoardTile[][] getTiles() {
        return tiles;
    }

    public BoardTile getTile(int i, int j) {
        if (i >= 0 && j >= 0 && i < Game.instance().getSettings().getBoardSize() && j < Game.instance().getSettings().getBoardSize()) {
            return tiles[i][j];
        }
        return null;
    }

    public Piece removePiece(int i, int j) {
        BoardTile t = getTile(i, j);
        if (t == null || t.isEmpty())
            return null;
        Piece piece = t.getPiece();
        t.setPiece(null);
        return piece;
    }

    public boolean movePiece(int iFrom, int jFrom, int iTo, int jTo) {
        BoardTile from = getTile(iFrom, jFrom);
        BoardTile to = getTile(iTo, jTo);
        if (from == null || to == null || from.isEmpty() || !to.isEmpty())
            return false;
        Piece p = removePiece(iFrom, jFrom);
        to.setPiece(p);
        return true;
    }

    public Color getPlayerToPlay() {
        return playerToPlay;
    }

    public GameState changePlayerToPlay() {
        playerToPlay = Color.other(playerToPlay);
        return this;
    }

    public boolean isDone() {
        return BoardTools.isDone(this);
    }
}
