package entities;

import java.util.ArrayList;

public class Game {
    private GameSettings settings;
    private ArrayList<GameState> gameStates;

    private Game(){
        this.settings = new GameSettings();
//        setGameStates();
    }

    private void setGameStates(){
        this.gameStates = new ArrayList<>();
        this.gameStates.add(new GameState(this));
    }

    private static final Game instance = new Game();

    public static Game getInstance(){
        return instance;
    }

    public GameSettings getSettings() {
        return settings;
    }

    public Game resetGame(GameSettings gameSettings){
        this.settings  = gameSettings;
        setGameStates();
        return instance;
    }

}
