package paging.algorithms;


import com.opencsv.CSVReader;
import paging.frames.Frame;
import propertieshandler.PropertiesHandler;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * <p>Abstract class for all paging algorithms</p>
 * @see LRU
 */
public abstract class BasePagingAlgorithm {

    /**
     * <p>Memory in which frames are stored while they are being processed.</p>
     */
    Frame[] memoryQueue = null;

    /**
     * <p>Reference page list which will be used to create frames.</p>
     *
     * Pages are equal to frames in this particular example.
     */
    List<Frame> referenceList= null;

    /**
     * <p>Path to source file in which pages are stored</p>
     */
    String pathToSourceFile;

    /**
     * <p>Amount of pages used.</p>
     */
    int amnt;

    /**
     * <p>Amount of avaible frames in memory.</p>
     */
    int avaibleFramesInMemory;

    /**
     * <p>Amount of tries of simulation.</p>
     */
    int tries;

    /**
     * <p>Average amount of page faults.</p>
     */
    double amntPageFault;

    /**
     *  Abstract method that simulates execution of paging.
     */
    protected abstract void executePages();

    /**
     *<p>
     * Creates pages using data from file "pages*.csv" and put them to reference list.
     *</p>
     *
     * <p>
     *     Method reads each line of specified file under {@link #pathToSourceFile} and puts it into {@link #referenceList}.<br>
     *     First column is id of page.
     * </p>
     *
     */
    protected void createPages(){
        try {
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));
            CSVReader csvReader = new CSVReader(reader);
            String[] nextValues;
            while ((nextValues = csvReader.readNext()) != null) {
                if(referenceList.size()==Integer.parseInt(PropertiesHandler.getProp("sim.lengthOfReferenceList")))
                    break;
                this.referenceList.add(new Frame(Integer.parseInt(nextValues[0]), 0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Sets amount of page faults for specific algorithm.
     *
     *  @param amntPageFault is equal to amount of page faults
     *  @param isUnderTest is set to true if method is used in tests. As default it is false.
     */
    protected void setPageFaults(double amntPageFault, boolean... isUnderTest) {

        boolean isTest = isUnderTest.length > 0 ? isUnderTest[0] : false;
        this.amntPageFault = amntPageFault;
        /*
          //Temporary disabled
          if(!isTest) {

        }
        */
    }

    /**
     * Gets amount of page faults.
     * @return the value under the {@link #amntPageFault} variable
     */
    public double getPageFault(){
        return this.amntPageFault;
    }

    /**
     * Gets index of specified Frame from {@link #memoryQueue}
     * @param array {@link #memoryQueue}
     * @param frame specified frame
     * @return index of frame in {@link #memoryQueue}
     */
    public int getIndexInArray(Frame[] array, Frame frame){
        int i=0;
        for(i=0; i<avaibleFramesInMemory;i++){
            if(array[i].equals(frame)){
                break;
            }
        }
        return i;
    }


    /**
     * Sets age of elements in memory array on indexes that are not equal to given one.
     * @param memoryListIndex index of element that we don't want to be changed
     * @param memoryQueue   array of frames in memory
     * @return  fixed array of frames in memory
     */
    protected Frame[] setOthersAge(int memoryListIndex, Frame[] memoryQueue) {
        for(int i=0; i<memoryQueue.length; i++){
            if(i==memoryListIndex || memoryQueue[i]==null)
                continue;
            else{
                memoryQueue[i].setAgeOfFrame(memoryQueue[i].getAgeOfFrame()+1);
            }
        }
        return memoryQueue;
    }

    /**
     * Gets index of element which was least recently used
     * @param array array of frames in memory
     * @param size size of array
     * @return  index of element that was leas recently used
     */
    protected int getLeastRecentlyUsed(Frame[] array, int size){
        int i = 0;
        int oldest = 0;
        for(i=0; i<size;i++){
            if(array[i]!=null && array[i].getAgeOfFrame()>array[oldest].getAgeOfFrame()){
                oldest=i;
                //break;
            }
        }
        return oldest;
    }

}
