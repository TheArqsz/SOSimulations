package allocationproctime.algorithms;

import java.util.ArrayList;

import allocationproctime.processes.Process;
import propertieshandler.PropertiesHandler;

/**
 * This class runs simulation for FCFS schedulding algorithm
 * @author Arkadiusz Maruszczak
 * @see BaseAllocationAlgorithm
 *
 */
public class FCFS extends BaseAllocationAlgorithm {

    /**
     * The constructor for FCFS class.
     * @param pathToSourceFile  specifies path to source file
     * @param isUnderTest       is set to true if method is used in tests. As default it is false.
     *
     */
    public FCFS(String pathToSourceFile, boolean... isUnderTest) {
        boolean isTest = isUnderTest.length > 0 ? isUnderTest[0] : false;
        this.pathToSourceFile = pathToSourceFile;
        this.amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
        this.waitingQueue = new ArrayList<Process>();
        this.readyQueue = new ArrayList<Process>();

        if(!isTest) {
            startProcessing();
        }
    }

    /**
     * <p>
     * Begins schedulding processes
     * </p>
     *
     */
    private void startProcessing() {

        createProcesses();
        executeProcesses();
        createReport("FCFS");
    }

    /**
     * <p>Implements method from super class.</p>
     */
    protected void executeProcesses() {

        boolean allReady = false;
        int i = 0;
        double waitingTime = 0;

        while (!allReady) {
            waitingTime += this.waitingQueue.get(i).getBurstTime();

            for (int j = i + 1; j < this.amnt; j++) {
                this.waitingQueue.get(j).setAwaitingTime(waitingTime);
            }
            this.waitingQueue.get(i).setProcessingTime(this.waitingQueue.get(i).getAwaitingTime() + this.waitingQueue.get(i).getBurstTime());
            this.readyQueue.add(this.waitingQueue.get(i));
            i++;
            if (this.readyQueue.size() == this.amnt) {
                allReady = true;
            }
        }

    }


}
