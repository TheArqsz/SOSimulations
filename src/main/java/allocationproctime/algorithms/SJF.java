package allocationproctime.algorithms;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.opencsv.CSVReader;

import allocationproctime.processes.Process;
import propertieshandler.PropertiesHandler;


/**
 * This class runs simulation for SJF schedulding algorithm
 *
 * @author amaruszc
 *
 */
public class SJF extends BaseAllocationAlgorithm{

  protected Process[] readyQueue;

  /**
   * The constructor.
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
   * Begin schedulding processes
   * </p>
   *
   */
  private void startProcessing() {

    createProcesses();
    executeProcesses();
    createReport("SJF");
  }

  protected void createProcesses() {

    super.createProcesses();
    this.waitingQueue.sort(Comparator.comparing(Process::getBurstTime));

  }

  /**
   *
   * Method that simulates the execution of processes in processor
   *
   */
  protected void executeProcesses() {

    boolean allReady = false;
    int i = 0;
    int waitingTime = 0;

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