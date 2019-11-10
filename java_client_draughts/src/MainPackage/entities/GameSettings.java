package MainPackage.entities;

import MainPackage.consts.SettingConst;

public class GameSettings {

    private int level;
    private int boardSize;
    private int startingLinesPawns; // how many pawn lines for each player
    private String serverUrl;
    private int serverPort;

    /**
     * Initialize values with default values
     *
     * @see SettingConst#DEFAULT_LEVEL
     * @see SettingConst#DEFAULT_DIMENSIONS
     */
    public GameSettings() {
        level = SettingConst.DEFAULT_LEVEL;
        boardSize = SettingConst.DEFAULT_DIMENSIONS;
        setStartingLinesPawns();
        serverPort = SettingConst.SERVER_PORT;
        serverUrl = SettingConst.SERVER_LOCAL_HOST;
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
        if (isLevelValid(level)) {
            this.level = level;
        }
        return this;
    }

    /**
     * Check and return if the level value if valid.
     *
     * @param level new valid level
     * @return valid or not (boolean)
     * @see SettingConst#MAX_LEVEL
     * @see SettingConst#MIN_LEVEL
     */
    public boolean isLevelValid(int level) {
        return (level <= SettingConst.MAX_LEVEL && level >= SettingConst.MIN_LEVEL);
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
     *                  and in range:  (MIN_DIMENSIONS, MAX_DIMENSIONS)
     * @return this
     * @see SettingConst#MAX_DIMENSIONS
     * @see SettingConst#MIN_DIMENSIONS
     */
    public GameSettings setBoardSize(int boardSize) {
        if (isBoardSizeValid(boardSize)) {
            this.boardSize = boardSize;
        }
        if (!isStartingLinesPawnsValid(startingLinesPawns))
            setStartingLinesPawns();
        return this;
    }

    /**
     * Check and return if the dimensions value if valid.
     *
     * @param boardSize new valid dimensions - odd number
     *                  and in range:  (MIN_DIMENSIONS, MAX_DIMENSIONS)
     * @return valid or not (boolean)
     * @see SettingConst#MAX_DIMENSIONS
     * @see SettingConst#MIN_DIMENSIONS
     */
    public boolean isBoardSizeValid(int boardSize) {
        return (boardSize % 2 == 0 && boardSize <= SettingConst.MAX_DIMENSIONS && boardSize >= SettingConst.MIN_DIMENSIONS);
    }

    /**
     * @return startingLinesPawns
     */
    public int getStartingLinesPawns() {
        return startingLinesPawns;
    }

    public GameSettings setStartingLinesPawns() {
        setStartingLinesPawns((boardSize / 2) - 1);
        return this;
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
        if (isStartingLinesPawnsValid(startingLinesPawns)) {
            this.startingLinesPawns = startingLinesPawns;
        }
        return this;
    }

    /**
     * Check and return if the startingLinesPawns value if valid.
     *
     * @param startingLinesPawns new valid startingLinesPawns - bigger/equal to MIN_PAWS_LINES
     *                           and smaller then: boardSize / 2   (at least one empty line)
     * @return valid or not (boolean)
     * @see SettingConst#MIN_PAWS_LINES
     */
    public boolean isStartingLinesPawnsValid(int startingLinesPawns) {
        return startingLinesPawns >= SettingConst.MIN_PAWS_LINES && startingLinesPawns < boardSize / 2;
    }

    /**
     * Check and return if the serverUrl value if valid.
     *
     * @param serverUrl server ip as string
     * @return not null and not empty will return true, or false otherwise
     */
    public boolean isServerURLValid(String serverUrl) {
        return serverUrl != null && !serverUrl.isEmpty();
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public GameSettings setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
        return this;
    }

    public int getServerPort() {
        return serverPort;
    }

    public GameSettings setServerPort(int serverPort) {
        this.serverPort = serverPort;
        return this;
    }

    public String getServerFullUrl() {
        return serverUrl + ":" + serverPort;
    }
}
