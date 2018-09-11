package n.platform.core.proxy.task;
import com.sun.istack.internal.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import n.platform.core.Parser;
import n.platform.core.proxy.ProxyParserFactory;
import n.platform.core.proxy.ProxyPool;
import n.platform.domain.Page;
import n.platform.domain.Proxy;
import n.platform.utils.HttpClientUtil;
import org.apache.http.HttpStatus;
import org.jsoup.helper.StringUtil;
import java.io.IOException;
import java.util.List;

/**
 * @Author: Near
 * @Date: 2018/9/6 15:04
 * @Description:代理任务
 */

@Slf4j
@ToString
@EqualsAndHashCode(of = "url")
public class ProxyTask implements Runnable {

    @Getter
    @Setter
    @NotNull
    private String url;

    public ProxyTask(String url) {
        this.url = url;
    }


    public void run() {
        try {
            //TODO 爬取代理目前直接用本机IP，后续考虑序列化代理。使用代理爬代理
            Page page = HttpClientUtil.getPage(url);
            if(page.getStatusCode() == HttpStatus.SC_OK
                    && !StringUtil.isBlank(page.getHtml())){
                Parser<Proxy> parser = ProxyParserFactory.getParser(ProxyPool.PROXY_SOURCE_URL.get(url));
                List<Proxy> proxyList = parser.parser(page);
                if(proxyList.size()> 0){
                    for(Proxy proxy : proxyList){
                        log.info("proxy:{}",proxy.toString());
                        ProxyPool.lock.readLock().lock();
                        boolean isExist = ProxyPool.proxys.contains(proxy);
                        ProxyPool.lock.readLock().unlock();
                        if(!isExist){
                            ProxyPool.lock.writeLock().lock();
                            ProxyPool.proxys.add(proxy);
                            ProxyPool.lock.writeLock().unlock();
                        }
                    }
                }


            }

        } catch (IOException e) {
           log.error("http request failure,url:{}",url);
        }
    }

}
