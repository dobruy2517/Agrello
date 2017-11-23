package helpers;

import java.io.IOException;
import java.io.InputStream;

public class Properties {

    private java.util.Properties property;

    private Properties() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.properties");
        property = new java.util.Properties();
        try {
            property.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final Properties INSTANCE = new Properties();

    public static Properties getInstance(){
        return INSTANCE;
    }
    public String getResourceByName(String name){
        return property.getProperty(name);
    }
}
