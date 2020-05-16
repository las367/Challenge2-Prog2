package chess;

import pieces.Piece;

public interface Receiver {
    
    /**
     * read the movement of opponent's piece => possible own piece to be killed.
     * @param x
     * @param y
     * @param piece
     */
    void readMovement ();

    /**
     * accept message from server if checkmate => end game.
     */
    void acceptCheckMate ();

    /**
     * refuse checkmate message given from the server => tellError => server check the checkmate condition once again.
     */
    void refuseCheckMate ();

    /**
     * tme elapsed from another client!
     * @return
     */
    int readTimeElapsed ();
}