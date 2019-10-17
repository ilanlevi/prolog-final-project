package entities;

import tools.BoardTools;

public class GameState {

    private BoardTile[][] tiles;
    private Color playerToPlay;
    private boolean isDone;
    private int moveNumber;

    public GameState(int moveNumber, Color playerToPlay, BoardTile[][] tiles) {
        this.playerToPlay = playerToPlay;
        this.tiles = tiles;
        this.moveNumber = moveNumber;
        isDone = BoardTools.isDone(this);
    }

    public GameState(Game game){
        this(0, Color.WHITE,  tools.BoardTools.generateNewBoard(Game.instance().getSettings()));
    }

    public BoardTile[][] getTiles() {
        return tiles;
    }

    public BoardTile getTile(int i, int j) {
        if (i > 0 && j > 0 && i < Game.instance().getSettings().getBoardSize() && j < Game.instance().getSettings().getBoardSize()) {
            return tiles[i][j];
        }
        return null;
    }

    public Color getPlayerToPlay() {
        return playerToPlay;
    }

    public boolean isDone() {
        return isDone;
    }
}
