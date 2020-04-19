import filepersistence.WriteAndReadDataSet;
import org.junit.Test;

import static org.junit.Assert.*;

public class WriteAndReadDataSetTest {

    //Zum Testen, wenn die Datei nich existiert
    @Test
    public void writeData() {
        String data = "Guten Morgen";
        WriteAndReadDataSet.writeData("aaa", data);
    }

    //Zum Testen, wenn die Datei nicht exiistiert
    @Test
    public void printData() {
        WriteAndReadDataSet.printData("non-existing-file.txt");
    }

    @Test
    public void printAndReadData() {
        String sampleText = "Moodle HTW", fileName = "test.txt";

        //read data
        WriteAndReadDataSet.writeData(fileName, sampleText);
        //read data from the written file
        String dataSet = WriteAndReadDataSet.printData(fileName);

        assertEquals(sampleText, dataSet);
    }
}