package allocationproctime.algorithms;

import allocationproctime.processes.Process;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class BaseAllocationAlgorithm {

    List<Process> waitingQueue = null;

    List<Process> readyQueue = null;

    String pathToSourceFile;
    int amnt;

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
    protected void createReport(String nameOfAlgorithm) {

        float avgAwaitTime = 0;
        for (int i = 0; i < this.amnt; i++) {
            avgAwaitTime += this.readyQueue.get(i).getAwaitingTime();
        }
        avgAwaitTime = avgAwaitTime / this.amnt;
        System.out.println(nameOfAlgorithm+": Average await time for " + this.amnt + " processes is equal to: " + avgAwaitTime + " [time unit]");
    }

}
