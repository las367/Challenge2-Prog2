package chess;

import java.util.Random;

public class ChessEngine implements IEngine {

        Sender out;
        Receiver in;
        ChessStates state;

          //private object containing ids for each actions!
        private static class ActionIds {

                static int dice = 0;
                static int move = 1;
                static int movePawnRule = 2;
                static int rochade = 3;
                static int endGame = 4;
                static int proposalEnd = 5;
        }          

          //intialize in, out and start state here!
        public ChessEngine() {

                    state = ChessStates.START;
          }

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

                if ( state != ChessStates.CONNECTED ) throw new OutOfStateException("Softwares are not yet connected!");

                Random random = new Random();
                int num = random.nextInt(101);       
        }

	@Override
	public void chooseColor(boolean white) throws OutOfStateException {
			// TODO Auto-generated method stub
			
	}

	@Override
	public void move(int from, int to) throws OutOfStateException {
			// TODO Auto-generated method stub
			
	}

	@Override
	public void movePawnRule(int from, int figureType) throws OutOfStateException {
			// TODO Auto-generated method stub
			
	}

	@Override
	public void rochade(int from) throws OutOfStateException {
			// TODO Auto-generated method stub
			
	}

	@Override
	public void endGame(int reason) throws OutOfStateException {
			// TODO Auto-generated method stub
			
	}

	@Override
	public void proposalEnd(int reason) throws OutOfStateException {
			// TODO Auto-generated method stub
			
	}

	@Override
	public void proposalAnswer(boolean accept) throws OutOfStateException {
			// TODO Auto-generated method stub
			
	}

}