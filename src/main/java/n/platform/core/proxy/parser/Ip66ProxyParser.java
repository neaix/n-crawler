package n.platform.core.proxy.parser;

import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import n.platform.core.Parser;
import n.platform.domain.Page;
import n.platform.domain.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Near
 * @Date: 2018/9/6 09:57
 * @Description: ip66 parser
 */
@Slf4j
public class Ip66ProxyParser implements Parser<Proxy> {

    public List<Proxy> parser(@NotNull Page page) {
        log.info("load html page,url:{}",page.getUrl());
        Document doc = Jsoup.parse(page.getHtml());
        Elements elements = doc.getElementsByTag("table");
        if(null != elements && elements.size() > 0){
            Elements element =  elements.eq(2).tagName("tbody");
            int size = element.size();
            if(null != element && size > 0){
                List<Proxy> proxies = new ArrayList<Proxy>(100);
              for(int i = 1; i < size; i++){
                   Element item = element.get(i);
                   Elements tds = item.getElementsByTag("td");
                   Proxy proxy = new Proxy(tds.eq(0).html(),Integer.valueOf(tds.eq(1).html()));
                   proxies.add(proxy);
              }
            }
        }
        return Collections.emptyList();
    }
}
