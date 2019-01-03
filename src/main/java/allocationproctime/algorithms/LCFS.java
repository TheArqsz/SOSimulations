package allocationproctime.algorithms;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.opencsv.CSVReader;

import allocationproctime.processes.Process;
import propertieshandler.PropertiesHandler;

/**
 * This class runs simulation for LCFS schedulding algorithm
 * @author amaruszc
 *
 */
public class LCFS extends BaseAllocationAlgorithm{

  /**
   * The constructor.
   *
   */
  public LCFS(String pathToSourceFile, boolean... isUnderTest) {
    boolean isTest = isUnderTest.length > 0 ? isUnderTest[0] : false;

    this.pathToSourceFile=pathToSourceFile;
    this.amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
    this.waitingQueue = new ArrayList<Process>();
    this.readyQueue = new ArrayList<Process>();

    if(!isTest) {
      startProcessing();
    }
  }

  /**
   * <p>
   * Begin schedulding processes
   * </p>
   *
   */
  private void startProcessing() {

    createProcesses();
    executeProcesses();
    createReport("LCFS");
  }

  /**
   *
   */
  protected void executeProcesses() {

    boolean allReady = false;
    int i = amnt - 1;
    int waitingTime = 0;

    while (!allReady) {
      waitingTime += this.waitingQueue.get(i).getBurstTime();

      for (int j = i - 1; j >= 0; j--) {
        this.waitingQueue.get(j).setAwaitingTime(waitingTime);
      }
      this.waitingQueue.get(i).setProcessingTime(this.waitingQueue.get(i).getAwaitingTime() + this.waitingQueue.get(i).getBurstTime());
      this.readyQueue.add(this.waitingQueue.get(i));
      i--;
      if (this.readyQueue.size() == this.amnt) {
        allReady = true;
      }
    }

  }

}
