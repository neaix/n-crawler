package n.platform.core.proxy;

import lombok.extern.slf4j.Slf4j;
import n.platform.core.proxy.task.ProxyTask;

import java.util.concurrent.*;

/**
 * @Author: Near
 * @Date: 2018/9/5 15:21
 * @Description: 代理客户端
 */

@Slf4j
public class ProxyClient {

    private ProxyClient(){
    }

    private static ProxyClient instance;

    public static volatile boolean isStop = false;

    public static ProxyClient getInstance(){
        if(null == instance){
            synchronized (ProxyClient.class){
                instance = new ProxyClient();
            }
        }
        return instance;
    }

    private static ExecutorService executorService;

    //TODO 添加线程log,以及使用ThreadPoolExecutor定制线程池。
    private void initTheadPool(){

    }

    //TODO 实现线程池关闭策略
    /**
     * 停止代理采集任务
     */
    public static void stop(){
        executorService.shutdownNow();
    }

    /**
     * 开始爬取代理
     */
    public void start(){
        executorService =  Executors.newFixedThreadPool(40);
        for(String url : ProxyPool.PROXY_SOURCE_URL.keySet()){

            executorService.execute(new ProxyTask(url));
        }
        executorService.shutdown();
        isStop = true;
    }
}
