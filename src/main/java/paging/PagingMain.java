package paging;

import com.opencsv.CSVWriter;
import datahelper.GenerateData;
import datahelper.GenerateSumUp;
import org.apache.commons.math3.util.Precision;
import paging.algorithms.LFU;
import paging.algorithms.LRU;
import propertieshandler.PropertiesHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Main class for paging simulation.
 *
 * @author Arkadiusz Maruszczak
 * <p>Main class for simulation "Paging"</p>
 *
 */
public class PagingMain {
    /**
     * Object that collects average data from each algorithm and sums up it at the end
     */
    static GenerateSumUp avgFaults;

    /**
     * Amount of pages specified in properties file
     */
    static int amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfPages"));

    /**
     * <p>Main method for simulation "Paging"</p>
     *
     * @param args main method arguments
     */
    public static void main(String[] args) {
        /**
         *  Variable that stores amount of tries for simulation
         */
        int tries = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfTries"));
        /**
         * Array that stores all avaible frames for simulations
         */
        Integer[] avaiblePhysicalMemoryFrames = GenerateData.processArrayToInt(PropertiesHandler.getProp("sim.avaiblePhysicalMemoryFrames").split(","));

        HashMap<Integer, GenerateSumUp> results = new HashMap<>();


        /**
         * Loop that generates source files
         */
        for (int i =1; i<=tries; i++){
            String pathToSourceFile = PropertiesHandler.getProp("sim.pathToPagesData") + PropertiesHandler.getProp("sim.baseNameOfPageFile") + i + PropertiesHandler.getProp("sim.extension");
            GenerateData.generatePagesSourceFile(pathToSourceFile);
        }

        /**
         *  Main loop for simulation
         */
        for(int x=0;x<avaiblePhysicalMemoryFrames.length;x++) {

            avgFaults = new GenerateSumUp();

            /**
             *  Loop that goes through all the tries specified in properties file
             */
            for (int i = 1; i <= tries; i++) {
                /**
                 *  Path to each source file that contains process id and burst time
                 */
                String pathToSourceFile = PropertiesHandler.getProp("sim.pathToPagesData") + PropertiesHandler.getProp("sim.baseNameOfPageFile") + i + PropertiesHandler.getProp("sim.extension");

                //System.out.println("START RUN NUMBER " + i);
                startLRU(pathToSourceFile, avaiblePhysicalMemoryFrames[x]);
                startLFU(pathToSourceFile, avaiblePhysicalMemoryFrames[x]);
                //System.out.println("END");
            }

            results.put(avaiblePhysicalMemoryFrames[x], avgFaults);
            System.out.println("\nAmount of pages: " + amnt);
            System.out.println("Amount of tries: " + tries);
            System.out.println("Used value of avaible frames in physical memory: " + avaiblePhysicalMemoryFrames[x]);
            System.out.println("\n\nLRU: avg amount of page faults : " + Precision.round(avgFaults.getAvgAmountOfFaultsSum("LRU") / tries, 3) );
            System.out.println("LFU: avg amount of page faults : " + Precision.round(avgFaults.getAvgAmountOfFaultsSum("LFU") / tries, 3) );
        }
        generateSummary(results, amnt, tries);
    }



    /**
     *  <p>Starts the LRU paging algorithm</p>
     * @param pathToSourceFile  specifies path to source files
     * @param avaiblePhysicalMemoryFrames amount of frames in memory queue
     */
    private static void startLRU(String pathToSourceFile, int avaiblePhysicalMemoryFrames) {

        LRU lru = new LRU(pathToSourceFile, avaiblePhysicalMemoryFrames, false);
        avgFaults.setAvgAmountOfFaultsSum(avgFaults.getAvgAmountOfFaultsSum("LRU") + lru.getPageFault(), "LRU");

    }

    /**
     * <p>Starts the LFU paging algorithm</p>
     *  @param pathToSourceFile   specifies path to source files
     *  @param avaiblePhysicalMemoryFrames amount of frames in memory queue
     */
    private static void startLFU(String pathToSourceFile, int avaiblePhysicalMemoryFrames) {

        LFU lfu = new LFU(pathToSourceFile, avaiblePhysicalMemoryFrames);
        avgFaults.setAvgAmountOfFaultsSum(avgFaults.getAvgAmountOfFaultsSum("LFU") + lfu.getPageFault(), "LFU");

    }

    /**
     * Generates summary from previously created sumUp objects {@link #avgFaults}
     *
     * @param results map of sumUp objects with avaible frames in memory for each.
     * @param amountOfPages amount of pages specified in <a href="file:../simulationproperties.html"><b>simulation.properties</b></a>.
     * @param tries amount of tries for simulation specified in <a href="file:../simulationproperties.html"><b>simulation.properties</b></a>.
     */
    private static void generateSummary(HashMap<Integer, GenerateSumUp> results, int amountOfPages, int tries){
        try {
            String destinationPath = PropertiesHandler.getProp("sim.pathToPagesSummary") + PropertiesHandler.getProp("sim.pagesSummaryName");
            CSVWriter writer = new CSVWriter(new FileWriter(new File(destinationPath)));

            /**
             * Array that stores all avaible frames for simulations
             */
            Integer[] avaiblePhysicalMemoryFrames = GenerateData.processArrayToInt(PropertiesHandler.getProp("sim.avaiblePhysicalMemoryFrames").split(","));
            {
                String[] header = {"Avaible frames in physical memory", "Algorithm", "Amount of pages", "Average amount of page faults"};
                writer.writeNext(header);
            }

            for(int i=0; i<avaiblePhysicalMemoryFrames.length;i++) {

                String[] algorithmNames = {"LRU", "LFU"};
                for (String algorithmName: algorithmNames) {
                    double avgAmountOfFaults = Precision.round(results.get(avaiblePhysicalMemoryFrames[i]).getAvgAmountOfFaultsSum(algorithmName) / tries, 3);
                    String[] data = {Integer.toString(avaiblePhysicalMemoryFrames[i]), algorithmName, Integer.toString(amountOfPages), Double.toString(avgAmountOfFaults) };
                    writer.writeNext(data);

                }
                writer.writeNext(new String[0]);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
