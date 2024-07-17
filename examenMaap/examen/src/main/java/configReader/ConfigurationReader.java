package configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigurationReader {
    private static final String CONFIG_FILE = "C:\\Users\\vlaic\\examenMaap\\examen\\src\\main\\java\\configReader\\settings.properties";
    private Properties properties = new Properties();

    public Map<String, String> config() {
        Map<String, String> elements = new HashMap<>();
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);

            String patientsFile = properties.getProperty("Song");
            elements.put("Song", patientsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elements;
    }
}
