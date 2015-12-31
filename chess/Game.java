package chess;

import java.util.List;
import java.util.ArrayList;

import static chess.PieceColor.*;

/** Represents a game of chess with a board and GUI.
 *  @author Wan Fung Chui
 */

class Game {

    /** A game of 2-player chess, displayed in a GUI. */
    Game() {
        _moves = new ArrayList<Move>();
        _gui = new ChessGUI("Chess", this);
        newGame();
    }

    /** Clears the game and starts a new one. */
    public void newGame() {
        initializeBoard();
        _moves.clear();
        _turn = WHITE;
        _selectedX = -1;
        _selectedY = -1;
    }

    /** Quits the game. */
    public void quit() {
        System.exit(0);
    }

    /** Undoes the last move in the game. */
    public void undoMove() {
        if (_moves.size() > 0) {
            Move lastMove = _moves.remove(_moves.size() - 1);
            makeMove(lastMove.undoMove());
            _moves.remove(_moves.size() - 1);
        }
    }

    /** Makes the move MOVE on the board. Assumes that it is valid. */
    public void makeMove(Move move) {
        _moves.add(move);
        if (!move.isDouble()) {
            SingleMove singlemove = (SingleMove) move;
            executeMove(singlemove);
        } else {
            DoubleMove doublemove = (DoubleMove) move;
            executeMove(doublemove.move1());
            executeMove(doublemove.move2());
        }
        _turn = _turn.opposite();
    }

    /** Executes the single move MOVE on the board. */
    private void executeMove(SingleMove move) {
        _board[move.x1()][move.y1()] = move.replace();
        if (move.replace() != null) {
            move.replace().setLocation(move.x1(), move.y1());
        }
        _board[move.x2()][move.y2()] = move.selected();
        if (move.selected() != null) {
            move.selected().setLocation(move.x2(), move.y2());
        }
        if (move.target() != null) {
            move.target().setLocation(-1, -1);
        }
    }

