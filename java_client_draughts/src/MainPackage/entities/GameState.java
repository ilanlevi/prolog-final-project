package MainPackage.entities;

import MainPackage.tools.BoardTools;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameState {

    private BoardTile[][] tiles;
    private Color playerToPlay;

    public GameState(GameState toCopy) {
        this.playerToPlay = toCopy.getPlayerToPlay();
        int length = toCopy.tiles.length;
        this.tiles = new BoardTile[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                BoardTile boardTileToCopy = toCopy.tiles[i][j];
                Piece piece = boardTileToCopy.getPiece();
                tiles[i][j] = new BoardTile(boardTileToCopy.getTileColor(), piece == null ? null : piece.clonePiece());
            }
        }
    }

    public GameState(Color playerToPlay, BoardTile[][] tiles) {
        this.playerToPlay = playerToPlay;
        this.tiles = tiles;
    }

    public GameState(Game game) {
        this(Color.BLACK, MainPackage.tools.BoardTools.generateNewBoard(game.getSettings()));
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

    protected void setPiece(int i, int j, Piece piece) {
        BoardTile from = getTile(i, j);
        if (from == null)
            return;
        from.setPiece(piece);
    }

    public void markNewQueensIfNeeded() {
        int boardSize = Game.instance().getSettings().getBoardSize();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Piece p = getTile(i, j).getPiece();
                if (p != null)
                    Piece.markQueenIfNeeded(p, i);
            }
        }
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

    public static GameState fromJsonArray(GameState last, String newState) {
        int boardSize = Game.instance().getSettings().getBoardSize();

        GameState newGameState = new GameState(last);
        newGameState.changePlayerToPlay();

        // remove all pieces
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                newGameState.removePiece(i, j);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List pieces = null;
        try {
            pieces = objectMapper.readValue(newState, List.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        for (Object piece : pieces) {
            int i = (int) ((Map) piece).get("i");
            int j = (int) ((Map) piece).get("j");
            Color color = Color.fromString((String) ((Map) piece).get("color"));
            boolean isQueen = Boolean.parseBoolean((String) ((Map) piece).get("isQueen"));
            Piece pieceObject = new Piece(color, isQueen);
            newGameState.setPiece(i, j, pieceObject);
        }

        return newGameState;

    }

    @Override
    public String toString() {
        int boardSize = Game.instance().getSettings().getBoardSize();
        ArrayList<String> pieces = new ArrayList<String>();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                BoardTile t = getTile(i, j);
                if (!t.isEmpty()) {
                    pieces.add(t.getPiece().toJson(i, j));
                }
            }
        }

        return "{\"state\": "
                + pieces.toString() +
                ",\"settings\":{"
                + "\"level\": " + Game.instance().getSettings().getLevel() + "," +
                "\"rows\": " + boardSize +
                "}" +
                "}";
    }
}
