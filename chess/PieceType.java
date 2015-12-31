package chess;

/** The different types of pieces in a chess game.
 *  @author Wan Fung Chui
 */
enum PieceType {
    /** The names of the pieces. */
    BISHOP, KING, KNIGHT, PAWN, QUEEN, ROOK;

    /** Returns the string denotation of this piece. */
    String abbrev() {
        switch (this) {
        case BISHOP:
            return "bi";
        case KING:
            return "ki";
        case KNIGHT:
            return "kn";
        case PAWN:
            return "pa";
        case QUEEN:
            return "qu";
        case ROOK:
            return "ro";
        default:
            return "-";
        }
    }

}
