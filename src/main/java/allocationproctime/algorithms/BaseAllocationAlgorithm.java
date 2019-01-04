package allocationproctime.algorithms;

import allocationproctime.processes.Process;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * <p>Abstract class for all algorithms</p>
 * @see FCFS
 * @see LCFS
 * @see RoundRobin
 * @see SJF
 */

public abstract class BaseAllocationAlgorithm {
    /**
     * <p>Waiting queue in which processes are stored while they are being processed or waiting</p>
     */
    List<Process> waitingQueue = null;
    /**
     * <p>Ready queue in which processes are stored when they finished processing</p>
     */
    List<Process> readyQueue = null;

    /**
     * <p>Path to source file in which processes are stored</p>
     */
    String pathToSourceFile;
    /**
     * <p>Amount of processes used</p>
     */
    int amnt;
    /**
     * <p>Average waiting time of processes</p>
     */
    double avgAwaitTime;
    /**
     * <p>Average processing time of processes</p>
     */
    double avgProcessingTime;

    /**
     *  Abstract method that simulates execution of processes in processor
     */
    protected abstract void executeProcesses();

    /**
     * <p>
     * Creates processes using data from file "processes.csv" and put them to waiting queue
     * </p>
     *
     */
    protected void createProcesses(){
        try {
            Reader reader = Files.newBufferedReader(Paths.get(pathToSourceFile));
            CSVReader csvReader = new CSVReader(reader);
            String[] nextValues;
            while ((nextValues = csvReader.readNext()) != null) {

                this.waitingQueue.add(new Process(Integer.parseInt(nextValues[0]), Double.parseDouble(nextValues[1])));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Calculates average waiting and processing time for specific algorithm
     *  @param nameOfAlgorithm is equal to name of algorithm
     *  @param isUnderTest is set to true if method is used in tests. As default it is false.
     */
    protected void createReport(String nameOfAlgorithm, boolean... isUnderTest) {

        boolean isTest = isUnderTest.length > 0 ? isUnderTest[0] : false;
        this.avgAwaitTime = 0;
        for (int i = 0; i < this.amnt; i++) {
            this.avgAwaitTime += this.readyQueue.get(i).getAwaitingTime();
        }
        this.avgAwaitTime = this.avgAwaitTime / this.amnt;

        this.avgProcessingTime = 0;
        for (int i = 0; i < this.amnt; i++) {
            this.avgProcessingTime += this.readyQueue.get(i).getProcessingTime();
        }
        this.avgProcessingTime = this.avgProcessingTime / this.amnt;
        /*
          //Temporary disabled
          if(!isTest) {
            System.out.println(nameOfAlgorithm + ": Average await time for " + this.amnt + " processes is equal to: " + this.avgAwaitTime + " [time unit]");
            System.out.println(nameOfAlgorithm + ": Average processing time for " + this.amnt + " processes is equal to: " + this.avgProcessingTime + " [time unit]");
        }
        */

    }

    /**
     * Gets average waiting time of processes
     * @return the value under the avgAwaitTime variable
     */
    public double getAvgAwaitTime(){
        return this.avgAwaitTime;
    }

    /**
     * Gets average processing time of processes
     * @return the value under the avgAwaitTime variable
     */
    public double getAvgProcessingTime(){
        return this.avgProcessingTime;
    }

}
