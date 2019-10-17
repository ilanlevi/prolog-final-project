package entities;

import java.util.ArrayList;

public class Game {
    private GameSettings settings;
    private ArrayList<GameState> gameStates;

    private Game() {
        this.settings = new GameSettings();
//        setGameStates();
    }

    private static Game instance = null;

    public static Game instance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }


    private void setGameStates() {
        this.gameStates = new ArrayList<>();
        this.gameStates.add(new GameState(this));
    }

    public GameSettings getSettings() {
        return settings;
    }

    public Game resetGame(GameSettings gameSettings) {
        this.settings = gameSettings;
//        setGameStates();
        return instance;
    }

}
