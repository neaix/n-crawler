package n.platform.core.proxy;

import n.platform.constants.PlatformConstants;
import n.platform.core.proxy.parser.Ip66ProxyParser;
import n.platform.core.proxy.parser.XiciProxyParser;
import n.platform.domain.Proxy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Near
 * @Date: 2018/9/5 14:55
 * @Description: proxy pool
 */

public class ProxyPool {

    public static final ReentrantReadWriteLock  lock = new ReentrantReadWriteLock();
    public static final Set<Proxy> proxys = new HashSet<Proxy>(100);
    public static final Map<String,Class> PROXY_SOURCE_URL = new HashMap<String, Class>(10);
    static {
            for(int i = 1; i <= 10; i++){
                PROXY_SOURCE_URL.put(PlatformConstants.XICI_BASE_URL+i, XiciProxyParser.class);
                PROXY_SOURCE_URL.put(PlatformConstants.IP66_BASE_URL+i, Ip66ProxyParser.class);
            }
    }

}