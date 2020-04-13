import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.Date;

public class WriteAndReadDataSet {

        //Helper method to build the String on the preferred format (Name + Datum + Werte)
        static String createData(String name, long timeStamp, float[] value) {
            return name + "\n" + new Date(timeStamp) + "\n" + Arrays.toString(value) + "\n";
        }

        static void writeData(String filename, byte[] dataAsBytes) {
            try {
                FileOutputStream fos = new FileOutputStream(filename);
                fos.write(dataAsBytes);
            } catch (NoSuchFileException ex) {
                System.out.println("File Is Not Found!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        static String printData(int length, String filename) {
            try {

                //create new array of bytes to store the input byte stream
                byte[] datas = new byte[length];
                FileInputStream fis = new FileInputStream(filename);
                int i = 0;
                byte temp = (byte) fis.read();

                do {
                    datas[i] = temp;
                    i++;
                    temp = (byte) fis.read();
                } while(temp != -1);

                return new String(datas);

            } catch (FileNotFoundException e) {
                System.out.println("File is Not Found");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return "";
        }

    public static void main(String[] args) {

        // three example data sets
        String sensorName = "MyGoodOldSensor"; // does not change

        long[] timeStamps = new long[3];
        timeStamps[0] = System.currentTimeMillis();
        timeStamps[1] = timeStamps[0] + 1; // milli sec later
        timeStamps[2] = timeStamps[1] + 1000; // second later

        float[][] values = new float[3][];
        // 1st measure .. just one value
        float[] valueSet = new float[1];
        values[0] = valueSet;
        valueSet[0] = (float) 1.5; // example value 1.5 degrees

        // 2nd measure .. just three values
        valueSet = new float[3];
        values[1] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;
        valueSet[2] = (float) 2.1;

        // 3rd measure .. two values
        valueSet = new float[2];
        values[2] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;

        // write three data set into a file
        String data = "";

        for (int i = 0; i < timeStamps.length; i++) {

            //build formatted string using the helper method
            data += createData(sensorName, timeStamps[i], values[i]);
        }

        //convert string into an array of bytes.
        byte[] dataAsBytes = data.getBytes();
        final int dataAsBytesLength = dataAsBytes.length;
        String filename = "datensatz.txt";


        writeData(filename, dataAsBytes);

        // read data from file and print to System.out
        String dataAsString = printData(dataAsBytesLength, filename);
        System.out.println(dataAsString);
    }
}