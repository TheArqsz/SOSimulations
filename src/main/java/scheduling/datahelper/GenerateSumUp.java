package scheduling.datahelper;

import com.opencsv.CSVWriter;
import org.apache.commons.lang3.tuple.Pair;
import propertieshandler.PropertiesHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>Class that allows to generate sum-up after simulation</p>
 * @author Arkadiusz Maruszczak
 *
 */
public class GenerateSumUp {
    /**
     * Stores name of the algorithm with its average waiting and processing times
     */
    protected Map<String, Pair<Double, Double>> algorithmTimesMap = null;

    /**
     *
     */
    protected String destinationFilePath;
    /**
     * The constructor for GenerateSumUp class
     */
    public GenerateSumUp()
    {

        algorithmTimesMap = new HashMap<String, Pair<Double, Double> >();
        Pair<Double, Double> pair = Pair.of((double) 0, (double)0);
        algorithmTimesMap.put("FCFS", pair);
        algorithmTimesMap.put("LCFS", pair);
        algorithmTimesMap.put("SJF", pair);
        algorithmTimesMap.put("RoundRobin FCFS", pair);
        algorithmTimesMap.put("RoundRobin LCFS", pair);
        //destinationFilePath =
    }

    /*public void generateSummary(){
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(new File(filePath)));
            Random r = new Random();
            for (int i = 0; i < amnt; i++) {
                String[] data = { Integer.toString(i), Integer.toString(r.nextInt(Integer.parseInt(PropertiesHandler.getProp("sim.rangeOfBurstTime")))+1) };
                writer.writeNext(data);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }*/

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
        Pair<Double, Double> pair = Pair.of(avgAwaitTime, right);

        algorithmTimesMap.put(nameOfAlgorithm, pair);
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
        Pair<Double, Double> pair = Pair.of(left,avgProccessingTime);

        algorithmTimesMap.put(nameOfAlgorithm, pair);
    }


}
