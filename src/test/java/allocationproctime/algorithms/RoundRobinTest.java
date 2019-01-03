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

public class RoundRobinTest {

    int amnt = 0;
    final String pathToSourceFile ="src/test/resources/test.csv";
    @Test
    public void constructorTest(){
        RoundRobin rr = new RoundRobin("test", true);
        assertEquals("test", rr.pathToSourceFile);
        assertEquals(Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses")), rr.amnt);
        assertEquals(Integer.parseInt(PropertiesHandler.getProp("sim.timeQuantumRR")), rr.timeQuantum);
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
    @Test
    public void executeProcessesTest() {
        RoundRobin rr = null;

        try {
            String pathToSourceFile ="src/test/resources/test.csv";
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));

            rr = new RoundRobin(pathToSourceFile, true);
            rr.createProcesses();
            rr.executeProcessesFCFS();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue("Process execution finish unproperly" ,amnt==rr.readyQueue.size());
        try {
            String pathToSourceFile ="src/test/resources/test.csv";
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));

            rr = new RoundRobin(pathToSourceFile, true);
            rr.createProcesses();
            rr.executeProcessesLCFS();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue("Process execution finish unproperly" ,amnt==rr.readyQueue.size());
    }
    @Test
    public void createProcessesTest() {
        String pathToSourceFile = "src/test/resources/test.csv";
        RoundRobin rr = new RoundRobin(pathToSourceFile, true);
        rr.createProcesses();
        assertTrue("Process creation finish unproperly" ,rr.amnt==rr.waitingQueue.size());
    }

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
    @After
    public void deleteTempFile() {
        File f = new File(pathToSourceFile);
        f.deleteOnExit();
    }

    @Before
    public void createTempSourceFile() throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(new File(pathToSourceFile)));
        amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
        for (int i = 0; i < amnt; i++) {
            String[] data = { Integer.toString(i), Integer.toString(1) };
            writer.writeNext(data);
        }
        writer.close();
    }

}