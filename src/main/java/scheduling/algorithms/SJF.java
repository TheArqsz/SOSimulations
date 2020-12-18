package scheduling.algorithms;

import java.util.ArrayList;
import java.util.Comparator;

import scheduling.processes.Process;
import propertieshandler.PropertiesHandler;



/**
 * This class runs simulation for SJF scheduling algorithm
 * @author Arkadiusz Maruszczak
 * @see BaseSchedulingAlgorithm
 *
 */
public class SJF extends BaseSchedulingAlgorithm {

  /**
   * <p>Ready queue in which processes are stored when they finished processing</p>
   */
  protected Process[] readyQueue;

  /**
   * The constructor for SJF class.
   * @param pathToSourceFilepathToSourceFile  specifies path to source file
   * @param isUnderTest                       is set to true if method is used in tests. As default it is false.
   *
   */
  public SJF(String pathToSourceFilepathToSourceFile, boolean... isUnderTest) {
    boolean isTest = isUnderTest.length > 0 ? isUnderTest[0] : false;
    this.pathToSourceFile=pathToSourceFilepathToSourceFile;
    this.amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
    this.waitingQueue = new ArrayList<Process>();
    this.readyQueue = new Process[this.amnt];

    if(!isTest) {
      startProcessing();
    }
  }

  /**
   * <p>
   * Begins scheduling processes
   * </p>
   *
   */
  private void startProcessing() {

    createProcesses();
    executeProcesses();
    createReport("SJF");
  }

  /**
   * {@inheritDoc}
   */
  protected void createProcesses() {

    super.createProcesses();
    this.waitingQueue.sort(Comparator.comparing(Process::getBurstTime));

  }

  /**
   *
   * Simulates the execution of processes in processor
   *
   * <p>Method loops through all processes in waiting queue and increments waiting time of each with burst time of the one that is being processed.</p>
   *
   */
  protected void executeProcesses() {

    boolean allReady = false;
    int i = 0;
    double waitingTime = 0;

    while (!allReady) {
      waitingTime += this.waitingQueue.get(i).getBurstTime();

      for (int j = i + 1; j < this.amnt; j++) {
        this.waitingQueue.get(j).setAwaitingTime(waitingTime);
      }
      this.waitingQueue.get(i).setProcessingTime(this.waitingQueue.get(i).getAwaitingTime() + this.waitingQueue.get(i).getBurstTime());
      this.readyQueue[this.waitingQueue.get(i).getId()] = this.waitingQueue.get(i);
      i++;
      if (i == this.amnt) {
        allReady = true;
      }
    }

  }

  /**
   * <p>Overrides method from super class</p>
   * @param nameOfAlgorithm   specifies name of algorithm used
   * @param isUnderTest       is set to true if method is used in tests. As default it is false.
   *
   */
  protected void createReport(String nameOfAlgorithm, boolean... isUnderTest) {

    boolean isTest = isUnderTest.length > 0 ? isUnderTest[0] : false;
    this.avgAwaitTime = 0;
    for (int i = 0; i < this.amnt; i++) {
      this.avgAwaitTime += this.readyQueue[i].getAwaitingTime();
    }
    this.avgProcessingTime = 0;
    for (int i = 0; i < this.amnt; i++) {
      this.avgProcessingTime += this.readyQueue[i].getProcessingTime();
    }
    this.avgProcessingTime = this.avgProcessingTime / this.amnt;
    this.avgAwaitTime = this.avgAwaitTime / this.amnt;

    /*
          Temporary disabled
          if(!isTest) {
            System.out.println(nameOfAlgorithm + ": Average await time for " + this.amnt + " processes is equal to: " + this.avgAwaitTime + " [time unit]");
            System.out.println(nameOfAlgorithm + ": Average processing time for " + this.amnt + " processes is equal to: " + this.avgProcessingTime + " [time unit]");
        }
        */
  }

}
