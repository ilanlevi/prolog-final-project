package entities;

import consts.SettingConst;

public class GameSettings {

    private int level;
    private int boardSize;
    private int starterPieces;

    /**
     * Initialize values with default values
     *
     * @see SettingConst#DEFAULT_LEVEL
     * @see SettingConst#DEFAULT_DIMENSIONS
     */
    public GameSettings() {
        level = SettingConst.DEFAULT_LEVEL;
        boardSize = SettingConst.DEFAULT_DIMENSIONS;
    }


    /**
     * @return game level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set game level if valid.
     *
     * @param level new valid level
     * @return this
     * @see SettingConst#MAX_LEVEL
     * @see SettingConst#MIN_LEVEL
     */
    public GameSettings setLevel(int level) {
        if (level <= SettingConst.MAX_LEVEL && level >= SettingConst.MIN_LEVEL) {
            this.level = level;
        }
        return this;
    }

    /**
     * @return board dimensions
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Set game dimensions if valid.
     *
     * @param boardSize new valid dimensions - odd number
     * @return this
     * @see SettingConst#MAX_DIMENSIONS
     * @see SettingConst#MIN_DIMENSIONS
     */
    public GameSettings setBoardSize(int boardSize) {
        if (boardSize % 2 == 0 && boardSize <= SettingConst.MAX_DIMENSIONS && boardSize >= SettingConst.MIN_DIMENSIONS) {
            this.boardSize = boardSize;
        }
        return this;
    }
}
