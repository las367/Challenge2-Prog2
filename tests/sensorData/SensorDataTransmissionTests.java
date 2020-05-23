package sensorData;

import filepersistence.EmptyDataException;
import filepersistence.NoSuchDataException;
import filepersistence.PersistenceException;
import filepersistence.SensorDataStorage;
import org.junit.Assert;
import org.junit.Test;
import transmission.DataConnection;
import transmission.DataConnector;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

public class SensorDataTransmissionTests {
    private static final int PORTNUMBER = 9876;

    @Test
    public void gutTransmissionTest() throws IOException {
        // create example data set
        String sensorName = "MyGoodOldSensor"; // does not change
        long timeStamp = System.currentTimeMillis();
        float[] valueSet = new float[3];
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;
        valueSet[2] = (float) 2.1;

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                              receiver side                                        //
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        // create storage
        // TODO: create object that implements SensorDataStorage
        SensorDataStorage dataStorage = new SensorDataStorage() {

            final String sensorName = "MyGoodOldSensor";
            LinkedList<float[]> valueSet = new LinkedList<>();
            LinkedList<Long> timestamps = new LinkedList<>();

            @Override
            public void saveData(long time, float[] values) throws PersistenceException {
                this.valueSet.add(values);
                this.timestamps.add(time);
            }

            @Override
            public boolean writeData(String filename) throws IOException, PersistenceException {

                try {
                    OutputStream out = new FileOutputStream(filename);
                    DataOutputStream dout = new DataOutputStream(out);

                    for (int i = 0; i < timestamps.toArray().length; i++) {
                        String data = createData(sensorName, this.timestamps.get(i), this.valueSet.get(i));
                        dout.write(data.getBytes());
                    }
                } catch (IOException ex) {
                    System.out.println("Something unexpected occurred");
                }
                return false;
            }

            @Override
            public int size() throws EmptyDataException {
                int size = this.valueSet.size() + this.timestamps.size();
                return 0;
            }

            @Override
            public void clear() throws EmptyDataException {
                if (!this.timestamps.isEmpty() && !this.valueSet.isEmpty()) {
                    this.valueSet.clear();
                    this.timestamps.clear();
                } else
                    throw new EmptyDataException("Storage is empty");
            }

            @Override
            public boolean contains(long time) throws EmptyDataException {
                return this.timestamps.contains(time);
            }

            @Override
            public float[] get(long time) throws EmptyDataException, NoSuchDataException {
                boolean doesContain = this.contains(time);

                if (doesContain) {
                    int index = timestamps.indexOf(time);
                    return valueSet.get(index);
                } else {
                    throw new NoSuchDataException("No data!");
                }
            }

            @Override
            public long getLastTimestamp() throws EmptyDataException {

                if (this.timestamps.isEmpty())
                    throw new EmptyDataException("No data!");
                else
                    return  this.timestamps.getLast();
            }

            @Override
            public float[] getLastValueSet() throws EmptyDataException {

                if (this.valueSet.isEmpty())
                    throw new EmptyDataException("No data!");
                else
                    return this.valueSet.getLast();
            }

            //Helper Method to transform the data into a preferred format
            private String createData(String name, long timeStamp, float[] value) {
                return name + "\n" + new Date(timeStamp) + "\n" + Arrays.toString(value) + "\n";
            }
        };

        //create one thread each for server and client and start them.

        // create connections
        DataConnector receiverConnection = new DataConnector(PORTNUMBER);
        Thread serverThread = new Thread(receiverConnection);
        serverThread.start();

        // create receiver
        SensorDataReceiver sensorDataReceiver = new SensorDataReceiver(receiverConnection, dataStorage);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                              sender side                                          //
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        // create connections
        DataConnector senderConnection = new DataConnector("localhost", PORTNUMBER);
        Thread clientThread = new Thread(senderConnection);
        // senderConnection.run();
        clientThread.start();

        // create sender
        SensorDataSender sensorDataSender = new SensorDataSender(senderConnection);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //                               execute communication and test                                      //
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        // send data with TCP
        sensorDataSender.sendData(sensorName, timeStamp, valueSet);

        //!! Before: no receive data.
        sensorDataReceiver.receiveData();

        // test if stored
        SensorDataStorage dataStorageReceived = sensorDataReceiver.getStorage();

        boolean isValueSaved;

        //check
        try {

            isValueSaved = dataStorage.contains(timeStamp);

            if (isValueSaved) {
                System.out.println("data is saved");
            } else {
                System.out.println("data is not saved");
            }
        } catch (EmptyDataException ex) {
            System.out.println("there's no data in the storage");
        }

        //sensor name => no error throw
        String sensorNameReceived = sensorDataReceiver.getSensorName();
        Assert.assertEquals(sensorName, sensorNameReceived);

        //catching empty data exception => try-catch block needed.

        try {

            long timeStampReceived = dataStorageReceived.getLastTimestamp();
            float[] valueSetReceived = dataStorageReceived.getLastValueSet();

            Assert.assertEquals(timeStamp, timeStampReceived);
            Assert.assertArrayEquals(valueSet, valueSetReceived, 0.01f);
        } catch (EmptyDataException ex) {
            System.out.println("No Data is saved, errror");
        }

        // TODO: How to stop the thread here?
    }

    @Test
    public void testHelper() {

        float[] fl = { 1.2f, 3, 4.2f, 5 };
        String data = Arrays.toString(fl) + "\n Sample Data\n";
        String[] datasInArray = SensorDataReceiver.readLineByLine(data);

        //index 0 should be the array fl...
        String flInString = datasInArray[0].substring(1, datasInArray[0].length() - 1);
        float[] floatParsed = SensorDataReceiver.stringToFloat(flInString.split(","));

        Assert.assertArrayEquals(fl, floatParsed, 0f);
    }
}
