package n.platform.lianjia;

import lombok.extern.slf4j.Slf4j;
import n.platform.constants.PlatformConstants;
import n.platform.utils.HttpClientUtil;
import n.platform.utils.NumberUtils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Near
 * @Date: 2018/9/8 09:38
 * @Description:
 */
@Slf4j
public class LianjiaClient {

    private LianjiaClient(){
        executorService = Executors.newFixedThreadPool(40);
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

    private ExecutorService executorService;
    private static final HouseParser parser = new HouseParser();

    public void start(String cityName){
        String url = String.format(PlatformConstants.LIANJIE_BASE_URL,cityName,1);
        try {
            int count = parser.getHouseCount(HttpClientUtil.getPage(url));
            int pageCount = NumberUtils.div(count,PlatformConstants.LIANJIE_PAGE_SIZE);
            log.info("数据条数:{},页数:{}",count,pageCount);
            for(int i = 1; i<= pageCount; i++){
                executorService.execute(new HouseTask(String.format(url,i)));
            }

        } catch (IOException e) {
            log.error("processing failure,error msg:{}",e.getMessage());
        }finally {
            executorService.shutdownNow();
        }
    }

}
