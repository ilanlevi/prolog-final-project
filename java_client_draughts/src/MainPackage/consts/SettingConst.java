package MainPackage.consts;

/**
 * This class holds the game setting values as const.
 * Each setting has the following values: max, min and default
 */
public class SettingConst {

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

    public static final String SERVER_PREFIX_FOR_HTTP = "http://";
    public static final String SERVER_INFIX_FOR_PORT = ":";
}