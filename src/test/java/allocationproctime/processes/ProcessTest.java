package allocationproctime.processes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for {@link Process} class
 * @author Arkadiusz Maruszczak
 *
 */
public class ProcessTest {

    /**
     * Test for constructor {@link Process#Process()}
     */
    @Test
    public void defaultConstructorTest(){
        Process p = new Process();
        assertEquals(0, p.id);
        assertEquals(0, p.arrivalTime);
        assertEquals(0, p.awaitingTime, 0.001);
        assertEquals(0, p.burstTime, 0.001);
        assertEquals(0, p.processingTime, 0.001);

    }

    /**
     * Test for constructor {@link Process#Process(int, double)}
     */
    @Test
    public void twoArgConstructorTest(){
        Process p = new Process(12, 13);
        assertEquals(12, p.id);
        assertEquals(13, p.burstTime, 0.001);
    }

    /**
     * Test for constructor {@link Process#Process(int, double, double)}
     */
    @Test
    public void threeArgConstructorTest(){
        Process p = new Process(12, 13, 14);
        assertEquals(12, p.id);
        assertEquals(14, p.awaitingTime, 0.001);
        assertEquals(13, p.burstTime, 0.001);

    }
}