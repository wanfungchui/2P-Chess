# 2P-Chess
A Java implementation of 2-Player Chess.

![animation](https://thumbs.gfycat.com/ScrawnySomeArawana-max-1mb.gif)

Most standard rules of the game are implemented, including pawn promotion, en passant, castle, and stalemate. The only caveat is that pawns can only be promoted to queens. An undo function is also implemented for both players.

The GUI is powered by Graphics2D and the [ucb.gui] (https://inst.eecs.berkeley.edu/~cs61b/fa15/docs/ucb-docs/ucb/gui/package-summary.html) package. The UI was inspired by Chess Titans, a default game in Windows 7.

# Usage

Play requires the latest version of Java and Make. Clone this repository and run
```
$ make
$ java chess.Main
```
