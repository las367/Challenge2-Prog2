package sensorData;

import filepersistence.PersistenceException;
import filepersistence.SensorDataStorage;
import transmission.DataConnection;

import java.io.DataInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SensorDataReceiver {
    private final DataConnection connection;
    private final SensorDataStorage storage;
    private String sensorName;

    public SensorDataReceiver(DataConnection connection, SensorDataStorage storage) {
        this.connection = connection;
        this.storage = storage;
    }

    SensorDataStorage getStorage() {
        return storage;
    }

    public void receiveData() {

        try {

            System.out.println("Receiving");

            DataInputStream in = connection.getDataInputStream();
            String dataInString = in.readUTF();

            //split the data into 3 parts
            String datas[] = readLineByLine(dataInString);
            //index 2 is where the float values lie
            //first delete the brackets on start and end of the array
            String valuesInString = datas[2].substring(1, datas[2].length()-1);
            //then split the array from
            float[] values = stringToFloat(valuesInString.split(","));

            //index 1 should be where the date string lies
            long timestamp = Long.parseLong(datas[1]);

            //index 0 is where the sensor name lies
            this.sensorName = datas[0];

            try {
                storage.saveData(timestamp, values);
            } catch (PersistenceException ex) {
                System.out.println("Persistence Exception, something isn't right");
            }

        } catch (IOException ex) {
            System.out.println("Something unexpected occurred");
        }
    }

    public String getSensorName() {
        return sensorName;
    }

    public static String[] readLineByLine(String line) {
        String[] lines = line.split("\\r?\\n");
        //at index 0 = sensor name
        //index 1 = timestamp
        //index 2 = float values
        return lines;
    }

    public static float[] stringToFloat(String[] sarray) {
        float[] toRet = new float[sarray.length];
        for (int i = 0; i < sarray.length; i++) {
            toRet[i] = Float.parseFloat(sarray[i]);
        }
        return toRet;
    }

    /*public static Date stringToDate(String dateInString) {
        Date date = null;
        try {
            //default formatting for java date object? not sure about this
            //but found it somewhere on stackoverflow => https://stackoverflow.com/questions/27833515/how-to-get-default-date-pattern-in-java
            //https://docs.oracle.com/javase/8/docs/api/java/util/Date.html => toString() section
            String dateDefaultPattern = "EEE MMM dd HH:mm:ss zzz yyyy";
            date = new SimpleDateFormat(dateDefaultPattern).parse(dateInString);
        } catch (ParseException ex) {
            System.out.println("Error parsing date");
        }
        return date;
    } */
}
