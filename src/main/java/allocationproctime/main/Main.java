package allocationproctime.main;

import allocationproctime.algorithms.FCFS;
import allocationproctime.algorithms.LCFS;
import allocationproctime.algorithms.RoundRobin;
import allocationproctime.algorithms.SJF;
import allocationproctime.datahelper.GenerateData;
import propertieshandler.PropertiesHandler;

/**
 * @author amaruszc
 *
 */
public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    int tries = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfTries"));
    for(int i=1; i<=tries; i++) {
      String pathToSourceFile = PropertiesHandler.getProp("sim.pathToProcessesData")+ PropertiesHandler.getProp("sim.baseNameOfFile") + i + PropertiesHandler.getProp("sim.extension");
      System.out.println("START RUN NUMBER " + i);
      GenerateData.generateSourceFile(pathToSourceFile);
      startFCFS(pathToSourceFile);
      startLCFS(pathToSourceFile);
      startSJF(pathToSourceFile);
      startRR(pathToSourceFile);
      System.out.println("\nEND\n");
    }
  }

  /**
   *
   */
  private static void startFCFS(String pathToSourceFile) {

    FCFS fcfs = new FCFS(pathToSourceFile, false);

  }

  /**
   *
   */
  private static void startLCFS(String pathToSourceFile) {

    LCFS lcfs = new LCFS(pathToSourceFile);

  }

  /**
   *
   */
  private static void startSJF(String pathToSourceFile) {

    SJF sjf = new SJF(pathToSourceFile);

  }

  /**
   *
   */
  private static void startRR(String pathToSourceFile) {

    RoundRobin rr = new RoundRobin(pathToSourceFile);

  }


}
