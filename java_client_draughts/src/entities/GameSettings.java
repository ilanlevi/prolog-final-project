package entities;

import consts.SettingConst;

public class GameSettings {

    private int level;
    private int boardSize;
    private int startingLinesPawns; // how many pawn lines for each player

    /**
     * Initialize values with default values
     *
     * @see SettingConst#DEFAULT_LEVEL
     * @see SettingConst#DEFAULT_DIMENSIONS
     */
    public GameSettings() {
        level = SettingConst.DEFAULT_LEVEL;
        boardSize = SettingConst.DEFAULT_DIMENSIONS;
        startingLinesPawns = SettingConst.MIN_PAWS_LINES;
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
     *                     and in range:  (MIN_DIMENSIONS, MAX_DIMENSIONS)
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

    /**
     * @return startingLinesPawns
     */
    public int getStartingLinesPawns() {
        return startingLinesPawns;
    }

    /**
     * Set startingLinesPawns if valid.
     *
     * @param startingLinesPawns new valid startingLinesPawns - bigger/equal to MIN_PAWS_LINES
     *                           and smaller then: boardSize / 2   (at least one empty line)
     * @return this
     * @see SettingConst#MIN_PAWS_LINES
     */
    public GameSettings setStartingLinesPawns(int startingLinesPawns) {
        if (startingLinesPawns >= SettingConst.MAX_DIMENSIONS && startingLinesPawns < boardSize / 2) {
            this.startingLinesPawns = startingLinesPawns;
        }
        return this;
    }
}
