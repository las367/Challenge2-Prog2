package chess;

import java.util.Random;

public class ChessEngine implements IEngine {

        Sender out;
        Receiver in;
        ChessStates state;

        // private object containing ids for each actions!
        private static class ActionIds {

                static int dice = 0;
                static int move = 1;
                static int movePawnRule = 2;
                static int rochade = 3;
                static int endGame = 4;
                static int proposalEnd = 5;
        }          

        // intialize in, out and start state here!
        public ChessEngine() {

                    state = ChessStates.START;
        }

        // to create strings with data and IDs appended at start.
        private String formatString (int actionId, int[] dataArray) {

                StringBuilder builder = new StringBuilder();

                builder.append(actionId);

                for ( int i : dataArray ) {
                        builder.append(i);
                }

                return builder.toString();
        }

        @Override
        public void dice() throws OutOfStateException {

                if ( state != ChessStates.START ) throw new OutOfStateException("Softwares are not yet connected!");

                Random random = new Random();
                int num = random.nextInt(101);       

                state = ChessStates.WAIT;
        }

	@Override
	public void chooseColor(boolean white) throws OutOfStateException {
                
                if ( state != ChessStates.WAIT || state != ChessStates.START ) throw new OutOfStateException("Softwares are not yet connected!")

                state = white ? ChessStates.ACTIVE : ChessStates.PASSIVE;
	}

	@Override
	public void move(int from, int to) throws OutOfStateException {

                if ( state != ChessStates.ACTIVE ) throw new OutOfStateException("Player is not on active state!");
                
                // TODO

                state = ChessStates.PASSIVE;
	}

	@Override
	public void movePawnRule(int from, int figureType) throws OutOfStateException {

		if ( state != ChessStates.ACTIVE ) throw new OutOfStateException("Player is not on active state!");
                
                // TODO
                
                state = ChessStates.PASSIVE;	
	}

	@Override
	public void rochade(int from) throws OutOfStateException {

                if ( state != ChessStates.ACTIVE ) throw new OutOfStateException("Player is not on active state!");
                
                // TODO
                switch ( from ) {
                        case 1: 
                                // Dame
                                break;
                        case 2:
                                // Turm
                                break;
                        case 3:
                                // Springer
                                break;
                        case 4:
                                // Laeufer
                                break;
                        default:
                                // error handling
                                break;
                }
                
                state = ChessStates.PASSIVE;
	}

	@Override
	public void endGame(int reason) throws OutOfStateException {
                
                if ( state != ChessStates.ACTIVE ) throw new OutOfStateException("Player is not on active state!");

                switch ( reason ) {
                        case 0: 
                                // checkmate!
                                break;
                        case 1:
                                // stalemate
                                break;
                        case 2:
                                // abandon
                                break;
                        default:
                                // error handling
                                break;
                }

                state = ChessStates.END;
	}

	@Override
	public void proposalEnd(int reason) throws OutOfStateException {
		
                if ( state != ChessStates.ACTIVE ) throw new OutOfStateException("Player is not on active state!");
                
                state = ChessStates.WAIT;
	}

	@Override
	public void proposalAnswer(boolean accept) throws OutOfStateException {

                if ( state != ChessStates.ACTIVE ) throw new OutOfStateException("Player is not on active state!");

                state = accept ? ChessStates.END : ChessStates.PASSIVE;
	}

}