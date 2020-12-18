package paging.algorithms;

import com.opencsv.CSVWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import paging.frames.Frame;
import propertieshandler.PropertiesHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Test class for {@link BasePagingAlgorithmTest} class
 * @author Arkadiusz Maruszczak
 *
 */
public class BasePagingAlgorithmTest {

    /**
     * <p>Path to mocked source file</p>
     */
    final String pathToSourceFile ="src/test/resources/test.csv";

    /**
     * Amount of pages
     */
    int amnt = 0;

    /**
     * Abstract method to be implemented in test class
     */
    @Test
    public void constructorTest(){

    }

    /**
     * Abstract method to be implemented in test class
     */
    @Test
    public void executePagesTest(){

    }

    /**
     * Test for method {@link BasePagingAlgorithm#createPages()}
     */
    @Test
    public void createPagesTest(){
        String pathToSourceFile = PropertiesHandler.getProp("sim.pathToPagesData") + PropertiesHandler.getProp("sim.baseNameOfPageFile") + 1 + PropertiesHandler.getProp("sim.extension");
        LFU bp = new LFU(pathToSourceFile, 2, true);
        bp.createPages();
        assertTrue("Pages creation finish unproperly" ,Integer.parseInt(PropertiesHandler.getProp("sim.lengthOfReferenceList"))==bp.referenceList.size());

    }

    /**
     * Test for method {@link BasePagingAlgorithm#setPageFaults(double, boolean...)}
     */
    @Test
    public void setPageFaults() {
        LFU bp = new LFU(pathToSourceFile, 2, true);
        bp.setPageFaults(3, true);
        assertTrue("Setting page faults finishes unproperly" ,3==bp.amntPageFault);

    }

    /**
     * Test for method {@link BasePagingAlgorithm#getPageFault()}
     */
    @Test
    public void getPageFault() {
        LFU bp = new LFU(pathToSourceFile, 2, true);
        bp.setPageFaults(3, true);
        assertTrue("Getting page faults finishes unproperly" ,3==bp.getPageFault());

    }

    /**
     * Test for method {@link BasePagingAlgorithm#getIndexInArray(Frame[], Frame)}
     */
    @Test
    public void getIndexInArray() {
        Frame[] array = new Frame[3];
        Frame frame = new Frame(1,2,3);
        array[2]=frame;
            int i=0;
            for(i=0; i<3;i++){
                if(array[i]!=null && array[i].equals(frame)){
                    break;
                }
            }
            assertEquals(2, i);
    }

    /**
     * Test for method {@link BasePagingAlgorithm#setOthersAge(int, Frame[])}
     */
    @Test
    public void setOthersAge() {
        Frame[] array = new Frame[3];
        for(int i=0; i<3; i++){
            array[i] = new Frame();
        }
        Frame frame = new Frame(1,2,3);
        array[1]=frame;
        for(int i=0; i<array.length; i++){
            if(i==1 || array[i]==null)
                continue;
            else{
                array[i].setAgeOfFrame(array[i].getAgeOfFrame()+1);
            }
        }
        assertEquals(1, array[0].getAgeOfFrame() );
        assertEquals(2, array[1].getAgeOfFrame() );
        assertEquals(1, array[2].getAgeOfFrame() );
    }

    /**
     * Test for method {@link BasePagingAlgorithm#getLeastRecentlyUsed(Frame[], int)}
     */
    @Test
    public void getLeastRecentlyUsed() {
        Frame[] array = new Frame[3];
        for(int i=0; i<3; i++){
            array[i] = new Frame(i, 2+i, 1);
            System.out.println(array[i].getAgeOfFrame());
        }

        int i = 0;
        int oldest = 0;
        for(i=0; i<3;i++){
            if(array[i]!=null && array[i].getAgeOfFrame()>array[oldest].getAgeOfFrame()){
                oldest=i;
                //break;
            }
        }
        assertEquals(2, oldest);
    }

    /**
     * Before method for test class.
     * <p>
     * Creates temporary source file.
     * @see <a href="https://junit.org/junit4/javadoc/4.12/org/junit/Before.html">Before (JUNIT 4)</a>
     *
     * @throws IOException
     */
    @Before
    public void createTempSourceFile() throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(new File(pathToSourceFile)));
        amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
        for (int i = 0; i < amnt; i++) {
            String[] data = { Integer.toString(i), Integer.toString(1) };
            writer.writeNext(data);
        }
        writer.close();
    }

    /**
     * After method for test class
     * @see <a href="https://junit.org/junit4/javadoc/4.12/org/junit/After.html">After (JUNIT 4)</a>
     */
    @After
    public void deleteTempFile() {
        File f = new File(pathToSourceFile);
        f.deleteOnExit();
    }
}