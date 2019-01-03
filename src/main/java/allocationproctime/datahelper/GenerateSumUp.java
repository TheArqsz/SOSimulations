package allocationproctime.datahelper;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateSumUp {
    private Map<String, Pair<Float, Float>> algorithmTimesMap = null;
    public GenerateSumUp()
    {

        algorithmTimesMap = new HashMap<String, Pair<Float, Float> >();
        Pair<Float, Float> pair = Pair.of((float) 0, (float)0);
        algorithmTimesMap.put("FCFS", pair);
        algorithmTimesMap.put("LCFS", pair);
        algorithmTimesMap.put("SJF", pair);
        algorithmTimesMap.put("RoundRobin FCFS", pair);
        algorithmTimesMap.put("RoundRobin LCFS", pair);
    }

    public float getAvgAwaitTimeSum(String nameOfAlgorithm) {
        return algorithmTimesMap.get(nameOfAlgorithm).getLeft();
    }

    public void setAvgAwaitTimeSum(float avgAwaitTime, String nameOfAlgorithm) {
        float right = algorithmTimesMap.get(nameOfAlgorithm).getRight();
        Pair<Float, Float> pair = Pair.of(avgAwaitTime, right);

        algorithmTimesMap.put(nameOfAlgorithm, pair);
    }

    public float getAvgProccessingTimeSum(String nameOfAlgorithm) {
        return algorithmTimesMap.get(nameOfAlgorithm).getRight();
    }

    public void setAvgProccessingTimeSum(float avgProccessingTime, String nameOfAlgorithm) {
        float left = algorithmTimesMap.get(nameOfAlgorithm).getLeft();
        Pair<Float, Float> pair = Pair.of(avgProccessingTime, left);

        algorithmTimesMap.put(nameOfAlgorithm, pair);
    }


}
