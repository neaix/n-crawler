package n.platform.core.proxy;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.*;

/**
 * @Author: Near
 * @Date: 2018/9/5 15:21
 * @Description: 代理客户端
 */

@Slf4j
public class ProxyClient {

    private ProxyClient(){}

    private static ProxyClient instance;

    public static ProxyClient getInstance(){
        if(null == instance){
            synchronized (ProxyClient.class){
                instance = new ProxyClient();
            }
        }
        return instance;
    }

    private ExecutorService executorService;
    /**
     * 开始爬取代理
     */
    public void start(){
        executorService =  Executors.newFixedThreadPool(40);
        for(String url : ProxyPool.PROXY_SOURCE_URL.keySet()){
            executorService.execute(new ProxyTask(url));
        }
        executorService.shutdown();

    }
}
