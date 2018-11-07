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
     * ҳ�����
     * @param page Page����
     * @see  Page
     * @return
     */
    List<T> parser(Page page);
}
