package processes;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProcessTest {

    @Test
    public void defaultConstructorTest(){
        Process p = new Process();
        assertEquals(1, p.id);
        assertEquals(0, p.arrivalTime);
        assertEquals(0, p.awaitingTime);
        assertEquals(1, p.burstTime);
        assertEquals(0, p.processingTime);

    }

    @Test
    public void twoArgConstructorTest(){
        Process p = new Process(12, 13);
        assertEquals(12, p.id);
        assertEquals(13, p.burstTime);
    }

    @Test
    public void threeArgConstructorTest(){
        Process p = new Process(12, 13, 14);
        assertEquals(12, p.id);
        assertEquals(14, p.awaitingTime);
        assertEquals(13, p.burstTime);

    }

}