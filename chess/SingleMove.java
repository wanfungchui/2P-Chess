package chess;

/** A normal, single move in a chess game.
 *  @author Wan Fung Chui
 */
public class SingleMove implements Move {

    /** Constructs a new move on the board by selected piece S,
      * onto the target piece T, from the cell at (X1, Y1) to
      * the cell at (X2, Y2). */
    public SingleMove(Piece s, int x1, int y1, Piece t, int x2, int y2) {
        _selected = s;
        _x1 = x1;
        _y1 = y1;
        _target = t;
        _x2 = x2;
        _y2 = y2;
        _replace = null;
    }

    /** Constructs a new move on the board by selected piece S,
      * onto the target piece T, from the cell at (X1, Y1) to
      * the cell at (X2, Y2), replacing the cell at (X1, Y1) with
      * the piece designated by R. */
    private SingleMove(Piece s, int x1, int y1, Piece t,
        int x2, int y2, Piece r) {
        _selected = s;
        _x1 = x1;
        _y1 = y1;
        _target = t;
        _x2 = x2;
        _y2 = y2;
        _replace = r;
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public Move undoMove() {
        return new SingleMove(_selected, _x2, _y2, null, _x1, _y1, _target);
    }

    @Override
    public Piece movedPiece() {
        return _selected;
    }

    /** Returns the piece being moved in this move. */
    public Piece selected() {
        return _selected;
    }

    /** Returns the piece at the destination cell of this move. */
    public Piece target() {
        return _target;
    }

    /** Returns the piece that will replace the moved piece after the move. */
    public Piece replace() {
        return _replace;
    }

    /** Returns the x-location of _selected. */
    public int x1() {
        return _x1;
    }

    /** Returns the y-location of _selected. */
    public int y1() {
        return _y1;
    }

    /** Returns the x-location of _target. */
    public int x2() {
        return _x2;
    }

    /** Returns the y-location of _target. */
    public int y2() {
        return _y2;
    }

    /** The piece being moved in this move. */
    private Piece _selected;

    /** The piece at the move destination. */
    private Piece _target;

    /** THe piece that will replace the moved piece after the move. */
    private Piece _replace;

    /** x-location of _selected. */
    private int _x1;

    /** y-location of _selected. */
    private int _y1;

    /** x-location of _target. */
    private int _x2;

    /** y-location of _target. */
    private int _y2;

}
