package chess;

/** A piece in a chess game.
 *  @author Wan Fung Chui
 */
public interface Piece {

    /** Returns the string for display of this piece on the GUI. */
    String imageString();

    /** Returns the color of this piece. */
    PieceColor color();

    /** Returns the type of this piece (e.g. Queen). */
    PieceType type();

    /** Returns whether the cell at column A and row B is a valid
      * destination for a move by the piece, and makes the move if valid. */
    boolean makeValidMove(int a, int b);

    /** Returns whether this piece has a valid move to play. */
    boolean hasMove();

    /** Returns whether this piece is threatening the piece at (A, B). */
    boolean canCapture(int a, int b);

    /** Sets the location of the piece to (X, Y) on the chessboard. */
    void setLocation(int x, int y);

}
