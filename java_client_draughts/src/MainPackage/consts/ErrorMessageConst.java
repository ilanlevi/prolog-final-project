package MainPackage.consts;

public class ErrorMessageConst {
    public static final String LEVEL_ERROR_MESSAGE = "The Level is invalid - please choose an option!";

    public static final String BOARD_SIZE_ERROR_MESSAGE = "The Board size is invalid - please enter a number in range: " +
            "[" + SettingConst.MIN_DIMENSIONS + ", " + SettingConst.MAX_DIMENSIONS + "]";

    public static final String PAWN_LINES_ERROR_MESSAGE = "The size is invalid - " +
            "please enter a number bigger then 0 and smaller then half of board size";

    public static final String SERVER_URL_EMPTY_ERROR_MESSAGE = "Server address is empty, please enter server ip!";

    public static final String EMPTY_MESSAGE = "";

    public static final String NOT_A_NUMBER_MESSAGE = "Please insert numbers only!";
}
