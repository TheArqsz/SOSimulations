package propertieshandler;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class PropertiesHandlerTest {

    @Test
    public void getProp() {
        boolean isFileValid = true;

        String propFilePath = "src/main/resources/simulation.properties";
        Properties mainProperties = new Properties();

        FileInputStream file;
        try {
            file = new FileInputStream(propFilePath);
            mainProperties.load(file);

            file.close();
        } catch (IOException e) {
            isFileValid = false;
        }
        assertTrue("Properties file \"" + propFilePath +"\" does not exists", isFileValid);
    }
}