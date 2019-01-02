package main;

import algorithms.FCFS;
import algorithms.LCFS;
import algorithms.SJF;

import static datahelper.GenerateData.generateSourceFile;

/**
 * @author amaruszc
 *
 */
public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {

    System.out.println("START");
    generateSourceFile();
    startFCFS();
    startLCFS();
    startSJF();
    System.out.println("END");
  }

  /**
   *
   */
  private static void startFCFS() {

    FCFS fcfs = new FCFS();

  }

  /**
   *
   */
  private static void startLCFS() {

    LCFS lcfs = new LCFS();

  }

  /**
   *
   */
  private static void startSJF() {

    SJF sjf = new SJF();

  }


}
