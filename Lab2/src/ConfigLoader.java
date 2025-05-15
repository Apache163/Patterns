import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    // Экземпляр Singleton
    private static ConfigLoader instance;
    private Properties properties;


    private ConfigLoader() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }

    public static void main(String[] args) {
        ConfigLoader configLoader = ConfigLoader.getInstance();

        Properties properties = configLoader.getProperties();

        System.out.println("App: " + properties.getProperty("app.name"));
        System.out.println("Version: " + properties.getProperty("app.version"));
    }
}
