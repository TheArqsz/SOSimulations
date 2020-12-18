package scheduling.algorithms;

import java.util.ArrayList;

import scheduling.processes.Process;
import propertieshandler.PropertiesHandler;


/**
 * This class runs simulation for LCFS scheduling algorithm
 * @author Arkadiusz Maruszczak
 * @see BaseSchedulingAlgorithm
 *
 */
public class LCFS extends BaseSchedulingAlgorithm {

  /**
   * The constructor for LCFS class.
   * @param pathToSourceFile  specifies path to source file
   * @param isUnderTest       is set to true if method is used in tests. As default it is false.
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
   *
   * Begins scheduling processes.
   *
   * <p>
   *     This method executes {@link #createProcesses()}, {@link #executeProcesses()} and {@link #createReport(String, boolean...)}
   * </p>
   */
  private void startProcessing() {

    createProcesses();
    executeProcesses();
    createReport("LCFS");
  }

  /**
   *  Implements method from super class.
   *
   *  <p>Method loops through all processes in waiting queue and increments waiting time of each with burst time of the one that is being processed.</p>
   */
  protected void executeProcesses() {

    boolean allReady = false;
    int i = amnt - 1;
    double waitingTime = 0;

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
