package chess;

import static chess.PieceType.*;

/** A rook in a chess game.
 *  @author Wan Fung Chui
 */
public class Rook implements Piece {

    /** A constructor for a piece on the board with color COLOR,
      * game GAME, and location (X, Y). */
    public Rook(PieceColor color, Game game, int x, int y) {
        _color = color;
        _game = game;
        _x = x;
        _y = y;
    }

    @Override
    public String imageString() {
        return _color.abbrev() + ROOK.abbrev();
    }

    @Override
    public PieceColor color() {
        return _color;
    }

    @Override
    public PieceType type() {
        return ROOK;
    }

    @Override
    public boolean makeValidMove(int a, int b) {
        if (_game.get(a, b) != null
            && _game.get(a, b).color() == _color) {
            return false;
        } else if (a == _x) {
            int dir = (b - _y) / Math.abs(b - _y);
            for (int i = _y + dir; i != b; i += dir) {
                if (_game.get(_x, i) != null) {
                    return false;
                }
            }
            Move move = new SingleMove(this, _x, _y, _game.get(a, b), a, b);
            return makeMoveCareful(move);
        } else if (b == _y) {
            int dir = (a - _x) / Math.abs(a - _x);
            for (int i = _x + dir; i != a; i += dir) {
                if (_game.get(i, _y) != null) {
                    return false;
                }
            }
            Move move = new SingleMove(this, _x, _y, _game.get(a, b), a, b);
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
        if ((_x + 1 <= 7 && makeValidMove(_x + 1, _y))
            || (_x - 1 >= 0 && makeValidMove(_x - 1, _y))
            || (_y + 1 <= 7 && makeValidMove(_x, _y + 1))
            || (_y - 1 >= 0 && makeValidMove(_x, _y - 1))) {
            _game.undoMove();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean canCapture(int a, int b) {
        if (a == _x) {
            int dir = (b - _y) / Math.abs(b - _y);
            for (int i = _y + dir; i != b; i += dir) {
                if (_game.get(_x, i) != null) {
                    return false;
                }
            }
            return true;
        } else if (b == _y) {
            int dir = (a - _x) / Math.abs(a - _x);
            for (int i = _x + dir; i != a; i += dir) {
                if (_game.get(i, _y) != null) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
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

    /** Returns whether this rook has been moved. */
    public boolean moved() {
        return _moved;
    }

    /** The game this piece belongs to. */
    private Game _game;

    /** The color of this piece. */
    private PieceColor _color;

    /** The x-location of this piece. */
    private int _x;

    /** The y-location of this piece. */
    private int _y;

    /** Stores whether this rook has been moved (for castle). */
    private boolean _moved;

}
