package n.platform;

import n.platform.core.proxy.ProxyClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Near
 * @Date: 2018/9/4 16:28
 * @Description:≈¿≥Ê∆Ù∂Ø¿‡
 */

public class Appliaction {
    private static final Logger log = LoggerFactory.getLogger(Appliaction.class);
    public static void main(String[] args) {
        ProxyClient.getInstance().start();
    }
}
