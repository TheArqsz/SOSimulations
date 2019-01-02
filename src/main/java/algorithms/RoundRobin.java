package algorithms;

import com.opencsv.CSVReader;
import processes.Process;
import propertieshandler.PropertiesHandler;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RoundRobin {
    private int amnt;
    private int timeQuantum;
    private List<Process> waitingQueue;

    private List<Process> readyQueue;

    private Boolean[] runningQueue;

    /**
     * The constructor.
     *
     */
    public RoundRobin() {

        this.amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
        this.waitingQueue = new ArrayList<Process>();
        this.readyQueue = new ArrayList<Process>();
        this.timeQuantum = Integer.parseInt(PropertiesHandler.getProp("sim.timeQuantumRR"));
        runningQueue=new Boolean[amnt];
        for(int i=0;i<amnt;i++){
            runningQueue[i]=true;
        }
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
        executeProcessesFCFS();
        createReport();
        executeProcessesLCFS();
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

    }

    /**
     *
     */
    private void executeProcessesFCFS() {

        boolean allReady = false;
        int i = 0;
        int waitingTime = 0;

        while (!allReady) {
            waitingTime += this.timeQuantum - this.waitingQueue.get(i).getBurstTime()%this.timeQuantum;
            this.waitingQueue.get(i).setProcessingTime(waitingTime);
            this.waitingQueue.get(i).setBurstTime( this.waitingQueue.get(i).getBurstTime()-timeQuantum);
            for (int j = i + 1; j < this.amnt; j++) {
                this.waitingQueue.get(j).setAwaitingTime(waitingTime);
            }
            this.readyQueue.add(this.waitingQueue.get(i));
            i++;
            if (this.readyQueue.size() == this.amnt) {
                allReady = true;
            }
        }

    }

    /**
     *
     */
    private void executeProcessesLCFS() {

        boolean allReady = false;
        int i = amnt-1;
        int waitingTime = 0;

        while (!allReady) {
            waitingTime += this.waitingQueue.get(i).getBurstTime();
            for (int j = i + 1; j < this.amnt; j++) {
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

        int avgAwaitTime = 0;
        for (int i = 0; i < this.amnt; i++) {
            avgAwaitTime += this.readyQueue.get(i).getAwaitingTime();
        }
        avgAwaitTime = avgAwaitTime / this.amnt;
        System.out.println("FCFS: Average await time for " + this.amnt + " processes is equal to: " + avgAwaitTime + " [time unit]");
        this.readyQueue = new ArrayList<Process>();
    }
}
