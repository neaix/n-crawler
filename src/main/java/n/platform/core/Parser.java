package n.platform.core;

import com.sun.istack.internal.NotNull;
import n.platform.domain.Page;

import java.util.List;

/**
 * @Author: Near
 * @Date: 2018/9/4 17:46
 * @Description:web page parser
 */
public interface Parser<T> {
    /**
     * 页面解析
     * @param page Page对象
     * @see  Page
     * @return
     */
    List<T> parser(Page page);
}
