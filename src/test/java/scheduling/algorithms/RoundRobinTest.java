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
 * Test class for {@link RoundRobinFCFS} class
 * @author Arkadiusz Maruszczak
 *
 */
public class RoundRobinTest extends BaseAlgorithmTest{

    /**
     * Test for constructor {@link RoundRobinFCFS#RoundRobinFCFS(String, double, boolean...)} and {@link RoundRobinLCFS#RoundRobinLCFS(String, double, boolean...)}
     */
    @Test
    public void constructorTest(){
        RoundRobinFCFS rr = new RoundRobinFCFS("test", 0,true);
        assertEquals("test", rr.pathToSourceFile);
        assertEquals(Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses")), rr.amnt);
        assertEquals(1, rr.timeQuantum, 0.001);
        assertNotEquals(null, rr.waitingQueue);
        assertNotEquals(null, rr.readyQueue);

        Boolean[] isReady = new Boolean[amnt];
        isReady=rr.isReady;
        boolean allCorrect = true;
        for(Boolean temp: isReady){
            if(temp){
                allCorrect=false;
            }
        }

        assertTrue("Not all values are correct", allCorrect);

        RoundRobinLCFS r = new RoundRobinLCFS("test", 0,true);
        assertEquals("test", r.pathToSourceFile);
        assertEquals(Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses")), r.amnt);
        assertEquals(1, r.timeQuantum, 0.001);
        assertNotEquals(null, r.waitingQueue);
        assertNotEquals(null, r.readyQueue);

        isReady = new Boolean[amnt];
        isReady=rr.isReady;
        allCorrect = true;
        for(Boolean temp: isReady){
            if(temp){
                allCorrect=false;
            }
        }

        assertTrue("Not all values are correct", allCorrect);

    }

    /**
     * Test for method {@link RoundRobinFCFS#executeProcesses()} and {@link RoundRobinLCFS#executeProcesses()}
     */
    @Test
    public void executeProcessesTest() {
        RoundRobinFCFS rrF = null;
        RoundRobinLCFS rrL = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));

            rrF = new RoundRobinFCFS(pathToSourceFile, 0.5, true);
            rrF.createProcesses();
            rrF.executeProcessesFCFS();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue("Process execution finish unproperly" ,amnt==rrF.readyQueue.size());
        try {
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));

            rrL = new RoundRobinLCFS(pathToSourceFile, 0.5, true);
            rrL.createProcesses();
            rrL.executeProcessesLCFS();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue("Process execution finish unproperly" ,amnt==rrL.readyQueue.size());
    }

    /**
     * Test for method {@link RoundRobinFCFS#createProcesses()}
     */
    @Test
    public void createProcessesTest() {
        String pathToSourceFile = "src/test/resources/test.csv";
        RoundRobinFCFS rr = new RoundRobinFCFS(pathToSourceFile, 0, true);
        rr.createProcesses();
        assertTrue("Process creation finish unproperly" ,rr.amnt==rr.waitingQueue.size());
    }

    /**
     * Test for method {@link RoundRobinFCFS#createReport(String, boolean...)}
     */
    @Test
    public void createReportTest() {

        double avgExpectedAwaitTime = 0;

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