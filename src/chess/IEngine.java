package chess;

public interface IEngine {
          
          void dice() throws OutOfStateException;

          void move (int fromX, int fromY, int toX, int toY);

          void movePawnRule (int fromX, int fromY, int toX, int toY, int figureType);

          void rochade (int fromX, int fromY);

          void abandond ();

          void matt ();

          void error (String errMessage);

          void listen ();
}