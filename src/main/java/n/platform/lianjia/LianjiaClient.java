package n.platform.lianjia;

import lombok.extern.slf4j.Slf4j;
import n.platform.constants.PlatformConstants;
import n.platform.core.monitor.MonitorThreadPoolTask;
import n.platform.utils.HttpClientUtil;
import n.platform.utils.NumberUtils;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @Author: Near
 * @Date: 2018/9/8 09:38
 * @Description:
 */
@Slf4j
public class LianjiaClient {

    private LianjiaClient(){
        initThreadPool();
    }


    private static LianjiaClient instance;

    public static LianjiaClient getInstance(){
        if(null == instance){
            synchronized (LianjiaClient.class){
                instance = new LianjiaClient();
            }
        }
        return instance;
    }

    private static final HouseParser parser = new HouseParser();

    private ThreadPoolExecutor executor;

    private  void initThreadPool(){
        executor = new ThreadPoolExecutor(PlatformConstants.DEFAULT_THREAD_POOL_SIZE,
                PlatformConstants.DEFAULT_THREAD_POOL_SIZE,
                0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue(20),
                new ThreadPoolExecutor.CallerRunsPolicy());
        //监控开始
        new Thread(new MonitorThreadPoolTask("lianjie-thread-pool",executor)).start();
    }

    public void start(String cityName){
        String url = String.format(PlatformConstants.LIANJIE_BASE_URL,cityName,1);
        try {
            int count = parser.getHouseCount(HttpClientUtil.getPage(url));
            int pageCount = NumberUtils.div(count,PlatformConstants.LIANJIE_PAGE_SIZE);
            log.info("数据条数:{},页数:{}",count,pageCount);
            for(int i = 1; i<= pageCount; i++){
               executor.execute(new HouseTask(String.format(url,i)));
            }



        } catch (IOException e) {
            log.error("processing failure,error msg:{}",e.getMessage());
        }
    }
}
