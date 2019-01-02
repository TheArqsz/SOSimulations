package datahelper;

import org.junit.Test;
import propertieshandler.PropertiesHandler;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class GenerateDataTest {

    @Test
    public void generateFile() {
        boolean doesFileExist = true;
        try{
            Reader f = Files.newBufferedReader(Paths.get(PropertiesHandler.getProp("sim.pathToProcessesData")));
            f.close();
        }catch(IOException e){
            doesFileExist=false;
        }
        assertTrue("File \"" + PropertiesHandler.getProp("sim.pathToProcessesData") + "\" does not exist.", doesFileExist);
    }
}