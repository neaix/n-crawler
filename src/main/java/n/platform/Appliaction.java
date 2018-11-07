package n.platform;

import lombok.extern.slf4j.Slf4j;
import n.platform.core.proxy.ProxyClient;
import n.platform.lianjia.LianjiaClient;


/**
 * @Author: Near
 * @Date: 2018/9/4 16:28
 * @Description:≈¿≥Ê∆Ù∂Ø¿‡
 */
@Slf4j
public class Appliaction {
    public static void main(String[] args) {
        ProxyClient.getInstance().start();
        LianjiaClient.getInstance().start("fs");

    }
}
