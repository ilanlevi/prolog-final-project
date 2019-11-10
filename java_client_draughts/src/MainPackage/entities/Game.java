package MainPackage.entities;

import MainPackage.gui.entities.BoardGui;

import java.util.ArrayList;
import java.util.Optional;

public class Game {
    private GameSettings settings;
    private ArrayList<GameState> gameState;
    private BoardGui boardGui;

    private Game() {
        settings = new GameSettings();
        gameState = new ArrayList<>();
    }

    public BoardGui getBoardGui() {
        return boardGui;
    }

    public void setBoardGui(BoardGui boardGui) {
        instance.boardGui = boardGui;
    }

    private static Game instance = new Game();

    public static Game instance() {
        return instance;
    }

    public void setSettings(GameSettings settings) {
        instance.settings = settings;
    }

    public GameSettings getSettings() {
        return settings;
    }

    /**
     * Clear list and add a new state
     */
    public void resetGame() {
        instance.gameState.clear();
        instance.gameState.add(new GameState(instance));
        Optional.ofNullable(instance.boardGui).ifPresent(BoardGui::refresh);
    }

    public int getSize() {
        return gameState.size();
    }

    /**
     * @return the last state (last index)
     */
    public GameState getLatestGameState() {
        int size = instance.gameState.size();
        if (size == 0) {
            instance.gameState.add(new GameState(instance));
            return instance.gameState.get(0);
        }
        return instance.gameState.get(size - 1);
    }

    /**
     * @return size of game state list
     */
    public int numberOfMoves() {
        return instance.gameState.size();
    }

    /**
     * Return move with same index
     * @param index to remove
     * @return game state from list if index is in bound, else return null
     */
    public GameState getMove(int index) {
        if (index < 0 || index >= instance.gameState.size())
            return null;
        return instance.gameState.get(index);
    }

    /**
     * Remove last state
     */
    public void goBackState() {
        int s = instance.gameState.size();
        if (instance.gameState.size() > 1) {
            instance.gameState.remove(s - 1);
            instance.gameState.remove(s - 2);
            instance().getBoardGui().resetBoardColor();
        }
        Optional.ofNullable(instance.boardGui).ifPresent(BoardGui::refresh);
    }

    /**
     * Add new state to list if param isn't null
     * @param state new state
     */
    public void addNewState(GameState state) {
        if (state != null) {
            instance.gameState.add(state);
            Optional.ofNullable(instance.boardGui).ifPresent(BoardGui::refresh);
        }
    }

}
