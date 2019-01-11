package paging.algorithms;

import org.junit.Test;
import paging.frames.Frame;
import propertieshandler.PropertiesHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for {@link LRU} class
 * @author Arkadiusz Maruszczak
 *
 */
public class LRUTest {
    /**
     * Test for constructor {@link LRU#LRU(String, int, boolean...)}b
     */
    @Test
    public void constructorTest(){
        LRU lru = new LRU("test", 2, true);
        assertEquals("test", lru.pathToSourceFile);
        assertEquals(Integer.parseInt(PropertiesHandler.getProp("sim.amountOfPages")), lru.amnt);
        assertEquals(2, lru.avaibleFramesInMemory);
        assertNotEquals(null, lru.referenceList);
        assertNotEquals(null, lru.memoryQueue);
    }
    /**
     * Test for method {@link LRU#executePages()}
     */
    @Test
    public void executePages() {
        LRU lru = new LRU("test", 3, true);
        int memoryListIndex = 0;
        int amntPageFault = 0;
        List<Frame> referenceList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            referenceList.add(new Frame(i, 0));
        }
        Frame[] memoryQueue = new Frame[3];

        for(int i=0; i<referenceList.size(); i++) {
            List<Frame> temp = new ArrayList<>(Arrays.asList(memoryQueue));

            if(memoryQueue[memoryListIndex]==null && !temp.contains(referenceList.get(i))){
                amntPageFault++;
                memoryQueue[memoryListIndex]=referenceList.get(i);
                memoryQueue[memoryListIndex].setAgeOfFrame(memoryQueue[memoryListIndex].getAgeOfFrame()+1);
                memoryQueue=lru.setOthersAge(memoryListIndex, memoryQueue);
                memoryListIndex++;
            }else if(!temp.contains(referenceList.get(i))){
                amntPageFault++;
                memoryListIndex=lru.getLeastRecentlyUsed(memoryQueue, 3);
                memoryQueue[memoryListIndex]=referenceList.get(i);
                memoryQueue[memoryListIndex].setAgeOfFrame(1);
                memoryQueue=lru.setOthersAge(memoryListIndex, memoryQueue);
                memoryListIndex++;
            }else if(temp.contains(referenceList.get(i))){
                memoryListIndex = lru.getIndexInArray(memoryQueue, referenceList.get(i));
                memoryQueue[memoryListIndex].setAgeOfFrame(memoryQueue[memoryListIndex].getAgeOfFrame()+1);
                memoryQueue=lru.setOthersAge(memoryListIndex, memoryQueue);
                memoryListIndex++;

            }

            if(memoryListIndex==3){
                memoryListIndex=0;
            }
        }
        assertEquals(4, amntPageFault);
    }
}