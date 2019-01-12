package paging.algorithms;

import paging.frames.Frame;
import propertieshandler.PropertiesHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class runs simulation for LRU paging algorithm
 * @author Arkadiusz Maruszczak
 *
 */
public class LRU extends BasePagingAlgorithm{

    /**
     * The constructor for LRU class.
     * @param pathToSourceFile  specifies path to source file
     * @param avaibleFramesInMemory amount of frames in memory queue
     * @param isUnderTest       is set to true if method is used in tests. As default it is false.
     *
     */
    public LRU(String pathToSourceFile, int avaibleFramesInMemory, boolean... isUnderTest) {
        boolean isTest = isUnderTest.length > 0 ? isUnderTest[0] : false;
        this.pathToSourceFile = pathToSourceFile;
        this.amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfPages"));
        this.avaibleFramesInMemory=avaibleFramesInMemory;
        this.memoryQueue=new Frame[avaibleFramesInMemory];
        referenceList=new ArrayList<>();

        if(!isTest) {
            startProcessing();
        }
    }

    /**
     *
     * Begins paging.
     *
     * <p>
     *      This method executes {@link #createPages()}, {@link #executePages()} and {@link #setPageFaults(double, boolean...)}
     * </p>
     *
     */
    private void startProcessing() {

        createPages();
        executePages();
        setPageFaults(amntPageFault);
    }

    /**
     * Implements method from super class.
     *
     * <p>
     *     Method loops through all pages in reference list {@link #referenceList} and increments page faults {@link #amntPageFault} every time that it's representing frame is not present in memory {@link #memoryQueue}.
     * </p>
     */
    @Override
    protected void executePages() {
        int memoryListIndex = 0;
        amntPageFault = 0;

        for(int i=0; i<referenceList.size(); i++) {
            List<Frame> temp = new ArrayList<>(Arrays.asList(memoryQueue));

            if(memoryQueue[memoryListIndex]==null && !temp.contains(referenceList.get(i))){
                amntPageFault++;
                memoryQueue[memoryListIndex]=referenceList.get(i);
                memoryQueue[memoryListIndex].setAgeOfFrame(1);
                memoryQueue=setOthersAge(memoryListIndex, memoryQueue);
                memoryListIndex++;
            }else if(!temp.contains(referenceList.get(i))){
                amntPageFault++;
                memoryListIndex=getLeastRecentlyUsed(memoryQueue, avaibleFramesInMemory);
                memoryQueue[memoryListIndex]=referenceList.get(i);
                memoryQueue[memoryListIndex].setAgeOfFrame(1);
                memoryQueue=setOthersAge(memoryListIndex, memoryQueue);
                memoryListIndex++;
            }else if(temp.contains(referenceList.get(i))){
                memoryListIndex = getIndexInArray(memoryQueue, referenceList.get(i));
                memoryQueue[memoryListIndex].setAgeOfFrame(1);
                memoryQueue=setOthersAge(memoryListIndex, memoryQueue);
                memoryListIndex++;

            }

            if(memoryListIndex==avaibleFramesInMemory){
                memoryListIndex=0;
            }
        }
    }
}
