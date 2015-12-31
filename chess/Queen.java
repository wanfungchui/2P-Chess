package chess;

import static chess.PieceType.*;

/** A queen in a chess game.
 *  @author Wan Fung Chui
 */
public class Queen implements Piece {

    /** A constructor for a piece on the board with color COLOR,
      * game GAME, and location (X, Y). */
    public Queen(PieceColor color, Game game, int x, int y) {
        _color = color;
        _game = game;
        _x = x;
        _y = y;
    }

    @Override
    public String imageString() {
        return _color.abbrev() + QUEEN.abbrev();
    }

    @Override
    public PieceColor color() {
        return _color;
    }

    @Override
    public PieceType type() {
        return QUEEN;
    }

    @Override
    public boolean makeValidMove(int a, int b) {
        if (_game.get(a, b) != null
            && _game.get(a, b).color() == _color) {
            return false;
        } else if (a + b == _x + _y) {
            int dir = (a - _x) / Math.abs(a - _x);
            for (int i = _x + dir, j = _y - dir; i != a; i += dir, j -= dir) {
                if (_game.get(i, j) != null) {
                    return false;
                }
            }
            Move move = new SingleMove(this, _x, _y, _game.get(a, b), a, b);
            return makeMoveCareful(move);
        } else if (a - b == _x - _y) {
            int dir = (a - _x) / Math.abs(a - _x);
            for (int i = _x + dir, j = _y + dir; i != a; i += dir, j += dir) {
                if (_game.get(i, j) != null) {
                    return false;
                }
            }
            Move move = new SingleMove(this, _x, _y, _game.get(a, b), a, b);
            return makeMoveCareful(move);
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
        if (a + b == _x + _y) {
            int dir = (a - _x) / Math.abs(a - _x);
            for (int i = _x + dir, j = _y - dir; i != a; i += dir, j -= dir) {
                if (_game.get(i, j) != null) {
                    return false;
                }
            }
            return true;
        } else if (a - b == _x - _y) {
            int dir = (a - _x) / Math.abs(a - _x);
            for (int i = _x + dir, j = _y + dir; i != a; i += dir, j += dir) {
                if (_game.get(i, j) != null) {
                    return false;
                }
            }
            return true;
        } else if (a == _x) {
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
            return true;
        }
    }

    /** The game this piece belongs to. */
    private Game _game;

    /** The color of this piece. */
    private PieceColor _color;

    /** The x-location of this piece. */
    private int _x;

    /** THe y-location of this piece. */
    private int _y;

}
