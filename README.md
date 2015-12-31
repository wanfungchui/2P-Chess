# 2P Chess
A Java implementation of 2-Player Chess.

![Preview](chess/images/preview.PNG)

*Above is a queen-sacrifice white checkmate achievable in 7 moves.*

All standard rules of the game are implemented, including pawn promotion, en passant, castle, and stalemate. After compiling, play by running
```java chess.Main```

The UI is based off Chess Titans, a default game that comes packaged with Windows 7. Play commences by clicking pieces and their desired destinations on a GUI powered by Graphics2D and the [ucb.gui] (https://inst.eecs.berkeley.edu/~cs61b/fa15/docs/ucb-docs/ucb/gui/package-summary.html) package. Players can choose to begin a new game, undo their previous move, or quit.

Colors and types are designed as enums, while the Piece interface is implemented in each respective piece type. The Game class stores a 8x8 2D array to keep track of the board, which is then used in the GameDisplay class to display the game in the GUI.
