package MainPackage.consts;

/**
 * This class holds the game setting values as const.
 * Each setting has the following values: max, min and default
 */
public class SettingConst {
// TODO: 01/11/2019 change const -> and write in info
    /**
     * Level const
     */
    public static final int MAX_LEVEL = 7;
    public static final int MIN_LEVEL = 1;
    public static final int DEFAULT_LEVEL = 3;

    /**
     * Board dimensions const
     */
    public static final int MIN_DIMENSIONS = 6;
    public static final int MAX_DIMENSIONS = 12;
    public static final int DEFAULT_DIMENSIONS = 8;

    /**
     * how many pawn lines of pawns for each player const
     */
    public static final int MIN_PAWS_LINES = 1;

    /**
     * Server communication defaults
     */
    public static final String SERVER_LOCAL_HOST = "127.0.0.1";
    public static final int SERVER_PORT = 3000;
}