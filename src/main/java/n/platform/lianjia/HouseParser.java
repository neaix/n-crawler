package n.platform.lianjia;

import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import n.platform.constants.PlatformConstants;
import n.platform.core.Parser;
import n.platform.domain.House;
import n.platform.domain.Page;
import n.platform.utils.HttpClientUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Near
 * @Date: 2018/9/8 09:37
 * @Description:链家parser
 */

@Slf4j
public class HouseParser implements Parser<House> {
    @Override
    public List<House> parser(@NotNull Page page) {
        Objects.requireNonNull(page.getHtml(),"the html must not be null.");
        log.info("load html page,url:{}",page.getUrl());
        Document document = Jsoup.parse(page.getHtml());
        Elements elements = document.getElementsByClass("clear LOGCLICKDATA");
        if(null != elements && elements.size() > 0){
            List<House> houses = new ArrayList();
            for(Element element : elements){
                House house = new House();
                Elements img = element.getElementsByTag("img");
                house.setImgUrl(img.get(0).attr("data-original"));
                house.setTitle(element.select("div.title").select("a").html());
                String houseInfo = element.select("div.houseInfo").text().trim();
                if(StringUtils.isNotEmpty(houseInfo)){
                    String [] houseInfoArray = StringUtils.split(houseInfo,"|");
                    if(ArrayUtils.isNotEmpty(houseInfoArray)){
                        house.setName(houseInfoArray[0]);
                        house.setArea(houseInfoArray[1]);
                        house.setSize(houseInfoArray[2]);
                        house.setDescribe(houseInfo);
                    }
                }
                String positionInfo = element.select("div.positionInfo").text();
                if(StringUtils.isNotEmpty(positionInfo) && positionInfo.contains("年")){
                    house.setListingTime(positionInfo.substring(positionInfo.indexOf("年")-4,
                            positionInfo.indexOf("年")));
                }


             houses.add(house);
            }
            return houses;
        }
        return Collections.emptyList();
    }

    /**
     * 获取房源数量，以便与实现采集分页
     * @param page
     * @return
     */
    public int getHouseCount(@NotNull Page page){
        Objects.requireNonNull(page.getHtml(),"the html must not be null.");
        log.info("load html page,url:{}",page.getUrl());
        Document document = Jsoup.parse(page.getHtml());
        Elements elements = document.getElementsByClass("total fl");
        if(null == elements || elements.size() == 0){
            throw new RuntimeException("get house count failure");
        }
        Elements span = elements.get(0).getElementsByTag("span");
        return Integer.valueOf(span.html().trim());
    }


}
