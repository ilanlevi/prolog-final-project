package tools;

import entities.*;

public class GameBoardTools {

    public static GameState move(GameState gameStateOld, int i, int j, int newI, int newJ) {
        if (!isValidMove(gameStateOld, i, j, newI, newJ))
            return null;
        GameState gameState = new GameState(gameStateOld);
        Piece piece = gameState.getTile(i, j).getPiece();
        if (Math.abs(i - newI) == 1 && Math.abs(j - newJ) == 1) { // normal move, didn't eat
            if (!piece.isQueen() && pieceGotBack(piece.getColor(), i, newI)) {
                // only queen can go back
                return null;
            }
            gameState.changePlayerToPlay();
            if(!gameState.movePiece(i, j, newI, newJ))
                return null;
            gameState.markNewQueensIfNeeded();
            return gameState;
        }

        // eating
        if (canEat(gameStateOld, i, j, newI, newJ)) {
            gameState.removePiece((i + newI) / 2, (j + newJ) / 2);
            gameState.movePiece(i, j, newI, newJ);
            if(!canEatAnyone(gameState, newI, newJ)) { // cannot do strike
                gameState.markNewQueensIfNeeded();
                gameState.changePlayerToPlay();
            }
            return gameState;
        }

        return null;
    }

    public static boolean isStrikeMove(GameState gameState) {
        int numberOfMoves = Game.instance().numberOfMoves();
        if (numberOfMoves == 1)
            return false;
        return gameState.getPlayerToPlay().equals(Game.instance().getMove(numberOfMoves - 1).getPlayerToPlay());
    }

    public static boolean isValidMove(GameState gameStateOld, int i, int j, int newI, int newJ) {
        if (newI < 0 || newJ < 0 || newI >= Game.instance().getSettings().getBoardSize() || newJ >= Game.instance().getSettings().getBoardSize())
            // check bounds
            return false;
        if (Math.abs(i - newI) < 1 || Math.abs(i - newI) > 2 || Math.abs(j - newJ) < 1 || Math.abs(j - newJ) > 2)
            // check if neighbors
            return false;
        BoardTile oldTile = gameStateOld.getTile(i, j);
        BoardTile newTile = gameStateOld.getTile(newI, newJ);

        if (oldTile.isEmpty() || !newTile.isEmpty() || !oldTile.getPiece().getColor().equals(gameStateOld.getPlayerToPlay()))
            // check if this cell isn't empty + new one is empty + this cell is the same color as player
            return false;

        return true;
    }

    public static Piece getStepOverPiece(GameState gameStateOld, int i, int j, int newI, int newJ){
        int stepOverPieceI = (i + newI) / 2;
        int stepOverPieceJ = (j + newJ) / 2;
        return gameStateOld.getTile(stepOverPieceI, stepOverPieceJ).getPiece(); // get step over piece
    }

    public static boolean canEat(GameState gameStateOld, int i, int j, int newI, int newJ) {
        Piece piece = gameStateOld.getTile(i, j).getPiece();

        if (Math.abs(i - newI) != 2 || Math.abs(j - newJ) != 2) { // index for eating
            return false;
        }

        BoardTile tile = gameStateOld.getTile(newI, newJ);
        if(tile == null || !tile.isEmpty()) // new place isn't empty
            return false;


        Piece stepOverPiece = getStepOverPiece(gameStateOld, i, j, newI, newJ);

        if (stepOverPiece == null || stepOverPiece.getColor().equals(piece.getColor())) // check color
            return false;

        if (!pieceGotBack(piece.getColor(), i, newI) && !isStrikeMove(gameStateOld) && !piece.isQueen()) {
            // first strike - cannot go back
            return false;
        }



        // can go back

        return true;
    }

    public static boolean pieceGotBack(Color color, int i, int newI){
        return color.equals(Color.WHITE) ? i  > newI : i < newI;
    }

    public static boolean canEatAnyone(GameState gameState, int i, int j) {
        int boardSize = Game.instance().getSettings().getBoardSize();
        for (int i1 = -2; i1 <= 2; i1 += 4) {
            for (int j1 = -2; j1 <= 2; j1 += 4) {
                if (i + i1 >= 0 && i + i1 < boardSize && j + j1 >= 0 && j + j1 < boardSize && canEat(gameState, i, j, i + i1, j + j1))
                    return true;
            }
        }
        return false;
    }

}


