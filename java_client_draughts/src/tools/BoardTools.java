package tools;

import entities.*;

public class BoardTools {

    /**
     * Generate a new board for the given settings. Uses {@link BoardTools#generateNewTile(int, Integer, Integer)}
     *
     * @param gameSettings not null game settings
     * @return a new game board
     * @see GameSettings
     */
    public static BoardTile[][] generateNewBoard(GameSettings gameSettings) {
        BoardTile[][] boardTiles = new BoardTile[gameSettings.getBoardSize()][gameSettings.getBoardSize()];

        Integer whitePlayerTiles = getInitializeTilesNumberForBoard(gameSettings); // get number of whitePlayerTiles
        Integer emptyTiles = gameSettings.getBoardSize() - gameSettings.getStartingLinesPawns() * 2; // get number of empty tiles

        for (int i = 0; i < gameSettings.getBoardSize(); i++) {
            for (int j = 0; j < gameSettings.getBoardSize(); j++) {
                boardTiles[i][j] = generateNewTile(i + j, emptyTiles, whitePlayerTiles); // create suitable tile
            }
        }
        return boardTiles;
    }


    /**
     * Calculates and return the number of pawns for each player at a new game.
     *
     * @param gameSettings not null game settings
     * @return number of paws for each player.
     * @see GameSettings
     */
    public static int getInitializeTilesNumberForBoard(GameSettings gameSettings) {
        return gameSettings.getStartingLinesPawns() * gameSettings.getBoardSize();
    }
    
    /**
     * Create suitable tile: White or black.
     *      The black tile may be empty or below a black/white pawn, depending on params value.
     *
     * @param index the index of the tile - when even number the tile will be white, or else black.
     * @param whitePlayerTilesLeft - only on black tile: if bigger then 0, add a white pawn and decreases by 1.
     * @param emptyTilesLeft - only on black tile: if bigger then 0, remain empty and decreases by 1.
     *                       Else (whitePlayerTilesLeft <= 0 & emptyTilesLeft  <= 0), add a black pawn.
     * @return new suitable tile
     */
    public static BoardTile generateNewTile(int index, Integer emptyTilesLeft, Integer whitePlayerTilesLeft) {
        if (index % 2 == 0) { // white tile cannot have a pawn
            return new BoardTile(Color.WHITE);
        }

        // black tile
        if (whitePlayerTilesLeft > 0) {
            --whitePlayerTilesLeft;
            return new BoardTile(Color.BLACK).setPiece(new Piece(Color.WHITE)); // set a white pawn
        }

        if (emptyTilesLeft > 0) {
            --emptyTilesLeft; // no pawn
            return new BoardTile(Color.BLACK); // set an empty tile
        }
        return new BoardTile(Color.BLACK).setPiece(new Piece(Color.BLACK)); // set a black pawn
    }

    /**
     * Return if the game is done. (one of the player has not pawns on the board)
     * @param state the game state to check
     * @return true if done, false otherwise
     */
    public static boolean isDone(GameState state) {
        int blackPawns = 0;
        int whitePawns = 0;

        int length = state.getSettings().getBoardSize();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {

                BoardTile t = state.getTile(i, j);
                if (!t.isEmpty()) { // if the tile has a pawn
                    if (t.getPiece().getColor().equals(Color.BLACK)) {
                        blackPawns++;
                    } else {
                        whitePawns++;
                    }
                    if (blackPawns > 0 && whitePawns > 0) { // both player's has at least 1 pawn, stop the loop
                        return false;
                    }
                }
            }
        }
        return true;
    }

}


