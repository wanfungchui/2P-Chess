package chess;

/** A move in a chess game.
 *  @author Wan Fung Chui
 */
public interface Move {

    /** Returns whether this move is a double move (e.g. castle) */
    boolean isDouble();

    /** Returns the opposite of this move, for undoing. */
    Move undoMove();

    /** Returns the selected piece that moved in this move. */
    Piece movedPiece();

}
