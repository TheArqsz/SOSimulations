package scheduling.algorithms;

import scheduling.processes.Process;
import org.junit.*;
import propertieshandler.PropertiesHandler;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Test class for {@link FCFS} class
 * @author Arkadiusz Maruszczak
 *
 */
public class FCFSTest extends BaseAlgorithmTest{

    /**
     * Test for constructor {@link FCFS#FCFS(String, boolean...)}
     */
    @Test
    public void constructorTest(){
        FCFS fcfs = new FCFS("test", true);
        assertEquals("test", fcfs.pathToSourceFile);
        assertEquals(Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses")), fcfs.amnt);
        assertNotEquals(null, fcfs.waitingQueue);
        assertNotEquals(null, fcfs.readyQueue);
    }

    /**
     * Test for method {@link FCFS#executeProcesses()}
     */
    @Test
    public void executeProcessesTest() {
        FCFS fcfs = null;

        try {
            String pathToSourceFile ="src/test/resources/test.csv";
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));

            fcfs = new FCFS(pathToSourceFile, true);
            fcfs.createProcesses();
            fcfs.executeProcesses();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue("Process execution finish unproperly" ,amnt==fcfs.readyQueue.size());
    }

    /**
     * Test for method {@link FCFS#createProcesses()}
     */
    @Test
    public void createProcessesTest() {
        String pathToSourceFile = PropertiesHandler.getProp("sim.pathToProcessesData")+ PropertiesHandler.getProp("sim.baseNameOfFile") + 1 + PropertiesHandler.getProp("sim.extension");
        FCFS fcfs = new FCFS(pathToSourceFile, true);
        fcfs.createProcesses();
        assertTrue("Process creation finish unproperly" ,fcfs.amnt==fcfs.waitingQueue.size());
    }

    /**
     * Test for method {@link FCFS#createReport(String, boolean...)}
     */
    @Test
    public void createReportTest() {

        float avgExpectedAwaitTime = 0;

        FCFS fcfs = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));

            fcfs = new FCFS(pathToSourceFile, true);
            fcfs.createProcesses();
            fcfs.executeProcesses();
            reader.close();


            List<Process> temp = new ArrayList<>();
            temp=fcfs.readyQueue;
            for (int i = 0; i < amnt; i++) {
                avgExpectedAwaitTime += temp.get(i).getAwaitingTime();
            }
            avgExpectedAwaitTime = avgExpectedAwaitTime / fcfs.amnt;
            fcfs.createReport("testFCFS", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(avgExpectedAwaitTime, fcfs.avgAwaitTime, 0.00001);
    }



}