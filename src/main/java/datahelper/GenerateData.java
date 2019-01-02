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
 * @author amaruszc
 *
 */
public class GenerateData {

  /**
   * <p>Method that generates data for simulations</p>
   */
  public static void generateSourceFile() {
    if(Boolean.parseBoolean(PropertiesHandler.getProp("sim.generateData"))){
      System.out.println("Generating data source file to: \"" + PropertiesHandler.getProp("sim.pathToProcessesData") + "\"\n");
      int amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
      generateData(amnt);
    }else{
      System.out.println("Using existing file located in \"" + PropertiesHandler.getProp("sim.pathToProcessesData") + "\"\n") ;
      try{
        Reader f = Files.newBufferedReader(Paths.get(PropertiesHandler.getProp("sim.pathToProcessesData")));
        f.close();
      }catch(IOException e){
        System.out.println("File cannot be processed. " + e.toString());
        System.exit(-1);
      }
    }


  }

  /**
   * @param amnt
   *
   */
  private static void generateData(int amnt) {

    try {
      CSVWriter writer = new CSVWriter(new FileWriter(new File(PropertiesHandler.getProp("sim.pathToProcessesData"))));
      Random r = new Random();
      for (int i = 0; i < amnt; i++) {
        String[] data = { Integer.toString(i), Integer.toString(r.nextInt(10)) };
        writer.writeNext(data);
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
