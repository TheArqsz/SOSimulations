package allocationproctime.algorithms;

import allocationproctime.processes.Process;
import com.opencsv.CSVWriter;
import org.junit.*;
import propertieshandler.PropertiesHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class FCFSTest {
    int amnt = 0;
    final String pathToSourceFile ="src/test/resources/test.csv";
    @Test
    public void constructorTest(){
        FCFS fcfs = new FCFS("test", true);
        assertEquals("test", fcfs.pathToSourceFile);
        assertEquals(Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses")), fcfs.amnt);
        assertNotEquals(null, fcfs.waitingQueue);
        assertNotEquals(null, fcfs.readyQueue);
    }
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
    @Test
    public void createProcessesTest() {
        String pathToSourceFile = PropertiesHandler.getProp("sim.pathToProcessesData")+ PropertiesHandler.getProp("sim.baseNameOfFile") + 1 + PropertiesHandler.getProp("sim.extension");
        FCFS fcfs = new FCFS(pathToSourceFile, true);
        fcfs.createProcesses();
        assertTrue("Process creation finish unproperly" ,fcfs.amnt==fcfs.waitingQueue.size());
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