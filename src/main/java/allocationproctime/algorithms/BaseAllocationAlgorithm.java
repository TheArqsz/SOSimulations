package allocationproctime.algorithms;

import allocationproctime.processes.Process;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static allocationproctime.datahelper.GenerateSumUp.*;

public abstract class BaseAllocationAlgorithm {

    List<Process> waitingQueue = null;

    List<Process> readyQueue = null;

    String pathToSourceFile;
    int amnt;
    double avgAwaitTime;
    double avgProcessingTime;

    /**
     *  Abstract method that simulates the execution of processes in processor
     */
    protected abstract void executeProcesses();

    /**
     * <p>
     * Create processes using data from file "processes.csv" and put them to waiting queue
     * </p>
     *
     */
    protected void createProcesses(){
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
     *  Generates results
     * @param nameOfAlgorithm is equal to name of algorithm
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

    public double getAvgAwaitTime(){
        return this.avgAwaitTime;
    }

    public double getAvgProcessingTime(){
        return this.avgProcessingTime;
    }

}
