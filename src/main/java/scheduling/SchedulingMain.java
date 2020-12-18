package scheduling;

import com.opencsv.CSVWriter;
import scheduling.algorithms.*;
import datahelper.GenerateData;
import datahelper.GenerateSumUp;
import org.apache.commons.math3.util.Precision;
import propertieshandler.PropertiesHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


/**
 * @author Arkadiusz Maruszczak
 * <p>Main class for simulation "Allocation of processor time"</p>
 *
 */
public class SchedulingMain {
  /**
   * Object that collects average data from each algorithm and sums up it at the end
   */
  static GenerateSumUp avgTimes;
  /**
   * Amount of processes specified in properties file
   */

  static int amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));

  /**
   * <p>Main method for simulation "Scheduling of processor time"</p>
   *
   * @param args main method arguments
   */
  public static void main(String[] args) {
    /**
     *  Variable that stores amount of tries for simulation
     */
    int tries = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfTries"));
    /**
     * Array that stores all time quantums used for simulation in Round-Robin algorithm
     */
    Double[] timeQuantums = GenerateData.processArrayToDbl(PropertiesHandler.getProp("sim.timeQuantumRR").split(","));

    HashMap<Double, GenerateSumUp> results = new HashMap<>();


    /**
     * Loop that generates source files
     */
    for (int i =1; i<=tries; i++){
      String pathToSourceFile = PropertiesHandler.getProp("sim.pathToProcessesData") + PropertiesHandler.getProp("sim.baseNameOfProcessFile") + i + PropertiesHandler.getProp("sim.extension");
      GenerateData.generateProcessesSourceFile(pathToSourceFile);
    }

    /**
     *  Main loop for simulation
     */
    for(int x=0;x<timeQuantums.length;x++) {

      avgTimes = new GenerateSumUp();

      /**
       *  Loop that goes through all the tries specified in properties file
       */
      for (int i = 1; i <= tries; i++) {
        /**
         *  Path to each source file that contains process id and burst time
         */
        String pathToSourceFile = PropertiesHandler.getProp("sim.pathToProcessesData") + PropertiesHandler.getProp("sim.baseNameOfFile") + i + PropertiesHandler.getProp("sim.extension");

        //System.out.println("START RUN NUMBER " + i);
        startFCFS(pathToSourceFile);
        startLCFS(pathToSourceFile);
        startSJF(pathToSourceFile);
        startRR(pathToSourceFile, timeQuantums[x]);
        //System.out.println("END");
      }

      results.put(timeQuantums[x], avgTimes);
      System.out.println("\nAmount of processes: " + amnt);
      System.out.println("Amount of tries: " + tries);
      System.out.println("Used value of time quantum: " + timeQuantums[x]);
      System.out.println("\n\nFCFS: avg await time: " + Precision.round(avgTimes.getAvgAwaitTimeSum("FCFS") / tries, 3) + " avg process. time: " + Precision.round
              (avgTimes.getAvgProccessingTimeSum("FCFS") / tries, 3) + "[time unit]");
      System.out.println("LCFS: avg await time: " + Precision.round(avgTimes.getAvgAwaitTimeSum("LCFS") / tries, 3) + " avg process. time: " + Precision.round(avgTimes.getAvgProccessingTimeSum("LCFS") / tries, 3) + "[time unit]");
      System.out.println("SJF: avg await time: " + Precision.round(avgTimes.getAvgAwaitTimeSum("SJF") / tries, 3) + " avg process. time: " + Precision.round(avgTimes.getAvgProccessingTimeSum("SJF") / tries, 3) + "[time unit]");
      System.out.println("RoundRobin FCFS: avg await time: " + Precision.round(avgTimes.getAvgAwaitTimeSum("RoundRobin FCFS") / tries, 3) + " avg process. time: " + Precision.round(avgTimes.getAvgProccessingTimeSum("RoundRobin FCFS") / tries, 3) + "[time unit]");
      System.out.println("RoundRobin LCFS: avg await time: " + Precision.round(avgTimes.getAvgAwaitTimeSum("RoundRobin LCFS") / tries, 3) + " avg process. time: " + Precision.round(avgTimes.getAvgProccessingTimeSum("RoundRobin LCFS") / tries, 3) + "[time unit]");
    }
    generateSummary(results, amnt, tries);
  }



  /**
   *  <p>Starts the FCFS allocation algorithm</p>
   * @param pathToSourceFile  specifies path to source files
   */
  private static void startFCFS(String pathToSourceFile) {

    FCFS fcfs = new FCFS(pathToSourceFile, false);
    avgTimes.setAvgAwaitTimeSum(avgTimes.getAvgAwaitTimeSum("FCFS") + fcfs.getAvgAwaitTime(), "FCFS");
    avgTimes.setAvgProccessingTimeSum(avgTimes.getAvgProccessingTimeSum("FCFS") + fcfs.getAvgProcessingTime(), "FCFS");

  }

  /**
   * <p>Starts the LCFS allocation algorithm</p>
   *  @param pathToSourceFile   specifies path to source files
   */
  private static void startLCFS(String pathToSourceFile) {

    LCFS lcfs = new LCFS(pathToSourceFile);
    avgTimes.setAvgAwaitTimeSum(avgTimes.getAvgAwaitTimeSum("LCFS") + lcfs.getAvgAwaitTime(), "LCFS");
    avgTimes.setAvgProccessingTimeSum(avgTimes.getAvgProccessingTimeSum("LCFS") + lcfs.getAvgProcessingTime(), "LCFS");

  }

  /**
   * <p>Starts the SJF allocation algorithm</p>
   * @param pathToSourceFile  specifies path to source files
   */
  private static void startSJF(String pathToSourceFile) {

    SJF sjf = new SJF(pathToSourceFile);
    avgTimes.setAvgAwaitTimeSum(avgTimes.getAvgAwaitTimeSum("SJF") + sjf.getAvgAwaitTime(), "SJF");
    avgTimes.setAvgProccessingTimeSum(avgTimes.getAvgProccessingTimeSum("SJF") + sjf.getAvgProcessingTime(), "SJF");

  }

  /**
   * <p>Starts the Round-Robin allocation algorithm</p>
   * @param pathToSourceFile  specifies path to source files
   * @param timeQuantum       specifies time quantum used in Round-Robin algorithm
   */
  private static void startRR(String pathToSourceFile, double timeQuantum) {

    RoundRobinFCFS rrFCFS = new RoundRobinFCFS(pathToSourceFile, timeQuantum);
    RoundRobinLCFS rrLCFS = new RoundRobinLCFS(pathToSourceFile, timeQuantum);
    avgTimes.setAvgAwaitTimeSum(avgTimes.getAvgAwaitTimeSum("RoundRobin FCFS") + rrFCFS.getAvgAwaitTime(), "RoundRobin FCFS");
    avgTimes.setAvgProccessingTimeSum(avgTimes.getAvgProccessingTimeSum("RoundRobin FCFS") + rrFCFS.getAvgProcessingTime(), "RoundRobin FCFS");
    avgTimes.setAvgAwaitTimeSum(avgTimes.getAvgAwaitTimeSum("RoundRobin LCFS") + rrLCFS.getAvgAwaitTime(), "RoundRobin LCFS");
    avgTimes.setAvgProccessingTimeSum(avgTimes.getAvgProccessingTimeSum("RoundRobin LCFS") + rrLCFS.getAvgProcessingTime(), "RoundRobin LCFS");

  }

    /**
     * Generates summary from previously created sumUp objects {@link #avgTimes}
     *
     * @param results map of sumUp objects with time quantums for each.
     * @param amountOfProcesses amount of processes specified in <a href="file:../simulationproperties.html"><b>simulation.properties</b></a>.
     * @param tries amount of tries for simulation specified in <a href="file:../simulationproperties.html"><b>simulation.properties</b></a>.
     */
  private static void generateSummary(HashMap<Double, GenerateSumUp> results, int amountOfProcesses, int tries){
        try {
            String destinationPath = PropertiesHandler.getProp("sim.pathToProcessesSummary") + PropertiesHandler.getProp("sim.processesSummaryName");
            CSVWriter writer = new CSVWriter(new FileWriter(new File(destinationPath)));

            /**
             * Array that stores all time quantums used for simulation in Round-Robin algorithm
             */
            Double[] timeQuantums = GenerateData.processArrayToDbl(PropertiesHandler.getProp("sim.timeQuantumRR").split(","));

            {
                String[] header = {"Time Quantum", "Algorithm", "Amount of processes", "Average processing time", "Average waiting time"};
                writer.writeNext(header);
            }

            for(int i=0; i<timeQuantums.length;i++) {

                String[] algorithmNames = {"FCFS", "LCFS", "SJF", "RoundRobin FCFS", "RoundRobin LCFS"};
                for (String algorithmName: algorithmNames) {
                    double avgWaitTime = Precision.round(results.get(timeQuantums[i]).getAvgAwaitTimeSum(algorithmName) / tries, 3);
                    double avgProcessingTime = Precision.round(results.get(timeQuantums[i]).getAvgProccessingTimeSum(algorithmName) / tries, 3);
                    String[] data = {Double.toString(timeQuantums[i]).replace('.', '.'), algorithmName, Integer.toString(amountOfProcesses), Double.toString(avgWaitTime), Double.toString(avgProcessingTime) };
                    writer.writeNext(data);

                }
                writer.writeNext(new String[0]);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
