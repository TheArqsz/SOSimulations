package paging.algorithms;

import org.junit.Test;
import paging.frames.Frame;
import propertieshandler.PropertiesHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for {@link LFU} class
 * @author Arkadiusz Maruszczak
 *
 */
public class LFUTest {

    /**
     * Test for constructor {@link LFU#LFU(String, int, boolean...)}b
     */
    @Test
    public void constructorTest(){
        LFU lfu = new LFU("test", 2, true);
        assertEquals("test", lfu.pathToSourceFile);
        assertEquals(Integer.parseInt(PropertiesHandler.getProp("sim.amountOfPages")), lfu.amnt);
        assertEquals(2, lfu.avaibleFramesInMemory);
        assertNotEquals(null, lfu.referenceList);
        assertNotEquals(null, lfu.memoryQueue);
    }
    /**
     * Test for method {@link LFU#executePages()}
     */
    @Test
    public void executePages() {
        LFU lfu = new LFU("test", 3, true);
        int memoryListIndex = 0;
        int amntPageFault = 0;
        List<Frame> referenceList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            referenceList.add(new Frame(i, 0));
        }
        Frame[] memoryQueue = new Frame[3];

        for (int i = 0; i < referenceList.size(); i++) {
            List<Frame> temp = new ArrayList<>(Arrays.asList(memoryQueue));


            if (memoryQueue[memoryListIndex] == null && !temp.contains(referenceList.get(i))) {
                amntPageFault++;
                memoryQueue[memoryListIndex] = referenceList.get(i);
                memoryQueue[memoryListIndex].setAgeOfFrame(memoryQueue[memoryListIndex].getAgeOfFrame() + 1);
                memoryQueue[memoryListIndex].setAmountOfUses(memoryQueue[memoryListIndex].getAmountOfUses() + 1);
                memoryQueue = lfu.setOthersAge(memoryListIndex, memoryQueue);
                memoryListIndex++;
            } else if (!temp.contains(referenceList.get(i))) {
                amntPageFault++;
                memoryListIndex = lfu.getLeastFrequentlyUsed(memoryQueue);
                memoryQueue[memoryListIndex] = referenceList.get(i);
                memoryQueue[memoryListIndex].setAmountOfUses(1);
                memoryQueue = lfu.setOthersAge(memoryListIndex, memoryQueue);
                memoryListIndex++;
            } else if (temp.contains(referenceList.get(i))) {
                memoryListIndex = lfu.getIndexInArray(memoryQueue, referenceList.get(i));
                memoryQueue[memoryListIndex].setAgeOfFrame(memoryQueue[memoryListIndex].getAgeOfFrame() + 1);
                memoryQueue[memoryListIndex].setAmountOfUses(memoryQueue[memoryListIndex].getAmountOfUses() + 1);
                memoryQueue = lfu.setOthersAge(memoryListIndex, memoryQueue);
                memoryListIndex++;

            }

            if (memoryListIndex == 3) {
                memoryListIndex = 0;
            }
        }
        assertEquals(4, amntPageFault);
    }

    /**
     * Test for method {@link LFU#getLeastRecentlyUsed(Frame[], int)}
     */
    @Test
    public void getLeastFrequentlyUsed() {
        LFU lfu = new LFU("test", 3, true);
        List<Frame> leastFqUsed = new ArrayList<>();;
        Frame[] memoryQueue = new Frame[3];
        for (int i = 0; i < 3; i++) {
            memoryQueue[i] = new Frame(i, 0, i+1);
        }


        int least = 5;
        int minAmntUses=5;
        /**
         * Get minimum amount of uses
         */
        for( int i = 0; i<3;i++){
            if(memoryQueue[i]!=null  && memoryQueue[i].getAmountOfUses()<minAmntUses){
                minAmntUses=memoryQueue[i].getAmountOfUses();
            }
        }

        for(int i = 0; i<3; i++){
            if(memoryQueue[i]!=null && memoryQueue[i].getAmountOfUses()==minAmntUses){
                leastFqUsed.add(memoryQueue[i]);
            }
        }

        int oldest = lfu.getLeastRecentlyUsed(leastFqUsed.toArray(new Frame[leastFqUsed.size()]), leastFqUsed.size());
        Frame oldestFrame = leastFqUsed.get(oldest);

        for(int i = 0; i<3; i++){
            if(memoryQueue[i]!=null && memoryQueue[i].equals(oldestFrame)){
                least=i;
                break;
            }
        }
        assertEquals(0, least);
    }
}