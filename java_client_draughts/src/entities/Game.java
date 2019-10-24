package entities;

import java.util.ArrayList;

public class Game {
    private GameSettings settings;
    private ArrayList<GameState> gameState;

    private Game() {
        settings = new GameSettings();
        gameState = new ArrayList<>();
    }

    private static Game instance = new Game();

    public static Game instance() {
        return instance;
    }

    public Game setSettings(GameSettings settings) {
        this.settings = settings;
        return this;
    }

    public GameSettings getSettings() {
        return settings;
    }

    public Game resetGame() {
        instance.gameState.clear();
        instance.gameState.add(new GameState(instance));
        return instance;
    }

    public GameState getLatestGameState() {
        int size = gameState.size();
        if (size == 0)
            instance.gameState.add(new GameState(instance));
        return gameState.get(0);
    }

    public GameState goBackState() {
        int size = gameState.size();
        if (size > 1)
            instance.gameState.remove(instance.getLatestGameState());
        return instance.getLatestGameState();
    }

    public void addNewState(GameState state) {
        if (state != null)
            gameState.add(state);
    }

}
