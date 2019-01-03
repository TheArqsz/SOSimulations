package allocationproctime.datahelper;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateSumUp {
    private Map<String, Pair<Double, Double>> algorithmTimesMap = null;
    public GenerateSumUp()
    {

        algorithmTimesMap = new HashMap<String, Pair<Double, Double> >();
        Pair<Double, Double> pair = Pair.of((double) 0, (double)0);
        algorithmTimesMap.put("FCFS", pair);
        algorithmTimesMap.put("LCFS", pair);
        algorithmTimesMap.put("SJF", pair);
        algorithmTimesMap.put("RoundRobin FCFS", pair);
        algorithmTimesMap.put("RoundRobin LCFS", pair);
    }

    public double getAvgAwaitTimeSum(String nameOfAlgorithm) {
        return algorithmTimesMap.get(nameOfAlgorithm).getLeft();
    }

    public void setAvgAwaitTimeSum(double avgAwaitTime, String nameOfAlgorithm) {
        double right = algorithmTimesMap.get(nameOfAlgorithm).getRight();
        Pair<Double, Double> pair = Pair.of(avgAwaitTime, right);

        algorithmTimesMap.put(nameOfAlgorithm, pair);
    }

    public double getAvgProccessingTimeSum(String nameOfAlgorithm) {
        return algorithmTimesMap.get(nameOfAlgorithm).getRight();
    }

    public void setAvgProccessingTimeSum(double avgProccessingTime, String nameOfAlgorithm) {
        double left = algorithmTimesMap.get(nameOfAlgorithm).getLeft();
        Pair<Double, Double> pair = Pair.of(avgProccessingTime, left);

        algorithmTimesMap.put(nameOfAlgorithm, pair);
    }


}
