package sensorData;

import transmission.DataConnection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class SensorDataSender {
    private final DataConnection connection;

    public SensorDataSender(DataConnection connection) {
        this.connection = connection;
    }

    public void sendData(String name, long time, float[] values) throws IOException {

        String data = createData(name, time, values);

        try {

            System.out.println("Sending data..");
            DataOutputStream out = connection.getDataOutputStream();
            out.writeUTF(data);
            out.close();
        } catch (IOException ex) {
            System.out.println("Something unexpected happened");
        } catch ( NullPointerException ex ) {

            System.err.println(ex);
        }
    }

    //Helper Method to transform the data into a preferred format
    private String createData(String name, long timeStamp, float[] value) {
        return name + "\n" + timeStamp + "\n" + Arrays.toString(value) + "\n";
    }
}