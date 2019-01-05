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

        startProcessing();
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

    @Override
    protected void executePages() {
        boolean isFault = false;
        int memoryListIndex = 0;
        amntPageFault = 0;

        for(int i=0; i<referenceList.size(); i++) {
            List<Frame> temp = new ArrayList<>(Arrays.asList(memoryQueue));

            if(memoryQueue[memoryListIndex]==null && !temp.contains(referenceList.get(i))){
                amntPageFault++;
                memoryQueue[memoryListIndex]=referenceList.get(i);
                memoryQueue[memoryListIndex].setAgeOfFrame(memoryQueue[memoryListIndex].getAgeOfFrame()+1);
                memoryQueue=setOtherAge(memoryListIndex, memoryQueue);
                memoryListIndex++;
            }else if(!temp.contains(referenceList.get(i))){
                amntPageFault++;
                memoryListIndex=getLeastRecentlyUsed(memoryQueue);
                memoryQueue[memoryListIndex]=referenceList.get(i);
                memoryQueue[memoryListIndex].setAgeOfFrame(1);
                memoryQueue=setOtherAge(memoryListIndex, memoryQueue);
                memoryListIndex++;
            }else if(temp.contains(referenceList.get(i))){
                memoryListIndex = getIndexInArray(memoryQueue, referenceList.get(i));
                memoryQueue[memoryListIndex].setAgeOfFrame(memoryQueue[memoryListIndex].getAgeOfFrame()+1);
                memoryQueue=setOtherAge(memoryListIndex, memoryQueue);
                memoryListIndex++;

            }

            if(memoryListIndex==avaibleFramesInMemory){
                memoryListIndex=0;
            }
        }
    }

    private Frame[] setOtherAge(int memoryListIndex, Frame[] memoryQueue) {
        for(int i=0; i<memoryQueue.length; i++){
            if(i==memoryListIndex || memoryQueue[i]==null)
                continue;
            else{
                memoryQueue[i].setAgeOfFrame(memoryQueue[i].getAgeOfFrame()+1);
            }
        }
        return memoryQueue;
    }

    private int getLeastRecentlyUsed(Frame[] array){
        int i = 0;
        int oldest = 0;
        for(i=0; i<avaibleFramesInMemory;i++){
            if(array[i]!=null && array[i].getAgeOfFrame()>array[oldest].getAgeOfFrame()){
                oldest=i;
                break;
            }
        }
        return oldest;
    }
}
