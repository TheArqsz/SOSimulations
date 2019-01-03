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

public class LCFSTest {
    int amnt = 0;
    final String pathToSourceFile ="src/test/resources/test.csv";
    @Test
    public void constructorTest(){
        LCFS lcfs = new LCFS("test", true);
        assertEquals("test", lcfs.pathToSourceFile);
        assertEquals(Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses")), lcfs.amnt);
        assertNotEquals(null, lcfs.waitingQueue);
        assertNotEquals(null, lcfs.readyQueue);
    }
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
    @Test
    public void createProcessesTest() {
        String pathToSourceFile = PropertiesHandler.getProp("sim.pathToProcessesData")+ PropertiesHandler.getProp("sim.baseNameOfFile") + 1 + PropertiesHandler.getProp("sim.extension");
        LCFS fcfs = new LCFS(pathToSourceFile, true);
        fcfs.createProcesses();
        assertTrue("Process creation finish unproperly" ,fcfs.amnt==fcfs.waitingQueue.size());
    }

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