import java.io.FileInputStream;
import java.io.FileOutputStream;

import static org.junit.Assert.*;

public class WriteAndReadDataSetTest {

    //Zum Testen, wenn die Datei nich existiert
    @org.junit.Test
    public void writeData() {
        byte[] sampleData = {1, 2, 3, 2, 1, 4, 5, 2, 1, 7};
        WriteAndReadDataSet.writeData("non-existing-file.txt", sampleData);
    }

    //Zum Testen, wenn die Datei nicht exiistiert
    @org.junit.Test
    public void printData() {
        byte[] sampleData = {1, 2, 3, 2, 1, 4, 5, 2, 1, 7};
        WriteAndReadDataSet.writeData("non-existing-file.txt", sampleData);
    }
}