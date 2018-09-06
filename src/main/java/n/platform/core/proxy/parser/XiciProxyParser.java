package n.platform.core.proxy.parser;

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
 * @Date: 2018/9/5 15:21
 * @Description: 西刺http代理解析
 */

@Slf4j
public class XiciProxyParser implements Parser<Proxy> {

    public List<Proxy> parser(Page page) {
        log.info("load html page,url:{}",page.getUrl());
        Document doc = Jsoup.parse(page.getHtml());
        Elements elements = doc.getElementsByClass("odd");
        int sise = elements.size();
        if(null != elements && sise > 0){
            List<Proxy> list = new ArrayList<Proxy>();
            for(int i = 0; i < sise; i++){
                Element ele = elements.get(i);
                Elements tds =  ele.getElementsByTag("td");
                Proxy proxy = new Proxy(tds.eq(1).html(),Integer.valueOf(tds.eq(2).html()));
                list.add(proxy);

            }
            return list;
        }
        return Collections.emptyList();
    }
}
