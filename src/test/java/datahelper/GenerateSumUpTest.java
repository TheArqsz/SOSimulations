package datahelper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for {@link GenerateSumUp} class
 * @author Arkadiusz Maruszczak
 *
 */
public class GenerateSumUpTest {

    /**
     * Test for constructor {@link GenerateSumUp#GenerateSumUp()}
     */
    @Test
    public void constructorTest(){
        GenerateSumUp temp = new GenerateSumUp();
        assertNotNull("Constructor didn't initialize properly", temp.algorithmTimesMap);
        assertNotNull("Constructor didn't initialize properly", temp.algorithmTimesMap.get("FCFS"));
        assertNotNull("Constructor didn't initialize properly", temp.algorithmTimesMap.get("LCFS"));
        assertNotNull("Constructor didn't initialize properly", temp.algorithmTimesMap.get("LCFS"));
        assertNotNull("Constructor didn't initialize properly", temp.algorithmTimesMap.get("RoundRobin FCFS"));
        assertNotNull("Constructor didn't initialize properly", temp.algorithmTimesMap.get("RoundRobin LCFS"));
        assertEquals(0, temp.algorithmTimesMap.get("FCFS").getLeft(), 0.001);
        assertEquals(0, temp.algorithmTimesMap.get("FCFS").getRight(), 0.001);
        assertEquals(0, temp.algorithmTimesMap.get("LCFS").getLeft(), 0.001);
        assertEquals(0, temp.algorithmTimesMap.get("LCFS").getRight(), 0.001);
        assertEquals(0, temp.algorithmTimesMap.get("SJF").getLeft(), 0.001);
        assertEquals(0, temp.algorithmTimesMap.get("SJF").getRight(), 0.001);
        assertEquals(0, temp.algorithmTimesMap.get("RoundRobin FCFS").getLeft(), 0.001);
        assertEquals(0, temp.algorithmTimesMap.get("RoundRobin FCFS").getRight(), 0.001);
        assertEquals(0, temp.algorithmTimesMap.get("RoundRobin LCFS").getLeft(), 0.001);
        assertEquals(0, temp.algorithmTimesMap.get("RoundRobin LCFS").getRight(), 0.001);

    }

    /**
     * Test for method {@link GenerateSumUp#getAvgAwaitTimeSum(String)}
     */
    @Test
    public void getAvgAwaitTimeSumTest() {
        GenerateSumUp temp = new GenerateSumUp();
        assertEquals(temp.algorithmTimesMap.get("FCFS").getRight(), temp.getAvgAwaitTimeSum("FCFS"),  0.001);
        assertEquals( temp.algorithmTimesMap.get("LCFS").getRight(), temp.getAvgAwaitTimeSum("LCFS"),  0.001);
        assertEquals( temp.algorithmTimesMap.get("SJF").getRight(), temp.getAvgAwaitTimeSum("SJF"),  0.001);
        assertEquals( temp.algorithmTimesMap.get("RoundRobin FCFS").getRight(), temp.getAvgAwaitTimeSum("RoundRobin FCFS"),  0.001);
        assertEquals( temp.algorithmTimesMap.get("RoundRobin LCFS").getRight(), temp.getAvgAwaitTimeSum("RoundRobin LCFS"),  0.001);
    }

    /**
     * Test for method {@link GenerateSumUp#setAvgAwaitTimeSum(double, String)}
     */
    @Test
    public void setAvgAwaitTimeSumTest() {
        GenerateSumUp temp = new GenerateSumUp();
        temp.setAvgAwaitTimeSum(1,"FCFS");
        temp.setAvgAwaitTimeSum(1,"LCFS");
        temp.setAvgAwaitTimeSum(1,"SJF");
        temp.setAvgAwaitTimeSum(1,"RoundRobin FCFS");
        temp.setAvgAwaitTimeSum(1,"RoundRobin LCFS");
        assertEquals(1, temp.algorithmTimesMap.get("FCFS").getLeft(),  0.001);
        assertEquals( 1, temp.algorithmTimesMap.get("LCFS").getLeft(),  0.001);
        assertEquals( 1, temp.algorithmTimesMap.get("SJF").getLeft(), 0.001);
        assertEquals( 1, temp.algorithmTimesMap.get("RoundRobin FCFS").getLeft(), 0.001);
        assertEquals( 1, temp.algorithmTimesMap.get("RoundRobin LCFS").getLeft(), 0.001);
    }

    /**
     * Test for method {@link GenerateSumUp#getAvgProccessingTimeSum(String)}
     */
    @Test
    public void getAvgProccessingTimeSumTest() {
        GenerateSumUp temp = new GenerateSumUp();
        assertEquals(temp.algorithmTimesMap.get("FCFS").getRight(), temp.getAvgProccessingTimeSum("FCFS"),  0.001);
        assertEquals( temp.algorithmTimesMap.get("LCFS").getRight(), temp.getAvgProccessingTimeSum("LCFS"),  0.001);
        assertEquals( temp.algorithmTimesMap.get("SJF").getRight(), temp.getAvgProccessingTimeSum("SJF"),  0.001);
        assertEquals( temp.algorithmTimesMap.get("RoundRobin FCFS").getRight(), temp.getAvgProccessingTimeSum("RoundRobin FCFS"),  0.001);
        assertEquals( temp.algorithmTimesMap.get("RoundRobin LCFS").getRight(), temp.getAvgProccessingTimeSum("RoundRobin LCFS"),  0.001);
    }

    /**
     * Test for method {@link GenerateSumUp#setAvgProccessingTimeSum(double, String)}
     */
    @Test
    public void setAvgProccessingTimeSumTest() {
        GenerateSumUp temp = new GenerateSumUp();
        temp.setAvgProccessingTimeSum(1,"FCFS");
        temp.setAvgProccessingTimeSum(1,"LCFS");
        temp.setAvgProccessingTimeSum(1,"SJF");
        temp.setAvgProccessingTimeSum(1,"RoundRobin FCFS");
        temp.setAvgProccessingTimeSum(1,"RoundRobin LCFS");
        assertEquals(1, temp.algorithmTimesMap.get("FCFS").getRight(),  0.001);
        assertEquals( 1, temp.algorithmTimesMap.get("LCFS").getRight(),  0.001);
        assertEquals( 1, temp.algorithmTimesMap.get("SJF").getRight(), 0.001);
        assertEquals( 1, temp.algorithmTimesMap.get("RoundRobin FCFS").getRight(), 0.001);
        assertEquals( 1, temp.algorithmTimesMap.get("RoundRobin LCFS").getRight(), 0.001);

    }
}