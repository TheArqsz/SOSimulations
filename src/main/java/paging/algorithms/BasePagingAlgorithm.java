package paging.algorithms;


import com.opencsv.CSVReader;
import paging.frames.Frame;

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
     * <p>Path to source file in which processes are stored</p>
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
     * <p>Amount of tries.</p>
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
     *
     * Creates pages using data from file "pages*.csv" and put them to reference list
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

    public int getIndexInArray(Frame[] array, Frame frame){
        int i=0;
        for(i=0; i<avaibleFramesInMemory;i++){
            if(array[i].equals(frame)){
                break;
            }
        }
        return i;
    }

}