    /** Returns whether COLOR king is being checked by the opponent. */
    public boolean inCheck(PieceColor color) {
        int x = kingX(color);
        int y = kingY(color);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = get(i, j);
                if (p != null && p.color() == color.opposite()
                    && p.canCapture(x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Returns whether the player to play has no valid moves. */
    public boolean noMoves() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = get(i, j);
                if (p != null && p.color() == _turn) {
                    if (p.hasMove()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /** Returns whether the position (X, Y) is being guarded by an opposing
      * piece. Used to check if a king can castle. */
    public boolean guarded(int x, int y) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = get(i, j);
                if (p != null && p.color() == _turn.opposite()
                    && p.canCapture(x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Returns the piece present at column I and row J. */
    public Piece get(int i, int j) {
        return _board[i][j];
    }

    /** Returns the last piece that moved in the game. */
    public Piece lastMover() {
        return _moves.get(_moves.size() - 1).movedPiece();
    }

    /** Initializes the board to begin play. */
    private void initializeBoard() {
        Piece blackRo0 = new Rook(BLACK, this, 0, 0);
        Piece blackKn0 = new Knight(BLACK, this, 1, 0);
        Piece blackBi0 = new Bishop(BLACK, this, 2, 0);
        Piece blackQu0 = new Queen(BLACK, this, 3, 0);
        _blackKi = new King(BLACK, this, 4, 0);
        Piece blackBi1 = new Bishop(BLACK, this, 5, 0);
        Piece blackKn1 = new Knight(BLACK, this, 6, 0);
        Piece blackRo1 = new Rook(BLACK, this, 7, 0);
        Piece blackPa0 = new Pawn(BLACK, this, 0, 1);
        Piece blackPa1 = new Pawn(BLACK, this, 1, 1);
        Piece blackPa2 = new Pawn(BLACK, this, 2, 1);
        Piece blackPa3 = new Pawn(BLACK, this, 3, 1);
        Piece blackPa4 = new Pawn(BLACK, this, 4, 1);
        Piece blackPa5 = new Pawn(BLACK, this, 5, 1);
        Piece blackPa6 = new Pawn(BLACK, this, 6, 1);
        Piece blackPa7 = new Pawn(BLACK, this, 7, 1);
        Piece whiteRo0 = new Rook(WHITE, this, 0, 7);
        Piece whiteKn0 = new Knight(WHITE, this, 1, 7);
        Piece whiteBi0 = new Bishop(WHITE, this, 2, 7);
        Piece whiteQu0 = new Queen(WHITE, this, 3, 7);
        _whiteKi = new King(WHITE, this, 4, 7);
        Piece whiteBi1 = new Bishop(WHITE, this, 5, 7);
        Piece whiteKn1 = new Knight(WHITE, this, 6, 7);
        Piece whiteRo1 = new Rook(WHITE, this, 7, 7);
        Piece whitePa0 = new Pawn(WHITE, this, 0, 6);
        Piece whitePa1 = new Pawn(WHITE, this, 1, 6);
        Piece whitePa2 = new Pawn(WHITE, this, 2, 6);
        Piece whitePa3 = new Pawn(WHITE, this, 3, 6);
        Piece whitePa4 = new Pawn(WHITE, this, 4, 6);
        Piece whitePa5 = new Pawn(WHITE, this, 5, 6);
        Piece whitePa6 = new Pawn(WHITE, this, 6, 6);
        Piece whitePa7 = new Pawn(WHITE, this, 7, 6);
        Piece[][] newBoard = {
            {blackRo0, blackPa0, null, null, null, null, whitePa0, whiteRo0},
            {blackKn0, blackPa1, null, null, null, null, whitePa1, whiteKn0},
            {blackBi0, blackPa2, null, null, null, null, whitePa2, whiteBi0},
            {blackQu0, blackPa3, null, null, null, null, whitePa3, whiteQu0},
            {_blackKi, blackPa4, null, null, null, null, whitePa4, _whiteKi},
            {blackBi1, blackPa5, null, null, null, null, whitePa5, whiteBi1},
            {blackKn1, blackPa6, null, null, null, null, whitePa6, whiteKn1},
            {blackRo1, blackPa7, null, null, null, null, whitePa7, whiteRo1} };
        _board = newBoard;
    }

    /** Returns the x-location of the king of color COLOR. */
    public int kingX(PieceColor color) {
        if (color == WHITE) {
            return _whiteKi.getX();
        } else {
            return _blackKi.getX();
        }
    }

    /** Returns the y-location of the king of color COLOR. */
    public int kingY(PieceColor color) {
        if (color == WHITE) {
            return _whiteKi.getY();
        } else {
            return _blackKi.getY();
        }
    }

    /** Sets the selected piece's x-location to X. */
    public void setSelectedX(int x) {
        _selectedX = x;
    }

    /** Sets the selected piece's y-location to Y. */
    public void setSelectedY(int y) {
        _selectedY = y;
    }

    /** Returns the selected piece's x-location. */
    public int selectedX() {
        return _selectedX;
    }

    /** Returns the selected piece's y-location. */
    public int selectedY() {
        return _selectedY;
    }

    /** Returns the board associated with this game. */
    public Piece[][] board() {
        return _board;
    }

    /** Returns the color to play on this turn. */
    public PieceColor turn() {
        return _turn;
    }

    /** The game board representing this game. */
    private Piece[][] _board;

    /** The GUI displayed to the player. */
    private ChessGUI _gui;

    /** The color to move next. */
    private PieceColor _turn;

    /** An ordered list of the moves made in the game. */
    private List<Move> _moves;

    /** Stores the black king. */
    private King _blackKi;

    /** Stores the white king. */
    private King _whiteKi;

    /** The x-location of the piece selected by the user for a move. */
    private int _selectedX;

    /** The y-location of the piece selected by the user for a move. */
    private int _selectedY;

}
