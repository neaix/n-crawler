package n.platform.lianjia;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import n.platform.constants.Config;
import n.platform.constants.PlatformConstants;
import n.platform.core.Parser;
import n.platform.core.proxy.ProxyPool;
import n.platform.domain.House;
import n.platform.domain.Page;
import n.platform.domain.Proxy;
import n.platform.utils.HttpClientUtil;
import n.platform.utils.NumberUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Near
 * @Date: 2018/9/8 17:56
 * @Description:
 */

@Slf4j
public class HouseTask implements Runnable {

    @Getter
    @Setter
    @NotNull
    private String url;

    public HouseTask(String url) {
        this.url = url;
    }

    private static final Parser<House> parser = new HouseParser();

    @Override
    public void run() {
        Page page = null;
        if(Config.USE_PROXY){
            HttpGet httpGet = new HttpGet();
            Proxy currentProxy = ProxyPool.proxys.get(NumberUtils.random(ProxyPool.proxys.size()));
            if(null == currentProxy){
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            HttpHost proxy = new HttpHost(currentProxy.getIp(), currentProxy.getPort());
            httpGet.setConfig(HttpClientUtil.getRequestConfigBuilder().setProxy(proxy).build());
            try {
                page = HttpClientUtil.getPage(httpGet);
            } catch (IOException e) {
                log.error("http request failure,url:{},error msg:{}",url,e.getMessage());
            }finally {
                httpGet.releaseConnection();
            }

        }else{
            try {
                page = HttpClientUtil.getPage(url);
            } catch (IOException e) {
                log.error("http request failure,url:{},error msg:{}",url,e.getMessage());
            }
        }
        if(null != page){
           List<House> list =  parser.parser(page);
           for(House house : list){
               //TODO ³Ö¾Ã»¯£¬db or execl
               log.info(house.toString());
           }
        }
    }
}
