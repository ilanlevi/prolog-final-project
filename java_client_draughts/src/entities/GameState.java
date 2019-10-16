package entities;

public class GameState {

    private GameSettings settings;
    private BoardTile[][] tiles;
    private Color playerToPlay;
    private boolean isDone;

    public GameState(GameSettings gameSettings) {
        this.settings = gameSettings;
        this.tiles = tools.BoardTools.generateNewBoard(gameSettings);
        playerToPlay = Color.WHITE;
        isDone = false;
    }

    public GameSettings getSettings() {
        return settings;
    }


    public BoardTile[][] getTiles() {
        return tiles;
    }

    public BoardTile getTile(int i, int j) {
        if (i > 0 && j > 0 && i < settings.getBoardSize() && j < settings.getBoardSize()) {
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
