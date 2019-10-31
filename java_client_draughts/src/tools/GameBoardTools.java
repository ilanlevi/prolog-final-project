package tools;

import entities.BoardTile;
import entities.Game;
import entities.GameState;

public class GameBoardTools {

    public static GameState move(GameState gameStateOld, int i, int j, int newI, int newJ) {
        if (!isValidMove(gameStateOld, i, j, newI, newJ))
            return null;
        GameState gameState = new GameState(gameStateOld);
        if (Math.abs(i - newI) == 1 && Math.abs(j - newJ) == 1) { // normal move, didn't eat
            gameState.changePlayerToPlay();
            return gameState.movePiece(i, j, newI, newJ) ? gameState : null;
        }

        return null;
//        if (Math.abs(i - newI) == 2 && Math.abs(j - newJ) == 2) { // normal move, didn't eat
//            if (canEat(gameState, i, j, newI, newJ)) {
//                return null;
//            }
//        }

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
        if (i > newI && !oldTile.getPiece().isQueen())
            // only queen can go back
            return false;

        return true;
    }

    public static boolean canEat(GameState gameStateOld, int i, int j, int newI, int newJ) {
        if (Math.abs(i - newI) != 2 || Math.abs(j - newJ) != 2) { // index for eating
            return false;
        }
//        boolean movesUp = i - newI;
        return true;
    }
}
