package n.platform.constants;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: Near
 * @Date: 2018/9/5 15:36
 * @Description: ≈‰÷√¿‡
 */

@Slf4j
public class Config {
    private static Properties properties;
    static {
        init();
    }

    private static void init() {
         properties = new Properties();
        ClassLoader classLoader = Config.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream("application.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            log.error("load propities failure,msg:{}",e.getMessage());
        }

    }

    public static int getInt(String key,int defaultVal){
        String val = properties.getProperty(key,String.valueOf(defaultVal));
        return Integer.valueOf(val);
    }

    public static String getString(String key){
       return properties.getProperty(key);
    }

}
