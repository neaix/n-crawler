package n.platform.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author: Near
 * @Date: 2018/9/5 15:36
 * @Description: ≈‰÷√¿‡
 */

public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);
    public static final boolean USE_PROXY;
    private static Properties properties;
    static {
        init();
        USE_PROXY = getInt("platform.use_proxy",PlatformConstants.NON_USE_PROXY) == PlatformConstants.USE_PROXY;
    }

    private static void init() {
         properties = new Properties();
        try {
            properties.load(new FileInputStream("application.properties"));
        } catch (IOException e) {
            log.error("load appliaction.properties failure.",e);
        }

    }

    private static int getInt(String key,int defaultVal){
        String val = properties.getProperty(key,String.valueOf(defaultVal));
        return Integer.valueOf(val);
    }

}
