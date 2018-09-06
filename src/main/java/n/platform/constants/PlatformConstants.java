package n.platform.constants;

/**
 * @Author: Near
 * @Date: 2018/9/5 15:06
 * @Description:
 */

public class PlatformConstants {
    /**
     * http超时时间
     */
    public static final int HTTP_TIME_OUT = 10000;

    public final static String[] USER_AGENTS = new String[]{
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2623.110 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2623.110 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2623.110 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2623.110 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2623.110 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2623.110 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2623.110 Safari/537.36",
            "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:50.0) Gecko/20100101 Firefox/50.0",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.115 Safari/537.36"
    };

    /**
     * 加载代理数量
     */
    public static final int PROXY_MAX_COUNT = 1000;

    /**
     * 使用代理
     */
    public static final int USE_PROXY = 1;

    /**
     * 不使用代理
     */
    public static final int NON_USE_PROXY = 0;

    /**西刺url*/
    public static final String XICI_BASE_URL = "http://www.xicidaili.com/nn/";
    /**ip66 url*/
    public static final String IP66_BASE_URL = "http://www.66ip.cn/";

    public static final String PROXY_XICI = "xici";

    public static final String PROXY_IP66 = "ip66";
}
