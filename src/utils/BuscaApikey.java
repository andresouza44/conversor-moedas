package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BuscaApikey {

    public static String getApiKey(String apiName) {
        try {
            Properties prop = new Properties();
            FileInputStream file = null;
            file = new FileInputStream("./properties/config.properties");
            prop.load(file);
            file.close();

            return prop.getProperty(apiName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
