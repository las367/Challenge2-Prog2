package transmission;

import org.junit.Assert;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TransmissionTests {
    private static final int PORTNUMBER = 9876;
    private static final int TEST_INT = 42;

    @Test
    public void gutConnectionTest1() throws IOException {
        // open server side
        DataConnector serverSide = new DataConnector(PORTNUMBER);
        Thread serverThread = new Thread(serverSide);
        serverThread.start();

        // open client side
        DataConnector clientSide = new DataConnector("localhost", PORTNUMBER);
        Thread clientThread = new Thread(clientSide);
        clientSide.run();

        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;

        while ( true ) {

            try {

                dataOutputStream = clientSide.getDataOutputStream();
                dataInputStream = serverSide.getDataInputStream();
                break;
            } catch ( NullPointerException ex ) {

            }
        }

        dataOutputStream.writeInt(TEST_INT);
        int readValue = dataInputStream.readInt();

        Assert.assertEquals(TEST_INT, readValue);
    }


}
