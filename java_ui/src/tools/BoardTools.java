package tools;

import entities.*;

public class BoardTools {

    public static BoardTile[][] generateNewBoard(GameSettings gameSettings) {
        BoardTile[][] boardTiles = new BoardTile[gameSettings.getBoardSize()][gameSettings.getBoardSize()];

        int whitePlayerTiles = (gameSettings.getBoardSize() / 2 - 1) * (gameSettings.getBoardSize() / 2);
        int emptyTiles = gameSettings.getBoardSize();

        for (int i = 0; i < gameSettings.getBoardSize(); i++) {
            for (int j = 0; j < gameSettings.getBoardSize(); j++) {
                boolean isWhiteTiles = ((j % 2) == 0);
                if (isWhiteTiles) {
                    boardTiles[i][j] = new BoardTile(Color.WHITE);
                } else { // black tile
                    BoardTile tile = new BoardTile(Color.BLACK);
                    if (whitePlayerTiles > 0) {
                        tile.setPiece(new Piece(Color.WHITE));
                        whitePlayerTiles--;
                    } else {
                        if (emptyTiles > 0) {
                            emptyTiles--;
                        } else {
                            tile.setPiece(new Piece(Color.BLACK));
                        }
                    }
                    boardTiles[i][j] = tile;
                }
            }
        }
        return boardTiles;
    }

    public static boolean isDone(GameState state) {
        int black = 0;
        int white = 0;

        int length = state.getSettings().getBoardSize();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                BoardTile t = state.getTile(i, j);
                if (!t.isEmpty()) {
                    if (t.getPiece().getColor().equals(Color.BLACK)) {
                        black++;
                    } else {
                        white++;
                    }
                    if (black > 0 && white > 0)
                        return false;
                }
            }
        }
        return true;
    }

}


