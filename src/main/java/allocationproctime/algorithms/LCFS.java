package allocationproctime.algorithms;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import allocationproctime.processes.Process;
import propertieshandler.PropertiesHandler;

/**
 * This class runs simulation for LCFS schedulding algorithm
 * @author amaruszc
 *
 */
public class LCFS {

  String pathToSourceFile;
  int amnt = 0;

  List<Process> waitingQueue;

  List<Process> readyQueue;

  /**
   * The constructor.
   *
   */
  public LCFS(String pathToSourceFile) {

    this.pathToSourceFile=pathToSourceFile;
    this.amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
    this.waitingQueue = new ArrayList<Process>();
    this.readyQueue = new ArrayList<Process>();

    startProcessing();
  }

  /**
   * <p>
   * Begin schedulding algorithms.allocationproctime.processes
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
   * Create algorithms.allocationproctime.processes using data from file "algorithms.allocationproctime.processes.csv" and put them to waiting queue
   * </p>
   *
   */
  private void createProcesses() {

    try {
      Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));
      CSVReader csvReader = new CSVReader(reader);
      String[] nextValues;
      while ((nextValues = csvReader.readNext()) != null) {

        this.waitingQueue.add(new Process(Integer.parseInt(nextValues[0]), Integer.parseInt(nextValues[1])));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   *
   */
  private void executeProcesses() {

    boolean allReady = false;
    int i = amnt-1;
    int waitingTime = 0;

    while (!allReady) {
      waitingTime += this.waitingQueue.get(i).getBurstTime();
      for (int j = i - 1; j >= 0; j--) {
        this.waitingQueue.get(j).setAwaitingTime(waitingTime);
      }
      this.readyQueue.add(this.waitingQueue.get(i));
      i--;
      if (this.readyQueue.size() == this.amnt) {
        allReady = true;
      }
    }

  }

  /**
   *
   */
  private void createReport() {

    float avgAwaitTime = 0;
    for (int i = 0; i < this.amnt; i++) {
      avgAwaitTime += this.readyQueue.get(i).getAwaitingTime();
    }
    avgAwaitTime = avgAwaitTime / this.amnt;
    System.out.println("LCFS: Average await time for " + this.amnt + " algorithms.allocationproctime.processes is equal to: " + avgAwaitTime + " [time unit]");
  }

}
