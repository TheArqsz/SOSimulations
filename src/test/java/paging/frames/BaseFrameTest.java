package paging.frames;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for {@link BaseFrame} class
 * @author Arkadiusz Maruszczak
 *
 */
public class BaseFrameTest {

    /**
     * Test for method {@link BaseFrame#getId()}
     */
    @Test
    public void getId() {
        Frame f = new Frame();
        assertEquals(0, (int) f.getId());
    }

    /**
     * Test for method {@link BaseFrame#setId(int)}
     */
    @Test
    public void setId() {
        Frame f = new Frame();
        f.setId(1);
        assertEquals(1, f.id);
    }

    /**
     * Test for method {@link BaseFrame#getAgeOfFrame()}
     */
    @Test
    public void getAgeOfFrame() {
        Frame f = new Frame();
        assertEquals(0, f.getAgeOfFrame());
    }

    /**
     * Test for method {@link BaseFrame#setAgeOfFrame(int)}
     */
    @Test
    public void setAgeOfFrame() {
        Frame f = new Frame();
        f.setAgeOfFrame(2);
        assertEquals(2, f.ageOfFrame);
    }

    /**
     * Test for method {@link BaseFrame#getAmountOfUses()}
     */
    @Test
    public void getAmountOfUses() {
        Frame f = new Frame(1,1,1);
        assertEquals(1, f.amountOfUses);
    }

    /**
     * Test for method {@link BaseFrame#setAmountOfUses(int)}
     */
    @Test
    public void setAmountOfUses() {
        Frame f = new Frame();
        f.setAmountOfUses(1);
        assertEquals(1, f.amountOfUses);
    }

    /**
     * Test for method {@link BaseFrame#equals(Object)}
     */
    @Test
    public void equals() {
        Frame f = new Frame(1,1,1);
        Frame f2 = new Frame(1,1,1);
        Frame f3 = new Frame(2,2,2);
        assertTrue(f.equals(f2));
        assertTrue(!f.equals(f3));
    }
}