package datahelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import com.opencsv.CSVWriter;

import propertieshandler.PropertiesHandler;

/**
 * Class that allows to generate source files necessary for simulation
 * @author Arkadiusz Maruszczak
 *
 */
public class GenerateData {

  /**
   * Checks if user wants data to be generated and if specified path is proper.
   * <pre>
   *     Method uses predefined field from <a href="file:../simulationproperties.html"><b>simulation.properties</b></a>
   * </pre>
   */
  public static void generateProcessesSourceFile(String filePath) {
    if(Boolean.parseBoolean(PropertiesHandler.getProp("sim.generateData"))){
      //System.out.println("Generating data source file to: \"" +filePath + "\"");
      int amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
      generateProcessesData(amnt, filePath);
    }else{
      //System.out.println("Using existing file located in \"" + filePath +  "\"") ;
      try{
        Reader f = Files.newBufferedReader(Paths.get(filePath));
        f.close();
      }catch(IOException e){
        System.out.println("File cannot be processed. " + e.toString());
        System.exit(-1);
      }
    }


  }

  /**
   * Generates source files using parameters.
   * @param amnt amount of processes specified in properties file
   * @param filePath path to source file
   *
   */
  private static void generateProcessesData(int amnt, String filePath) {

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

  /**
   * Checks if user wants data to be generated and if specified path is proper.
   * <pre>
   *     Method uses predefined field from <a href="file:../simulationproperties.html"><b>simulation.properties</b></a>
   * </pre>
   */
  public static void generatePagesSourceFile(String filePath) {
    if(Boolean.parseBoolean(PropertiesHandler.getProp("sim.generateData"))){
      //System.out.println("Generating data source file to: \"" +filePath + "\"");
      int amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfPages"));
      generatePagesData(amnt, filePath);
    }else{
      //System.out.println("Using existing file located in \"" + filePath +  "\"") ;
      try{
        Reader f = Files.newBufferedReader(Paths.get(filePath));
        f.close();
      }catch(IOException e){
        System.out.println("File cannot be processed. " + e.toString());
        System.exit(-1);
      }
    }


  }

  /**
   * Generates source files using parameters.
   * @param amnt amount of pages specified in properties file
   * @param filePath path to source file
   *
   */
  private static void generatePagesData(int amnt, String filePath) {

    try {
      CSVWriter writer = new CSVWriter(new FileWriter(new File(filePath)));
      Random r = new Random();
      for (int i = 0; i < Integer.parseInt(PropertiesHandler.getProp("sim.lengthOfReferenceList")); i++) {
        String[] data = {Integer.toString(r.nextInt(Integer.parseInt(PropertiesHandler.getProp("sim.amountOfPages")))+1) };
        writer.writeNext(data);
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Converts String array to Double array
   * @param strings array that contains String values to be converted to Double
   * @return array that contains Double values converted from String
   */
  public static Double[] processArrayToDbl(String[] strings) {
    Double[] array=new Double[strings.length];
    int i=0;
    for(String str:strings){
      array[i]=Double.parseDouble(str.trim());
      i++;
    }
    return array;
  }

  /**
   * Converts String array to Integer array
   * @param strings array that contains String values to be converted to Integer
   * @return array that contains Integer values converted from String
   */
  public static Integer[] processArrayToInt(String[] strings) {
    Integer[] array=new Integer[strings.length];
    int i=0;
    for(String str:strings){
      array[i]=Integer.parseInt(str.trim());
      i++;
    }
    return array;
  }
}
