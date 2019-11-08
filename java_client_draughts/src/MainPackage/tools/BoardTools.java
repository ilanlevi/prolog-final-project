package MainPackage.tools;

import MainPackage.entities.*;

public class BoardTools {

    /**
     * Generate a new board for the given settings.
     *
     * @param gameSettings not null game settings
     * @return a new game board
     * @see GameSettings
     */
    public static BoardTile[][] generateNewBoard(GameSettings gameSettings) {
        BoardTile[][] boardTiles = new BoardTile[gameSettings.getBoardSize()][gameSettings.getBoardSize()];

        int whitePlayerTiles = getInitializeTilesNumberForBoard(gameSettings); // get number of empty tiles
        int emptyTiles = (gameSettings.getBoardSize() - gameSettings.getStartingLinesPawns() * 2) * gameSettings.getBoardSize() / 2; // get number of whitePlayerTiles

        for (int i = 0; i < gameSettings.getBoardSize(); i++) {
            for (int j = 0; j < gameSettings.getBoardSize(); j++) {
                BoardTile tile;
                if ((i + j) % 2 == 0) { // white tile cannot have a pawn
                    tile = new BoardTile(Color.WHITE);
                }
                // black tile
                else {
                    if (whitePlayerTiles > 0) {
                        --whitePlayerTiles;
                        tile = new BoardTile(Color.BLACK).setPiece(new Piece(Color.WHITE)); // set a white pawn
                    } else {
                        if (emptyTiles > 0) {
                            --emptyTiles; // no pawn
                            tile = new BoardTile(Color.BLACK); // set an empty tile
                        } else {
                            tile = new BoardTile(Color.BLACK).setPiece(new Piece(Color.BLACK)); // set a black pawnate suitable tile
                        }
                    }
                }
                boardTiles[i][j] = tile;
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
        return gameSettings.getStartingLinesPawns() * gameSettings.getBoardSize() / 2;
    }


    /**
     * Return if the game is done.
     *
     * @param state the game state to check
     * @return true if done, false otherwise
     */
    public static boolean isDone(GameState state) {
        return isDoneNoPieces(state) || isDoneCannotMove(state);
    }

    /**
     * Return if the game is done. (one of the player has not pawns on the board)
     *
     * @param state the game state to check
     * @return true if done, false otherwise
     */
    public static boolean isDoneNoPieces(GameState state) {
        int blackPawns = 0;
        int whitePawns = 0;

        int length = Game.instance().getSettings().getBoardSize();
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

    /**
     * Return if the game is done. (one of the player cannot move any where)
     *
     * @param state the game state to check
     * @return true if done, false otherwise
     */
    public static boolean isDoneCannotMove(GameState state) {
        // TODO: 08/11/2019 Fix this shit
        int blackPawns = 0;
        int whitePawns = 0;
        int[][] possibleMoves = {{-2, -2}, {-2, 2}, {2, -2}, {2, 2}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}}; // all possible moves

        int length = Game.instance().getSettings().getBoardSize();
        // go over board
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                BoardTile t = state.getTile(i, j);

                if (!t.isEmpty()) { // if the tile has a pawn
                    Color c = t.getPiece().getColor();
                    // create all possible moves
                    for (int[] possibleMove : possibleMoves) {
                        // try to move
                        GameState gameState = GameBoardTools.move(state, i, j, i + possibleMove[0], j + possibleMove[1]);
                        if (gameState != null) {
                            // move is valid
                            if (c == Color.WHITE) {
                                whitePawns++;
                            } else {
                                blackPawns++;
                            }
                        }
                        if (blackPawns > 0 && whitePawns > 0) { // both player's has at least 1 pawn that can move, stop the loop
                            return false;
                        }

                    }
                }
            }
        }
        return true;

    }


}


