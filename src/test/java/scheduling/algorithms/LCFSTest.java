package scheduling.algorithms;

import scheduling.processes.Process;
import org.junit.Test;
import propertieshandler.PropertiesHandler;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for {@link LCFS} class
 * @author Arkadiusz Maruszczak
 *
 */
public class LCFSTest extends BaseAlgorithmTest{

    /**
     * Test for constructor {@link LCFS#LCFS(String, boolean...)}
     */
    @Test
    public void constructorTest(){
        LCFS lcfs = new LCFS("test", true);
        assertEquals("test", lcfs.pathToSourceFile);
        assertEquals(Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses")), lcfs.amnt);
        assertNotEquals(null, lcfs.waitingQueue);
        assertNotEquals(null, lcfs.readyQueue);
    }

    /**
     * Test for method {@link LCFS#executeProcesses()}
     */
    @Test
    public void executeProcessesTest() {
        LCFS lcfs = null;

        try {
            String pathToSourceFile ="src/test/resources/test.csv";
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));

            lcfs = new LCFS(pathToSourceFile, true);
            lcfs.createProcesses();
            lcfs.executeProcesses();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue("Process execution finish unproperly" ,amnt==lcfs.readyQueue.size());
    }

    /**
     * Test for method {@link LCFS#createProcesses()}
     */
    @Test
    public void createProcessesTest() {
        String pathToSourceFile = PropertiesHandler.getProp("sim.pathToProcessesData")+ PropertiesHandler.getProp("sim.baseNameOfProcessFile") + 1 + PropertiesHandler.getProp("sim.extension");
        LCFS lcfs = new LCFS(pathToSourceFile, true);
        lcfs.createProcesses();
        assertTrue("Process creation finish unproperly" ,lcfs.amnt==lcfs.waitingQueue.size());
    }

    /**
     * Test for method {@link LCFS#createReport(String, boolean...)} ()}
     */
    @Test
    public void createReportTest() {

        float avgExpectedAwaitTime = 0;

        LCFS lcfs = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));

            lcfs = new LCFS(pathToSourceFile, true);
            lcfs.createProcesses();
            lcfs.executeProcesses();
            reader.close();

            List<Process> temp = new ArrayList<>();
            temp=lcfs.readyQueue;
            for (int i = 0; i < amnt; i++) {
                avgExpectedAwaitTime += temp.get(i).getAwaitingTime();
            }
            avgExpectedAwaitTime = avgExpectedAwaitTime / amnt;
            lcfs.createReport("testFCFS", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(avgExpectedAwaitTime, lcfs.avgAwaitTime, 0);
    }
}