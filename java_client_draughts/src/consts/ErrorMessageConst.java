package consts;

public class ErrorMessageConst {
    public static final String LEVEL_ERROR_MESSAGE = "The Level is invalid - please enter a number in range: " +
            "[" + SettingConst.MIN_LEVEL + ", " + SettingConst.MAX_LEVEL + "]";

    public static final String BOARD_SIZE_ERROR_MESSAGE = "The Board size is invalid - please enter a number in range: " +
            "[" + SettingConst.MIN_DIMENSIONS + ", " + SettingConst.MAX_DIMENSIONS + "]";

    public static final String PAWN_LINES_ERROR_MESSAGE = "The size is invalid - " +
            "please enter a number bigger then 0 and smaller then half of board size";

    public static final String EMPTY_MESSAGE = "";

    public static final String NOT_A_NUMBER_MESSAGE = "Please insert numbers only!";
}
