import java.io.IOException;
/**
 * We assume: Each sensor gets its own storage engine. There wont be a parameter
 * sensor name.
 */
public interface SensorDataStorage {

    /**
     * This method can be called by a sensor to save a data set.
     * @param time UNIX time when measurement took place
     * @param values sensor data
     * @throws PersistenceException if something unexpected happened. Insufficient right, medium broken, offline..
     */
    void saveData(long time, float[] values) throws PersistenceException;

    /**
     * method to write the data set into a text file
     * @param filename name of the file, that the machine would write into..
     * @return true if successful, false if error occurred
     * @throws IOException id there is an error on Input/Output
     * @throws PersistenceException if something unexpected happened. Insufficient right, medium broken, offline..
     */
    boolean writeData(String filename) throws IOException, PersistenceException;

    /**
     * Method to return the size of data in the machine
     * @throws EmptyDataException if there are no data saved by the machine
     * @return size of the data in byte.
     */
    int size() throws  EmptyDataException;

    /**
     * method to clear all data saved inside the machine
     * @return returns true if successful and false if error occurred
     * @throws EmptyDataException if there are no data saved by the machine
     */
    boolean clear() throws EmptyDataException;

    /**
     * method to get a data set on a specified timestamp
     * @param time timestamp specified by user to get a particular data set
     * @return an array of data set
     * @throws EmptyDataException if there are no data saved by the machine
     * @throws NoSuchDataException if there are no data set saved on the particular timestamp.
     */
    float[] contains(long time) throws EmptyDataException, NoSuchDataException;
}
