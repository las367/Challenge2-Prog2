package chess;

import java.io.IOException;

import pieces.Pawn;
import pieces.Piece;

public class ChessEngine {
    
    private ChessStates state;
    private Receiver receiver;
    private Sender sender;
    
    private int timeElapsed;

    // TODO how to decide external address!
    // => there would be problems if c1 has exadress google while c2 has htw-berlin.de
    private String EXTERNAL_ADDRESS = "google.de";

    public ChessEngine() {

        state = ChessStates.START;
    }

    /**
     * define who starts first at the game.
     * If protocol accepted => define who would act as the server!!
     * @throws OutOfStateException
     */
    void initiateHandshake () throws OutOfStateException, IOException {

        if ( state != ChessStates.START ) throw new OutOfStateException("Not at start!");

        timeElapsed = sender.handshake(EXTERNAL_ADDRESS);
        int oppTimeElapsed = receiver.readTimeElapsed();

        if ( timeElapsed == oppTimeElapsed ) state = ChessStates.START;
        else state = timeElapsed > oppTimeElapsed ? ChessStates.PASSIVE : ChessStates.ACTIVE;
    }

    void startTurn () throws OutOfStateException {

        if ( state != ChessStates.PASSIVE ) throw new OutOfStateException("False!");

        receiver.readMovement();
        // TODO create method to read checkmateMessage from another client!

        state = ChessStates.ACTIVE;
    }

    void endTurn () throws OutOfStateException {

        if ( state != ChessStates.ACTIVE ) throw new OutOfStateException("No!");

        //what to set?

        //here mock data to prevent error!
        int x = 1,
            y = 3;

        Piece mockPawn = new Pawn();

        sender.set(x, y, mockPawn);

        state = ChessStates.PASSIVE;
    }

}