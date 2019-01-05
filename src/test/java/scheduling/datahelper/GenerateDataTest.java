package scheduling.datahelper;

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
     * Test for method {@link GenerateData#generateSourceFile(String)}
     */
    @Test
    public void generateFile() {
        boolean doesFileExist = true;
        String path = null;
        try{
            path = PropertiesHandler.getProp("sim.pathToProcessesData")+ PropertiesHandler.getProp("sim.baseNameOfFile") + 1 + PropertiesHandler.getProp("sim.extension");
            Reader f = Files.newBufferedReader(Paths.get(path));
            f.close();
        }catch(IOException e){
            doesFileExist=false;
        }
        assertTrue("File \"" + path + "\" does not exist.", doesFileExist);
    }
}