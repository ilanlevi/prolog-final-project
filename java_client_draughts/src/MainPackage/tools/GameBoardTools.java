package MainPackage.tools;

import MainPackage.entities.*;

public class GameBoardTools {

    /**
     * Creates new GameState after a piece move from (i, j) to (newI, newJ).
     * If a 'streak' is possible, won't switch players.
     * @param gameStateOld the old game state
     * @param i x index of piece
     * @param j y index of piece
     * @param newI new x index
     * @param newJ new y index
     * @param checkPlayer check playing player (for checking if game is done)
     * @return The new state, if the move was invalid return null.
     */
    public static GameState move(GameState gameStateOld, int i, int j, int newI, int newJ, boolean checkPlayer) {
        // check if valid
        if (!isValidMove(gameStateOld, i, j, newI, newJ, checkPlayer))
            return null;
        GameState gameState = new GameState(gameStateOld);
        Piece piece = gameState.getTile(i, j).getPiece();
        if (!isStrikeMove()) {
            if (Math.abs(i - newI) == 1 && Math.abs(j - newJ) == 1) { // normal move, didn't eat
                if (!piece.isQueen() && pieceGotBack(piece.getColor(), i, newI)) {
                    // only queen can go back
                    return null;
                }
                gameState.changePlayerToPlay();
                if (!gameState.movePiece(i, j, newI, newJ))
                    return null;
                gameState.markNewQueensIfNeeded();
                return gameState;
            }
        }
        // eating
        if (canEat(gameStateOld, i, j, newI, newJ, true)) {
            gameState.removePiece((i + newI) / 2, (j + newJ) / 2);
            gameState.movePiece(i, j, newI, newJ);
            if (!canDoStrike(gameState, newI, newJ)) { // cannot do strike
                gameState.markNewQueensIfNeeded();
                gameState.changePlayerToPlay();
            }
            return gameState;
        }

        return null;
    }

    /**
     * Returns if current state is a strike. (it will have the same player as the state before)
     * @return true if it is strike, false otherwise.
     */
    public static boolean isStrikeMove() {
        int numberOfMoves = Game.instance().numberOfMoves();
        if(numberOfMoves == 1)
            // length of 1 cannot be a strike
            return false;
        return (Game.instance().getLatestGameState().getPlayerToPlay().equals(Game.instance().getMove(numberOfMoves - 2).getPlayerToPlay()));
    }


    /**
     * Check if the piece move from (i, j) to (newI, newJ) is valid.
     * Checks for:
     *      1. boundary
     *      2. the new tile is empty
     *      3. tile difference (should be 1 or 2)
     *      3. If piece eat (difference of 2 ) -> check if the piece that was eaten belongs to the rival
     * @param gameStateOld the old game state
     * @param i x index of piece
     * @param j y index of piece
     * @param newI new x index
     * @param newJ new y index
     * @param checkPlayer check playing player (for checking if game is done)
     * @return true if the move was valid, else return false.
     */
    public static boolean isValidMove(GameState gameStateOld, int i, int j, int newI, int newJ, boolean checkPlayer) {
        if (newI < 0 || newJ < 0 || newI >= Game.instance().getSettings().getBoardSize() || newJ >= Game.instance().getSettings().getBoardSize())
            // check bounds
            return false;
        if (Math.abs(i - newI) < 1 || Math.abs(i - newI) > 2 || Math.abs(j - newJ) < 1 || Math.abs(j - newJ) > 2)
            // check if neighbors
            return false;
        BoardTile oldTile = gameStateOld.getTile(i, j);
        BoardTile newTile = gameStateOld.getTile(newI, newJ);

        if (oldTile.isEmpty() || !newTile.isEmpty() || (checkPlayer && !oldTile.getPiece().getColor().equals(gameStateOld.getPlayerToPlay())))
            // check if this cell isn't empty + new one is empty + this cell is the same color as player
            return false;

        return true;
    }

    /**
     * For a piece move with the index difference of 2 (eat move) get the 'stepped over' piece
     * @param gameStateOld the old game state
     * @param i x index of piece
     * @param j y index of piece
     * @param newI new x index
     * @param newJ new y index
     * @return the stepped over piece.
     */
    public static Piece getStepOverPiece(GameState gameStateOld, int i, int j, int newI, int newJ) {
        // stepped over indexes is the average of indexes
        int stepOverPieceI = (i + newI) / 2;
        int stepOverPieceJ = (j + newJ) / 2;
        return gameStateOld.getTile(stepOverPieceI, stepOverPieceJ).getPiece(); // get step over piece
    }

    /**
     * Return if a piece move 'eat' is valid
     * @param gameStateOld the old game state
     * @param i x index of piece
     * @param j y index of piece
     * @param newI new x index
     * @param newJ new y index
     * @param checkStrike to check strike or not
     * @return valid or not
     */
    public static boolean canEat(GameState gameStateOld, int i, int j, int newI, int newJ, boolean checkStrike) {
        Piece piece = gameStateOld.getTile(i, j).getPiece();

        if (Math.abs(i - newI) != 2 || Math.abs(j - newJ) != 2) { // index for eating
            return false;
        }

        BoardTile tile = gameStateOld.getTile(newI, newJ);
        if (tile == null || !tile.isEmpty()) // new place isn't empty
            return false;


        Piece stepOverPiece = getStepOverPiece(gameStateOld, i, j, newI, newJ);

        if (stepOverPiece == null || stepOverPiece.getColor().equals(piece.getColor())) // check color
            return false;

        if(checkStrike) {
            if (pieceGotBack(piece.getColor(), i, newI) && !isStrikeMove() && !piece.isQueen()) {
                // first strike - cannot go back
                return false;
            }
        }


        // can go back

        return true;
    }

    /**
     * Check if the piece got back
     * @param color the piece color
     * @param i row index
     * @param newI new row index
     * @return true if got back, else false
     */
    public static boolean pieceGotBack(Color color, int i, int newI) {
        return color.equals(Color.WHITE) ? i > newI : i < newI;
    }

    /**
     * Check and return if the move has a following eat move (strike move)
     * @param gameState the game state
     * @param i the row index
     * @param j the column index
     * @return true if a strike a possible, else false
     */
    public static boolean canDoStrike(GameState gameState, int i, int j) {
        int boardSize = Game.instance().getSettings().getBoardSize();
        // check all possible moves (4 diagonals)
        for (int i1 = -2; i1 <= 2; i1 += 4) {
            for (int j1 = -2; j1 <= 2; j1 += 4) {
                if (i + i1 >= 0 && i + i1 < boardSize && j + j1 >= 0 && j + j1 < boardSize && canEat(gameState, i, j, i + i1, j + j1, false))
                    // if such move was found return true
                    return true;
            }
        }
        // non found, return false
        return false;
    }

}


