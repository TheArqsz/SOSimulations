package allocationproctime.algorithms;

import org.junit.Test;
import propertieshandler.PropertiesHandler;

import static org.junit.Assert.*;

public class FCFSTest {

    @Test
    public void constructorTest(){
        FCFS fcfs = new FCFS("test", true);
        assertEquals("test", fcfs.pathToSourceFile);
        assertEquals(Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses")), fcfs.amnt);
        assertNotEquals(null, fcfs.waitingQueue);
        assertNotEquals(null, fcfs.readyQueue);
    }
    @Test
    public void executeProcesses() {
        String pathToSourceFile = PropertiesHandler.getProp("sim.pathToProcessesData")+ PropertiesHandler.getProp("sim.baseNameOfFile") + 1 + PropertiesHandler.getProp("sim.extension");
        FCFS fcfs = new FCFS(pathToSourceFile, true);
        fcfs.createProcesses();
        fcfs.executeProcesses();
        assertTrue("Process execution finish unproperly" ,fcfs.amnt==fcfs.readyQueue.size());
    }
    @Test
    public void createProcesses() {
        String pathToSourceFile = PropertiesHandler.getProp("sim.pathToProcessesData")+ PropertiesHandler.getProp("sim.baseNameOfFile") + 1 + PropertiesHandler.getProp("sim.extension");
        FCFS fcfs = new FCFS(pathToSourceFile, true);
        fcfs.createProcesses();
        assertTrue("Process creation finish unproperly" ,fcfs.amnt==fcfs.waitingQueue.size());
    }

    @Test
    public void createReport() {
    }
}