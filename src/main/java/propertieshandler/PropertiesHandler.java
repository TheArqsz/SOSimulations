package propertieshandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author AMARUSZC
 *
 */
public class PropertiesHandler {
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
