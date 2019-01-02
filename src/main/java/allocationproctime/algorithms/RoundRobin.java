package allocationproctime.algorithms;

import com.opencsv.CSVReader;
import allocationproctime.processes.Process;
import propertieshandler.PropertiesHandler;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RoundRobin {
    String pathToSourceFile;
    private int amnt;
    private int timeQuantum;
    private List<Process> waitingQueue;

    private List<Process> readyQueue;

    private Boolean[] isReady;

    /**
     * The constructor.
     *
     */
    public RoundRobin(String pathToSourceFile) {
        this.pathToSourceFile = pathToSourceFile;
        this.amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
        this.waitingQueue = new ArrayList<Process>();
        this.readyQueue = new ArrayList<Process>();
        this.timeQuantum = Integer.parseInt(PropertiesHandler.getProp("sim.timeQuantumRR"));
        isReady = new Boolean[amnt];
        for(int i = 0; i<amnt; i++){
            isReady[i]=false;
        }
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
        executeProcessesFCFS();
        createReport("Round Robin FCFS");
        executeProcessesLCFS();
        createReport("Round Robin LCFS");
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
    private void executeProcessesFCFS() {

        boolean allReady = false;
        int i = 0;

        while (!allReady) {
            int proccessingTime = timeQuantum;
            while(this.waitingQueue.get(i).getBurstTime()-proccessingTime<0){
                proccessingTime-=1;
            }
            for (int j = 0; j < this.amnt; j++) {
                if(j!=i && !isReady[j]){
                    this.waitingQueue.get(j).setAwaitingTime(this.waitingQueue.get(j).getAwaitingTime() + proccessingTime);
                }else if(j==i && this.waitingQueue.get(j).getBurstTime()>=0){
                    if(this.waitingQueue.get(j).getBurstTime()==0 && !isReady[j]) {
                        this.readyQueue.add(this.waitingQueue.get(j));
                        isReady[j] = true;
                        continue;
                    }
                    this.waitingQueue.get(j).setProcessingTime(this.waitingQueue.get(j).getProcessingTime() + proccessingTime);
                    this.waitingQueue.get(j).setBurstTime(this.waitingQueue.get(j).getBurstTime() - proccessingTime);

                }

            }
            i++;
            if (this.readyQueue.size() == this.amnt) {
                allReady = true;
            }else if(i==amnt) {
                i=0;
            }
        }

    }

    /**
     *
     */
    private void executeProcessesLCFS() {

        boolean allReady = false;
        int i = amnt-1;

        while (!allReady) {
            int proccessingTime = timeQuantum;
            while(this.waitingQueue.get(i).getBurstTime()-proccessingTime<0){
                proccessingTime-=1;
            }
            for (int j = amnt-1; j >= 0; j--) {
                if(j!=i && !isReady[j]){
                    this.waitingQueue.get(j).setAwaitingTime(this.waitingQueue.get(j).getAwaitingTime() + proccessingTime);
                }else if(j==i && this.waitingQueue.get(j).getBurstTime()>=0){
                    if(this.waitingQueue.get(j).getBurstTime()==0 && !isReady[j]) {
                        this.readyQueue.add(this.waitingQueue.get(j));
                        isReady[j] = true;
                        continue;
                    }
                    this.waitingQueue.get(j).setProcessingTime(this.waitingQueue.get(j).getProcessingTime() + proccessingTime);
                    this.waitingQueue.get(j).setBurstTime(this.waitingQueue.get(j).getBurstTime() - proccessingTime);

                }

            }
            i--;
            if (this.readyQueue.size() == this.amnt) {
                allReady = true;
            }else if(i<0) {
                i=amnt-1;
            }
        }

    }

    /**
     *
     */
    private void createReport(String name) {

        float avgAwaitTime = 0;
        for (int i = 0; i < this.amnt; i++) {
            avgAwaitTime += this.readyQueue.get(i).getAwaitingTime();
        }
        avgAwaitTime = avgAwaitTime / this.amnt;
        System.out.println(name+": Average await time for " + this.amnt + " algorithms.allocationproctime.processes is equal to: " + avgAwaitTime + " [time unit]");
        this.readyQueue = new ArrayList<Process>();
        for(int i = 0; i<amnt; i++){
            isReady[i]=false;
        }
    }
}
