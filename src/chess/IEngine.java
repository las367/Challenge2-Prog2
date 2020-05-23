package chess;

public interface IEngine {
          
        /**
         * Sendet eine zufällige Zahl. Spieler mit der höheren Zahl beginnt das Spiel.
         * Zahl von 1-100. Bei gleicher Zahl wird wiederholt.
         * @throws OutOfStateException
         */
        void dice() throws OutOfStateException;

        /**
         * Wählt die Farbe und somit die Seite des Spielfeldes und wer beginnt. -> wait for color...
         * waitforcolor: true -> passive. false -> active
         * @param white true = white, false = black. true => ACTIVE, false => PASSIVE
         * @throws OutOfStateException
         */
        void chooseColor (boolean white) throws OutOfStateException;

        /**
         * Setzt die Figur von der Ausgangsposition auf die Zielposition. 
         */
        void move (int from, int to) throws OutOfStateException;

        /**
         * Setzt den Bauer von der Ausgangsposition auf die Zielposition und gibt den
         * gewünschten Figurentyp für die Umwandlung an.
         * @param from
         * @param figureType 1 = Dame, 2 = Turm, 3 = Springer
         * @throws OutOfStateException
         */
        void movePawnRule (int from, int figureType) throws OutOfStateException;

        /**
         * Führt eine Rochade mit dem Turm auf der angegebenen Position durch. 
         * @param from
         * @throws OutOfStateException
         */
        void rochade (int from) throws OutOfStateException;

        /**
         * Beendet das Spiel und schickt sendet den Grund dafür. 
         * @param reason 0 = checkmate, 1 = stalemate, 2 = abandon
         * @throws OutOfStateException
         */
        void endGame (int reason) throws OutOfStateException;

        /**
         * Spieler schlägt vor das Spiel zu beenden. -> wait.
         * @param reason 0 = draw, 1 = end game
         * @throws OutOfStateException
         */
        void proposalEnd (int reason) throws OutOfStateException;

        /**
         * Spieler antwortet auf die Anfrage das Spiel zu beenden.
         * @param accept true: wait -> end. false: wait -> passive
         * @throws OutOfStateException
         */
        void proposalAnswer (boolean accept) throws OutOfStateException;
}