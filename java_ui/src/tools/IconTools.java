package tools;

import entities.Piece;
import consts.IconsConst;

public class IconTools {

    /**
     * Return the icon path for piece.
     * If the piece is null, return an empty string
     * @param piece the piece
     * @return icon path as string
     */
    public String getIconForPiece(Piece piece){
        if(piece == null) {
            return "";
        }

        String isQueenIcon = piece.isQueen() ? "Q" : "";
        return IconsConst.ICON_PREFIX + piece.getColor().getName() + isQueenIcon + IconsConst.ICON_SUFFIX;
    }
}
