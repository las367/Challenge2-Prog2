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
                    static int abandon = 4;
                    static int matt = 5;
                    static int error = 6;
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
          public void move(int fromX, int fromY, int toX, int toY) {
                    // TODO Auto-generated method stub

          }

          @Override
          public void movePawnRule(int fromX, int fromY, int toX, int toY, int figureType) {
                    // TODO Auto-generated method stub

          }

          @Override
          public void rochade(int fromX, int fromY) {
                    // TODO Auto-generated method stub

          }

          @Override
          public void abandond() {
                    // TODO Auto-generated method stub

          }

          @Override
          public void matt() {
                    // TODO Auto-generated method stub

          }

          @Override
          public void error(String errMessage) {
                    // TODO Auto-generated method stub

          }

          @Override
          public void listen() {
                    // TODO Auto-generated method stub

          }
          


}