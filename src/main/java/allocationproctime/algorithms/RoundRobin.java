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
import java.util.concurrent.TimeUnit;

public class RoundRobin extends BaseAllocationAlgorithm{

    private int timeQuantum;

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
        executeProcesses();

    }

    @Override
    protected void executeProcesses() {
        executeProcessesFCFS();
        createReport("Round Robin FCFS");
        executeProcessesLCFS();
        createReport("Round Robin LCFS");
    }

    /**
     *
     * Method that simulates the execution of processes in processor
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
     * Method that simulates the execution of processes in processor
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
}
