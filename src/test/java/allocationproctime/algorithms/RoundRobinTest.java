package allocationproctime.algorithms;

import allocationproctime.processes.Process;
import com.opencsv.CSVWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import propertieshandler.PropertiesHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for {@link RoundRobin} class
 * @author Arkadiusz Maruszczak
 *
 */
public class RoundRobinTest extends BaseAlgorithmTest{

    /**
     * Test for constructor {@link RoundRobin#RoundRobin(String, double, boolean...)}
     */
    @Test
    public void constructorTest(){
        RoundRobin rr = new RoundRobin("test", 0,true);
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

    }

    /**
     * Test for method {@link RoundRobin#executeProcesses()}
     */
    @Test
    public void executeProcessesTest() {
        RoundRobin rr = null;

        try {
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));

            rr = new RoundRobin(pathToSourceFile, 0.5, true);
            rr.createProcesses();
            rr.executeProcessesFCFS();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue("Process execution finish unproperly" ,amnt==rr.readyQueue.size());
        try {
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));

            rr = new RoundRobin(pathToSourceFile, 0.5, true);
            rr.createProcesses();
            rr.executeProcessesLCFS();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue("Process execution finish unproperly" ,amnt==rr.readyQueue.size());
    }

    /**
     * Test for method {@link RoundRobin#createProcesses()}
     */
    @Test
    public void createProcessesTest() {
        String pathToSourceFile = "src/test/resources/test.csv";
        RoundRobin rr = new RoundRobin(pathToSourceFile, 0, true);
        rr.createProcesses();
        assertTrue("Process creation finish unproperly" ,rr.amnt==rr.waitingQueue.size());
    }

    /**
     * Test for method {@link RoundRobin#createReport(String, boolean...)}
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