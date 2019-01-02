package allocationproctime.datahelper;

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
  public static void generateSourceFile(String filePath) {
    if(Boolean.parseBoolean(PropertiesHandler.getProp("sim.generateData"))){
      System.out.println("Generating data source file to: \"" +filePath + "\"\n");
      int amnt = Integer.parseInt(PropertiesHandler.getProp("sim.amountOfProcesses"));
      generateData(amnt, filePath);
    }else{
      System.out.println("Using existing file located in \"" + filePath +  "\"\n") ;
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
   * @param amnt
   *
   */
  private static void generateData(int amnt, String filePath) {

    try {
      CSVWriter writer = new CSVWriter(new FileWriter(new File(filePath)));
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
