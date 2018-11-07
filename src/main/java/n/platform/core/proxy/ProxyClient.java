package n.platform.core.proxy;

import lombok.extern.slf4j.Slf4j;
import n.platform.constants.Config;
import n.platform.constants.PlatformConstants;
import n.platform.core.monitor.MonitorThreadPoolTask;
import n.platform.core.proxy.task.ProxyTask;
import n.platform.domain.Proxy;
import n.platform.utils.SerializableUtils;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @Author: Near
 * @Date: 2018/9/5 15:21
 * @Description: 代理客户端
 */

@Slf4j
public class ProxyClient {

    private ProxyClient(){
        initThreadPool();
        initProxyPool();
    }

    private static ProxyClient instance;

    public static ProxyClient getInstance(){
        if(null == instance){
            synchronized (ProxyClient.class){
                instance = new ProxyClient();
            }
        }
        return instance;
    }


    private  ThreadPoolExecutor executor;

    private void initThreadPool(){
        executor = new ThreadPoolExecutor(PlatformConstants.DEFAULT_THREAD_POOL_SIZE,
                                          PlatformConstants.DEFAULT_THREAD_POOL_SIZE,
                                         0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue(20),
                new ThreadPoolExecutor.CallerRunsPolicy());
        //监控开始
        new Thread(new MonitorThreadPoolTask("proxy-thread-pool",executor)).start();
    }

    //初始化代理池
    private void initProxyPool(){
        Object o = SerializableUtils.deSerializable(
                Config.getString("platform.serializable.file"));
        if(null != o){
            Proxy [] array = (Proxy[]) o;
            ProxyPool.proxys.addAll(Arrays.asList(array));
        }
    }
    /**
     * 开始爬取代理
     */
    public void start(){
        for(String url : ProxyPool.PROXY_SOURCE_URL.keySet()){
            executor.execute(new ProxyTask(url));
        }


        while(true){
            if(executor.getActiveCount()  == 0){
                log.info("序列化代理");
                if(ProxyPool.proxys.size() >0){
                    SerializableUtils.serializable(ProxyPool.proxys.toArray(new Proxy[0]),
                            Config.getString("platform.serializable.file"));
                    break;
                }
            }


        }

    }
}
