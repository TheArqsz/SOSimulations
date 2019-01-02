package algorithms;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.opencsv.CSVReader;

import processes.Process;
import propertieshandler.PropertiesHandler;

/**
 * This class runs simulation for SJF schedulding algorithm
 *
 * @author amaruszc
 *
 */
public class SJF {

  private int amnt = 0;

  private List<Process> waitingQueue;

  private Process[] readyQueue;

  /**
   * The constructor.
   *
   */
  public SJF() {

    this.amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
    this.waitingQueue = new ArrayList<Process>();
    this.readyQueue = new Process[this.amnt];

    startProcessing();
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
    createReport();
  }

  /**
   * <p>
   * Create processes using data from file "processes.csv" and put them to waiting queue
   * </p>
   *
   */
  private void createProcesses() {

    try {
      Reader reader = Files.newBufferedReader(Paths.get(PropertiesHandler.getProp("sim.pathToProcessesData")));
      CSVReader csvReader = new CSVReader(reader);
      String[] nextValues;
      while ((nextValues = csvReader.readNext()) != null) {

        this.waitingQueue.add(new Process(Integer.parseInt(nextValues[0]), Integer.parseInt(nextValues[1])));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.waitingQueue.sort(Comparator.comparing(Process::getBurstTime));

  }

  /**
   *
   */
  private void executeProcesses() {

    boolean allReady = false;
    int i = 0;
    int waitingTime = 0;

    while (!allReady) {
      waitingTime += this.waitingQueue.get(i).getBurstTime();
      for (int j = i + 1; j < this.amnt; j++) {
        this.waitingQueue.get(j).setAwaitingTime(waitingTime);
      }
      this.readyQueue[this.waitingQueue.get(i).getId()] = this.waitingQueue.get(i);
      i++;
      if (i == this.amnt) {
        allReady = true;
      }
    }

  }

  /**
   *
   */
  private void createReport() {

    int avgAwaitTime = 0;
    for (int i = 0; i < this.amnt; i++) {
      avgAwaitTime += this.readyQueue[i].getAwaitingTime();
    }
    avgAwaitTime = avgAwaitTime / this.amnt;
    System.out.println(
        "SJF: Average await time for " + this.amnt + " processes is equal to: " + avgAwaitTime + " [time unit]");
  }

}
