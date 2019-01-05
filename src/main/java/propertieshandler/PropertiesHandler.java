package propertieshandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class that allows to get data from <a href="file:../simulation.properties"><b>simulation.properties</b></a> file
 * @author Arkadiusz Maruszczak
 *
 */
public class PropertiesHandler {
  /**
   * <pre>
   *     Gets data from specified property's name. Properties are stored under fields named "simulation.NAME" where
   *     NAME is different for each property.
   * </pre>
   *
   * @param property name of property from where the method gets data
   * @return the data under specified property
   */
  public static String getProp(String property) {

    Properties mainProperties = new Properties();

    FileInputStream file;
    try {
      file = new FileInputStream("src/main/resources/simulation.properties");
      mainProperties.load(file);

      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return mainProperties.getProperty(property);
  }
}
