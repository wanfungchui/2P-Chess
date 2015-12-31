package chess;

import static chess.PieceType.*;

/** A king in a chess game.
 *  @author Wan Fung Chui
 */
public class King implements Piece {

    /** A constructor for a piece on the board with color COLOR,
      * game GAME, and location (X, Y). */
    public King(PieceColor color, Game game, int x, int y) {
        _color = color;
        _game = game;
        _x = x;
        _y = y;
        _moved = false;
    }

    @Override
    public String imageString() {
        return _color.abbrev() + KING.abbrev();
    }

    @Override
    public PieceColor color() {
        return _color;
    }

    @Override
    public PieceType type() {
        return KING;
    }

    @Override
    public boolean makeValidMove(int a, int b) {
        if (Math.abs(a - _x) <= 1 && Math.abs(b - _y) <= 1
            && (_game.get(a, b) == null || _game.get(a, b).color() != _color)) {
            Move move = new SingleMove(this, _x, _y, _game.get(a, b), a, b);
            return makeMoveCareful(move);
        } else if (_x == originalX() && _y == originalY()
            && a == originalX() + 2 && b == originalY()
            && _game.get(a - 1, b) == null && _game.get(a, b) == null
            && _game.get(a + 1, b) != null && _game.get(a + 1, b).type() == ROOK
            && _game.get(a + 1, b).color() == _color && !_game.inCheck(_color)
            && !_game.guarded(a - 1, b) && !_game.guarded(a, b)
            && !_moved && !((Rook) _game.get(a + 1, b)).moved()) {
            SingleMove move1 = new SingleMove(this, _x, _y,
                _game.get(a, b), a, b);
            SingleMove move2 = new SingleMove(_game.get(a + 1, b),
                a + 1, b, null, a - 1, b);
            DoubleMove move = new DoubleMove(move1, move2);
            return makeMoveCareful(move);
        } else if (_x == originalX() && _y == originalY()
            && a == originalX() - 2 && b == originalY()
            && _game.get(a - 1, b) == null && _game.get(a, b) == null
            && _game.get(a + 1, b) == null && _game.get(a - 2, b) != null
            && _game.get(a - 2, b).type() == ROOK
            && _game.get(a - 2, b).color() == _color
            && !_game.inCheck(_color)
            && !_game.guarded(a - 1, b) && !_game.guarded(a, b)
            && !_moved && !((Rook) _game.get(a - 2, b)).moved()) {
            SingleMove move1 = new SingleMove(this, _x, _y,
                _game.get(a, b), a, b);
            SingleMove move2 = new SingleMove(_game.get(a - 2, b), a - 2,
                b, null, a + 1, b);
            DoubleMove move = new DoubleMove(move1, move2);
            return makeMoveCareful(move);
        } else {
            return false;
        }
    }

    @Override
    public void setLocation(int x, int y) {
        _x = x;
        _y = y;
    }

    @Override
    public boolean hasMove() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (_x + i >= 0 && _x + i <= 7 && _y + j >= 0 && _y + j <= 7) {
                    if (makeValidMove(_x + i, _y + j)) {
                        _game.undoMove();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean canCapture(int a, int b) {
        return Math.abs(a - _x) <= 1 && Math.abs(b - _y) <= 1;
    }

    /** Makes the move MOVE, and returns false if the move doesn't leave the
      * king in check, in which case the move is undone, and true otherwise. */
    private boolean makeMoveCareful(Move move) {
        _game.makeMove(move);
        if (_game.inCheck(_game.turn().opposite())) {
            _game.undoMove();
            return false;
        } else {
            _moved = true;
            return true;
        }
    }

    /** Returns the x-location of the king. */
    public int getX() {
        return _x;
    }

    /** Returns the y-location of the king. */
    public int getY() {
        return _y;
    }

    /** Returns the original x-location of this piece. */
    private int originalX() {
        return 4;
    }

    /** Returns the original y-location of this piece. */
    private int originalY() {
        if (_color == PieceColor.WHITE) {
            return 7;
        } else {
            return 0;
        }
    }

    /** The game this piece belongs to. */
    private Game _game;

    /** The color of this piece. */
    private PieceColor _color;

    /** The x-location of this piece. */
    private int _x;

    /** The y-location of this piece. */
    private int _y;

    /** Stores whether this king has been moved (for castle). */
    private boolean _moved;

}
