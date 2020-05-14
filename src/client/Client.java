package client;

import java.net.URL;

import Piece.Piece;

public interface Client {
    
    /**
     * ping external address and compare result with another client to determine which client works as server.
     * @param ip external address
     * @return time elapsed?
     */
    int setServer (URL ip);

    /**
     * establish connection with server
     * //TODO add DataConnector to this method?
     * @param ip address of server
     * @param port port of the server addr.
     * @return true if successfull.
     */
    boolean handshake (URL ip, int port);

    /**
     * move a piece to pos x, y => also possible to kill opponent's piece
     * @param x
     * @param y
     * @param piece
     */
    void set (int x, int y, Piece piece);

    /**
     * read the movement of opponent's piece => possible own piece to be killed.
     * @param x
     * @param y
     * @param piece
     */
    void readMovement (int x, int y, Piece piece);

    /**
     * give up => end game.
     */
    void giveUp ();

    /**
     * tell server if an error occurred
     * @return error number
     */
    int tellError ();

    /**
     * accept message from server if checkmate => end game.
     */
    void acceptCheckMate ();

    /**
     * refuse checkmate message given from the server => tellError => server check the checkmate condition once again.
     */
    void refuseCheckMate ();

    /**
     * write data into the server. !!EVERY DATA STARTS WITH AN ID
     */
    void write ();

    /**
     * read data given by the server. !!EVERY DATA STARTS WITH AN ID
     */
    void read ();
}