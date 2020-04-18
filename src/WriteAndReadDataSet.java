import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.Date;

public class WriteAndReadDataSet {

        //Helper method to build the String on the preferred format (Name + Datum + Werte)
        static String createData(String name, long timeStamp, float[] value) {
            return name + "\n" + new Date(timeStamp) + "\n" + Arrays.toString(value) + "\n";
        }

        static void writeData(String filename, String data) {

            //Change Dataset to an array of bytes
            byte[] dataAsBytes = data.getBytes();
            try {
                FileOutputStream fos = new FileOutputStream(filename);
                DataOutputStream dos = new DataOutputStream(fos);
                dos.write(dataAsBytes);
                dos.close();
                fos.close();
            } catch (FileNotFoundException ex) {
                System.out.println(filename + "is a directory!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        static String printData(String filename) {

            String data = "";

            try {

                //create new array of bytes to store the input byte stream
                FileInputStream fis = new FileInputStream(filename);
                DataInputStream dis = new DataInputStream(fis);

                while (true) {
                    try {
                        byte nextDataAsByte = dis.readByte();
                        data += (char) nextDataAsByte;
                    } catch (EOFException ex) {
                        break; //end reached
                    } catch (IOException ex) {
                        break;
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("File is Not Found");
            }

            return data;
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
        String filename = "datensatz.txt";


        writeData(filename, data);

        // read data from file and print to System.out
        String dataAsString = printData(filename);
        System.out.println(dataAsString);
    }
}