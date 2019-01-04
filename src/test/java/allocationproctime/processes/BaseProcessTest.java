package allocationproctime.processes;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Test class for {@link BaseProcess} class
 * @author Arkadiusz Maruszczak
 *
 */
public class BaseProcessTest {

    /**
     * Test for method {@link BaseProcess#getId()}
     */
    @Test
    public void getId() {
        Process p = new Process();
        assertEquals(0, (int) p.getId());
    }

    /**
     * Test for method {@link BaseProcess#getAwaitingTime()}
     */
    @Test
    public void getAwaitingTime() {
        Process p = new Process();
        assertEquals(p.awaitingTime, (double) p.getAwaitingTime(), 0.001);
    }

    /**
     * Test for method {@link BaseProcess#getBurstTime()}
     */
    @Test
    public void getBurstTime() {
        Process p = new Process();
        assertEquals(0, (double) p.getBurstTime(), 0.001);
    }

    /**
     * Test for method {@link BaseProcess#getProcessingTime()}
     */
    @Test
    public void getProcessingTime() {
        Process p = new Process();
        assertEquals(p.processingTime, (double) p.getProcessingTime(), 0.001);
    }

    /**
     * Test for method {@link BaseProcess#getArrivalTime()}
     */
    @Test
    public void getArrivalTime() {
        Process p = new Process();
        assertEquals(p.arrivalTime, (int)p.getArrivalTime());
    }

    /**
     * Test for method {@link BaseProcess#setId(int)}
     */
    @Test
    public void setId() {
        Random r = new Random();
        int idToSet = r.nextInt();
        Process p = new Process();
        p.setId(idToSet);
        assertEquals(idToSet, p.id);
    }

    /**
     * Test for method {@link BaseProcess#setAwaitingTime(double)}
     */
    @Test
    public void setAwaitingTime() {
        Random r = new Random();
        int awaitTimeToSet = r.nextInt();
        Process p = new Process();
        p.setAwaitingTime(awaitTimeToSet);
        assertEquals(awaitTimeToSet,p.awaitingTime, 0.001);
    }

    /**
     * Test for method {@link BaseProcess#setProcessingTime(double)}
     */
    @Test
    public void setProcessingTime() {
        Random r = new Random();
        int processingTimeToSet = r.nextInt();
        Process p = new Process();
        p.setProcessingTime(processingTimeToSet);
        assertEquals(processingTimeToSet, p.processingTime, 0.001);
    }

    /**
     * Test for method {@link BaseProcess#setArrivalTime(int)}
     */
    @Test
    public void setArrivalTime() {
        Random r = new Random();
        int arrivalTimeToSet = r.nextInt();
        Process p = new Process();
        p.setArrivalTime(arrivalTimeToSet);
        assertEquals(arrivalTimeToSet, p.arrivalTime);
    }

    /**
     * Test for method {@link BaseProcess#setBurstTime(double)}
     */
    @Test
    public void setBurstTime() {
        Random r = new Random();
        int burstTimeToSet = r.nextInt();
        Process p = new Process();
        p.setBurstTime(burstTimeToSet);
        assertEquals(burstTimeToSet, p.burstTime, 0.001);
    }
}