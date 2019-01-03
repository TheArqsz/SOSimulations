package allocationproctime.main;

import allocationproctime.algorithms.FCFS;
import allocationproctime.algorithms.LCFS;
import allocationproctime.algorithms.RoundRobin;
import allocationproctime.algorithms.SJF;
import allocationproctime.datahelper.GenerateData;
import allocationproctime.datahelper.GenerateSumUp;
import propertieshandler.PropertiesHandler;


/**
 * @author amaruszc
 *
 */
public class Main {
  static GenerateSumUp avgTimes;
  static int amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
  /**
   * @param args
   */
  public static void main(String[] args) {
    int tries = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfTries"));
    avgTimes = new GenerateSumUp();
    for(int i=1; i<=tries; i++) {
      String pathToSourceFile = PropertiesHandler.getProp("sim.pathToProcessesData")+ PropertiesHandler.getProp("sim.baseNameOfFile") + i + PropertiesHandler.getProp("sim.extension");
      System.out.println("START RUN NUMBER " + i);
      GenerateData.generateSourceFile(pathToSourceFile);
      startFCFS(pathToSourceFile);
      startLCFS(pathToSourceFile);
      startSJF(pathToSourceFile);
      startRR(pathToSourceFile);
      System.out.println("END");
    }

      System.out.println("Amount of processes: " + amnt);
      System.out.println("Amount of tries: " + tries);
      System.out.println("\n\nFCFS: avg await time: " + avgTimes.getAvgAwaitTimeSum( "FCFS")/tries + " avg process. time: " + avgTimes.getAvgProccessingTimeSum( "FCFS")/tries);
      System.out.println("LCFS: avg await time: " + avgTimes.getAvgAwaitTimeSum( "LCFS")/tries + " avg process. time: " + avgTimes.getAvgProccessingTimeSum( "LCFS")/tries);
      System.out.println("SJF: avg await time: " + avgTimes.getAvgAwaitTimeSum( "SJF")/tries + " avg process. time: " + avgTimes.getAvgProccessingTimeSum( "SJF")/tries);
      System.out.println("RoundRobin FCFS: avg await time: " + avgTimes.getAvgAwaitTimeSum( "RoundRobin FCFS")/tries + " avg process. time: " + avgTimes.getAvgProccessingTimeSum( "RoundRobin FCFS")/tries);
      System.out.println("RoundRobin LCFS: avg await time: " + avgTimes.getAvgAwaitTimeSum( "RoundRobin LCFS")/tries + " avg process. time: " + avgTimes.getAvgProccessingTimeSum( "RoundRobin LCFS")/tries);
  }

  /**
   *
   */
  private static void startFCFS(String pathToSourceFile) {

    FCFS fcfs = new FCFS(pathToSourceFile, false);
    avgTimes.setAvgAwaitTimeSum(avgTimes.getAvgAwaitTimeSum("FCFS") + fcfs.getAvgAwaitTime(), "FCFS");
    avgTimes.setAvgProccessingTimeSum(avgTimes.getAvgProccessingTimeSum("FCFS") + fcfs.getAvgProcessingTime(), "FCFS");

  }

  /**
   *
   */
  private static void startLCFS(String pathToSourceFile) {

    LCFS lcfs = new LCFS(pathToSourceFile);
    avgTimes.setAvgAwaitTimeSum(avgTimes.getAvgAwaitTimeSum("LCFS") + lcfs.getAvgAwaitTime(), "LCFS");
    avgTimes.setAvgProccessingTimeSum(avgTimes.getAvgProccessingTimeSum("LCFS") + lcfs.getAvgProcessingTime(), "LCFS");

  }

  /**
   *
   */
  private static void startSJF(String pathToSourceFile) {

    SJF sjf = new SJF(pathToSourceFile);
    avgTimes.setAvgAwaitTimeSum(avgTimes.getAvgAwaitTimeSum("SJF") + sjf.getAvgAwaitTime(), "SJF");
    avgTimes.setAvgProccessingTimeSum(avgTimes.getAvgProccessingTimeSum("SJF") + sjf.getAvgProcessingTime(), "SJF");

  }

  /**
   *
   */
  private static void startRR(String pathToSourceFile) {

    RoundRobin rr = new RoundRobin(pathToSourceFile);
    avgTimes.setAvgAwaitTimeSum(avgTimes.getAvgAwaitTimeSum("RoundRobin FCFS") + rr.getAvgAwaitTime(), "RoundRobin FCFS");
    avgTimes.setAvgProccessingTimeSum(avgTimes.getAvgProccessingTimeSum("RoundRobin FCFS") + rr.getAvgProcessingTime(), "RoundRobin FCFS");
    avgTimes.setAvgAwaitTimeSum(avgTimes.getAvgAwaitTimeSum("RoundRobin LCFS") + rr.getAvgAwaitTime(), "RoundRobin LCFS");
    avgTimes.setAvgProccessingTimeSum(avgTimes.getAvgProccessingTimeSum("RoundRobin LCFS") + rr.getAvgProcessingTime(), "RoundRobin LCFS");

  }


}
