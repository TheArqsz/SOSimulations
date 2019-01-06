package datahelper;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Class that allows to generate sum-up after simulation</p>
 * @author Arkadiusz Maruszczak
 *
 */
public class GenerateSumUp {
    /**
     * Stores name of the algorithm with its average waiting, amount of page faults and processing times
     */
    protected Map<String, Triple<Double, Double, Double>> algorithmTimesMap = null;

    /**
     * The constructor for GenerateSumUp class
     */
    public GenerateSumUp()
    {
        algorithmTimesMap = new HashMap<String, Triple<Double, Double, Double> >();
        Triple<Double, Double, Double> triple = Triple.of((double) 0, (double)0, (double)0);
        algorithmTimesMap.put("FCFS", triple);
        algorithmTimesMap.put("LCFS", triple);
        algorithmTimesMap.put("SJF", triple);
        algorithmTimesMap.put("RoundRobin FCFS", triple);
        algorithmTimesMap.put("RoundRobin LCFS", triple);
        algorithmTimesMap.put("LRU", triple);
        algorithmTimesMap.put("LFU", triple);
        //destinationFilePath =
    }


    /**
     * Gets sum of average waiting time of process
     * @param nameOfAlgorithm name of the algorithm
     * @return average waiting time of specified algorithm
     */
    public double getAvgAwaitTimeSum(String nameOfAlgorithm) {
        return algorithmTimesMap.get(nameOfAlgorithm).getLeft();
    }

    /**
     * Sets average waiting time of process
     * @param avgAwaitTime average waiting time to be set
     * @param nameOfAlgorithm name of the algorithm
     */
    public void setAvgAwaitTimeSum(double avgAwaitTime, String nameOfAlgorithm) {
        double right = algorithmTimesMap.get(nameOfAlgorithm).getRight();
        double middle = algorithmTimesMap.get(nameOfAlgorithm).getMiddle();
        Triple<Double, Double, Double> triple = Triple.of(avgAwaitTime,middle, right);

        algorithmTimesMap.put(nameOfAlgorithm, triple);
    }

    /**
     * Gets sum of average processing time of process
     * @param nameOfAlgorithm name of the algorithm
     * @return average processing time of specified algorithm
     */
    public double getAvgProccessingTimeSum(String nameOfAlgorithm) {
        return algorithmTimesMap.get(nameOfAlgorithm).getRight();
    }

    /**
     * Sets average processing time of process
     * @param avgProccessingTime average processing time to be set
     * @param nameOfAlgorithm name of the algorithm
     */
    public void setAvgProccessingTimeSum(double avgProccessingTime, String nameOfAlgorithm) {
        double left = algorithmTimesMap.get(nameOfAlgorithm).getLeft();
        double middle = algorithmTimesMap.get(nameOfAlgorithm).getMiddle();
        Triple<Double, Double, Double> triple = Triple.of(left, middle, avgProccessingTime);

        algorithmTimesMap.put(nameOfAlgorithm, triple);
    }

    /**
     * Gets sum of average amount of pages' faults
     * @param nameOfAlgorithm name of the algorithm
     * @return average amount of pages' faults for specified algorithm
     */
    public double getAvgAmountOfFaultsSum(String nameOfAlgorithm) {
        return algorithmTimesMap.get(nameOfAlgorithm).getMiddle();
    }

    /**
     * Sets average amount of pages' faults
     * @param amntOfFaults average amount of pages' faults to be set
     * @param nameOfAlgorithm name of the algorithm
     */
    public void setAvgAmountOfFaultsSum(double amntOfFaults, String nameOfAlgorithm) {
        double right = algorithmTimesMap.get(nameOfAlgorithm).getRight();
        double left = algorithmTimesMap.get(nameOfAlgorithm).getLeft();
        Triple<Double, Double, Double> triple = Triple.of(left, amntOfFaults, right);

        algorithmTimesMap.put(nameOfAlgorithm, triple);
    }


}
