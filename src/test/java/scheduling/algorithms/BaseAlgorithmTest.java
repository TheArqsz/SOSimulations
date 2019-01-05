package scheduling.algorithms;

import com.opencsv.CSVWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import propertieshandler.PropertiesHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Abstract class for algorithm classes'
 */
public abstract class BaseAlgorithmTest {

    /**
     * <p>Path to mocked source file</p>
     */
    final String pathToSourceFile ="src/test/resources/test.csv";

    /**
     * Amount of processes
     */
    int amnt = 0;

    /**
     * Abstract method to be implemented in test class
     */
    @Test
    abstract void constructorTest();

    /**
     * Abstract method to be implemented in test class
     */
    @Test
    abstract void executeProcessesTest();

    /**
     * Abstract method to be implemented in test class
     */
    @Test
    abstract void createProcessesTest();

    /**
     * Abstract method to be implemented in test class
     */
    @Test
    abstract void createReportTest();

    /**
     * Before method for test class.
     * <p>
     * Creates temporary source file.
     * @see <a href="https://junit.org/junit4/javadoc/4.12/org/junit/Before.html">Before (JUNIT 4)</a>
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
