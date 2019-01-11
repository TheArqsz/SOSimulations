package paging.frames;

import org.junit.Test;

import static org.junit.Assert.*;

public class FrameTest {
    /**
     * Test for constructor {@link Frame#Frame()}
     */
    @Test
    public void defaultConstructorTest(){
        Frame f = new Frame();
        assertEquals(0, f.id);
        assertEquals(0, f.amountOfUses);
        assertEquals(0, f.ageOfFrame);

    }

    /**
     * Test for constructor {@link Frame#Frame(int, int)}
     */
    @Test
    public void twoArgConstructorTest(){
        Frame f = new Frame(12, 13);
        assertEquals(12, f.id);
        assertEquals(13, f.ageOfFrame);
    }

    /**
     * Test for constructor {@link Frame#Frame(int, int, int)}
     */
    @Test
    public void threeArgConstructorTest(){
        Frame f = new Frame(12, 13, 14);
        assertEquals(12, f.id);
        assertEquals(13, f.ageOfFrame);
        assertEquals(14, f.amountOfUses);

    }

}