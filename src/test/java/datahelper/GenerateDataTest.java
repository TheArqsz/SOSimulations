package datahelper;

import org.junit.Test;
import propertieshandler.PropertiesHandler;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Test class for {@link GenerateData} class
 * @author Arkadiusz Maruszczak
 *
 */
public class GenerateDataTest {

    /**
     * Test for method {@link GenerateData#generateProcessesSourceFile(String)}
     */
    @Test
    public void generateProcessesSourceFile() {
        boolean doesFileExist = true;
        String path = null;
        try{
            path = PropertiesHandler.getProp("sim.pathToProcessesData")+ PropertiesHandler.getProp("sim.baseNameOfProcessFile") + 1 + PropertiesHandler.getProp("sim.extension");
            Reader f = Files.newBufferedReader(Paths.get(path));
            f.close();
        }catch(IOException e){
            doesFileExist=false;
        }
        assertTrue("File \"" + path + "\" does not exist.", doesFileExist);
    }

    /**
     * Test for method {@link GenerateData#generatePagesSourceFile(String)}
     */
    @Test
    public void generatePagesSourceFile() {
        boolean doesFileExist = true;
        String path = null;
        try{
            path = PropertiesHandler.getProp("sim.pathToPagesData")+ PropertiesHandler.getProp("sim.baseNameOfPageFile") + 1 + PropertiesHandler.getProp("sim.extension");
            Reader f = Files.newBufferedReader(Paths.get(path));
            f.close();
        }catch(IOException e){
            doesFileExist=false;
        }
        assertTrue("File \"" + path + "\" does not exist.", doesFileExist);
    }

    /**
     * Test for method {@link GenerateData#processArrayToDbl(String[])}
     */
    @Test
    public void processArrayToDbl() {
        String[] tempStr = {"1.0", "2.0", "3.0"};
        Double[] tempDbl = new Double[tempStr.length];
        for(int i=0; i<tempStr.length;i++){
            tempDbl[i]=Double.parseDouble(tempStr[i].trim());
            assertTrue(tempDbl[i].getClass().getName().equals(Double.class.getName()));
        }
    }

    /**
     * Test for method {@link GenerateData#processArrayToInt(String[])}
     */
    @Test
    public void processArrayToInt() {
        String[] tempStr = {"1", "2", "3"};
        Integer[] tempInt = new Integer[tempStr.length];
        for(int i=0; i<tempStr.length;i++){
            tempInt[i]=Integer.parseInt(tempStr[i].trim());
            assertTrue(tempInt[i].getClass().getName().equals(Integer.class.getName()));
        }
    }
}