package allocationproctime.processes;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class BaseProcessTest {
    @Test
    public void getId() {
        Process p = new Process();
        assertEquals(0, (int) p.getId());
    }

    @Test
    public void getAwaitingTime() {
        Process p = new Process();
        assertEquals(p.awaitingTime, (int) p.getAwaitingTime());
    }

    @Test
    public void getBurstTime() {
        Process p = new Process();
        assertEquals(1, (int) p.getBurstTime());
    }

    @Test
    public void getProcessingTime() {
        Process p = new Process();
        assertEquals(p.processingTime, (int) p.getProcessingTime());
    }

    @Test
    public void getArrivalTime() {
        Process p = new Process();
        assertEquals(p.arrivalTime, (int)p.getArrivalTime());
    }

    @Test
    public void setId() {
        Random r = new Random();
        int idToSet = r.nextInt();
        Process p = new Process();
        p.setId(idToSet);
        assertEquals(idToSet, p.id);
    }

    @Test
    public void setAwaitingTime() {
        Random r = new Random();
        int awaitTimeToSet = r.nextInt();
        Process p = new Process();
        p.setAwaitingTime(awaitTimeToSet);
        assertEquals(awaitTimeToSet,p.awaitingTime);
    }

    @Test
    public void setProcessingTime() {
        Random r = new Random();
        int processingTimeToSet = r.nextInt();
        Process p = new Process();
        p.setProcessingTime(processingTimeToSet);
        assertEquals(processingTimeToSet, p.processingTime);
    }

    @Test
    public void setArrivalTime() {
        Random r = new Random();
        int arrivalTimeToSet = r.nextInt();
        Process p = new Process();
        p.setArrivalTime(arrivalTimeToSet);
        assertEquals(arrivalTimeToSet, p.arrivalTime);
    }

    @Test
    public void setBurstTime() {
        Random r = new Random();
        int burstTimeToSet = r.nextInt();
        Process p = new Process();
        p.setBurstTime(burstTimeToSet);
        assertEquals(burstTimeToSet, p.burstTime);
    }
}