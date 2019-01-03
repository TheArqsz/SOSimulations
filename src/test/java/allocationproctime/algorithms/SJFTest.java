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

public class SJFTest {

    int amnt = 0;
    private Process[] readyQueue;

    final String pathToSourceFile ="src/test/resources/test.csv";

    @Test
    public void constructorTest(){
        SJF sjf = new SJF("test", true);
        assertEquals("test", sjf.pathToSourceFile);
        assertEquals(Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses")), sjf.amnt);
        assertNotEquals(null, sjf.waitingQueue);
        assertNotEquals(null, sjf.readyQueue);
    }
    @Test
    public void executeProcessesTest() {
        SJF sjf = null;

        try {
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));

            sjf = new SJF(pathToSourceFile, true);
            sjf.createProcesses();
            sjf.executeProcesses();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue("Process execution finish unproperly" ,amnt==sjf.readyQueue.length);
    }
    @Test
    public void createProcessesTest() {
        SJF sjf = new SJF(pathToSourceFile, true);
        sjf.createProcesses();
        boolean isSorted = true;
        for(int i=0; i<amnt-1;i++){
            if(sjf.waitingQueue.get(i).getBurstTime()>sjf.waitingQueue.get(i+1).getBurstTime()){
                isSorted=false;
                break;
            }
        }

        assertTrue("Process creation finish unproperly - sorting" , isSorted);
        assertTrue("Process creation finish unproperly" ,amnt==sjf.waitingQueue.size());
    }

    @Test
    public void createReportTest() {

        float avgExpectedAwaitTime = 0;
        float avgExpectedProcessingTime = 0;

        SJF sjf = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));

            sjf = new SJF(pathToSourceFile, true);
            sjf.createProcesses();
            sjf.executeProcesses();
            reader.close();


            Process[] temp = sjf.readyQueue;
            for (int i = 0; i < amnt; i++) {
                avgExpectedAwaitTime += temp[i].getAwaitingTime();
            }
            avgExpectedAwaitTime = avgExpectedAwaitTime / sjf.amnt;

            for (int i = 0; i < amnt; i++) {
                avgExpectedProcessingTime += temp[i].getProcessingTime();
            }
            avgExpectedProcessingTime = avgExpectedProcessingTime / sjf.amnt;
            sjf.createReport("testFCFS", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(avgExpectedAwaitTime, sjf.avgAwaitTime, 0.00001);
        assertEquals(avgExpectedProcessingTime, sjf.avgProcessingTime, 0.00001);
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