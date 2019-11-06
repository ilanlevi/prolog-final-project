package MainPackage.entities;

import MainPackage.gui.entities.BoardGui;
import javafx.scene.Node;

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

    public Game setBoardGui(BoardGui boardGui) {
        instance.boardGui = boardGui;
        return this;
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

    public void resetGame() {
        instance.gameState.clear();
        instance.gameState.add(new GameState(instance));
        Optional.ofNullable(instance.boardGui).ifPresent(BoardGui::refresh);
    }

    public GameState getLatestGameState() {
        int size = instance.gameState.size();
        if (size == 0) {
            instance.gameState.add(new GameState(instance));
            return instance.gameState.get(0);
        }
        return instance.gameState.get(size - 1);
    }

    public int numberOfMoves(){
        return instance.gameState.size();
    }

    public GameState getMove(int index){
        if(index < 0 || index >= instance.gameState.size())
            return null;
        return instance.gameState.get(index);
    }

    public void goBackState() {
        int s = instance.gameState.size();
        if (instance.gameState.size() > 0)
            instance.gameState.remove(s - 1);
        Optional.ofNullable(instance.boardGui).ifPresent(BoardGui::refresh);
    }

    public void addNewState(GameState state) {
        if (state != null)
            instance.gameState.add(state);
        Optional.ofNullable(instance.boardGui).ifPresent(BoardGui::refresh);
    }

}
