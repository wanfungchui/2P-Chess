package chess;

/** The different colors of pieces in a chess game.
 *  @author Wan Fung Chui
 */
enum PieceColor {
    /** The names of the colors. */
    BLACK, WHITE;

    /** Returns the string denotation of this color. */
    public String abbrev() {
        switch (this) {
        case BLACK:
            return "b";
        case WHITE:
            return "w";
        default:
            return "-";
        }
    }

    /** Returns the full string denotation of this color. */
    public String string() {
        switch (this) {
        case BLACK:
            return "BLACK";
        case WHITE:
            return "WHITE";
        default:
            return "-";
        }
    }

    /** Returns the opposite color. */
    public PieceColor opposite() {
        if (this == BLACK) {
            return WHITE;
        } else {
            return BLACK;
        }
    }

    /** Returns -1 if white, and +1 if black. */
    public int direction() {
        if (this == WHITE) {
            return -1;
        } else {
            return 1;
        }
    }

}
