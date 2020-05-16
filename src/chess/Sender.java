package chess;

import pieces.Piece;

public interface Sender {
    
    /**
     * move a piece to pos x, y => also possible to kill opponent's piece
     * @param x
     * @param y
     * @param piece
     */
    void set (int x, int y, Piece piece);

    /**
     * Give message to the server => client recognized check mate. waiting for verifications.
     */
    void requestCheckMate ();

    /**
     * give up => end game.
     */
    void giveUp ();

    /**
     * tell server if an error occurred
     * @return error number
     */
    int tellError ();

    int handshake (String address);
}