package allocationproctime.algorithms;

import allocationproctime.processes.Process;
import propertieshandler.PropertiesHandler;

import java.util.ArrayList;

/**
 * This class runs simulation for Round-Robin schedulding algorithm
 * @author Arkadiusz Maruszczak
 * @see BaseAllocationAlgorithm
 *
 */
public class RoundRobin extends BaseAllocationAlgorithm{

    /**
     * <p>Variable that stores time quantum value</p>
     */
    protected double timeQuantum;

    /**
     * <p>Array that stores boolean values. If process finishes, value in <b>isReady</b> array under index equal to its id is set to true. As default all values are set to false.  </p>
     */
    protected Boolean[] isReady;

    /**
     * The constructor for RoundRobin class.
     * @param pathToSourceFile  specifies path to source file
     * @param timeQuantum       specifies time quantum for Round-Robin simulation
     * @param isUnderTest       is set to true if method is used in tests. As default it is false.
     *
     */
    public RoundRobin(String pathToSourceFile, double timeQuantum, boolean... isUnderTest) {
        boolean isTest = isUnderTest.length > 0 ? isUnderTest[0] : false;
        this.pathToSourceFile = pathToSourceFile;
        this.amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
        this.waitingQueue = new ArrayList<Process>();
        this.readyQueue = new ArrayList<Process>();
        this.timeQuantum = !isTest ? timeQuantum : 1;
        isReady = new Boolean[amnt];
        for(int i = 0; i<amnt; i++){
            isReady[i]=false;
        }
        if(!isTest) {
            startProcessing();
        }
    }

    /**
     * <p>
     * Begins schedulding algorithms.allocationproctime.processes
     * </p>
     *
     */
    private void startProcessing() {

        createProcesses();
        executeProcesses();

    }

    /**
     * <p>Overrides method from super class.</p>
     */
    @Override
    protected void executeProcesses() {
        executeProcessesFCFS();
        createReport("Round Robin FCFS");
        executeProcessesLCFS();
        createReport("Round Robin LCFS");
    }

    /**
     *
     * Simulates the execution of processes in processor using RoundRobin FCFS algorithm
     *
     */
    protected void executeProcessesFCFS() {

        boolean allReady = false;
        int i = 0;

        while (!allReady) {
            double proccessingTime = timeQuantum;
            while(this.waitingQueue.get(i).getBurstTime()-proccessingTime<0){
                proccessingTime-=1;
            }

            for (int j = 0; j < this.amnt; j++) {
                if(j!=i && !isReady[j]){
                    this.waitingQueue.get(j).setAwaitingTime(this.waitingQueue.get(j).getAwaitingTime() + proccessingTime);
                }else if(j==i && this.waitingQueue.get(j).getBurstTime()>=0){
                    if(this.waitingQueue.get(j).getBurstTime()==0 && !isReady[j]) {
                        this.waitingQueue.get(j).setProcessingTime(this.waitingQueue.get(j).getProcessingTime()+this.waitingQueue.get(j).getAwaitingTime());
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
     * Simulates the execution of processes in processor using RoundRobin FCFS algorithm
     *
     */
    protected void executeProcessesLCFS() {

        boolean allReady = false;
        int i = amnt-1;

        while (!allReady) {
            double proccessingTime = timeQuantum;
            while(this.waitingQueue.get(i).getBurstTime()-proccessingTime<0){
                proccessingTime-=1;
            }

            for (int j = amnt-1; j >= 0; j--) {
                if(j!=i && !isReady[j]){
                    this.waitingQueue.get(j).setAwaitingTime(this.waitingQueue.get(j).getAwaitingTime() + proccessingTime);
                }else if(j==i && this.waitingQueue.get(j).getBurstTime()>=0){
                    if(this.waitingQueue.get(j).getBurstTime()==0 && !isReady[j]) {
                        this.waitingQueue.get(j).setProcessingTime(this.waitingQueue.get(j).getProcessingTime()+this.waitingQueue.get(j).getAwaitingTime());
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
