package n.platform.core.proxy;

import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import n.platform.core.Parser;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Near
 * @Date: 2018/9/6 13:33
 * @Description: 解析器工厂
 */

@Slf4j
public class ProxyParserFactory {
    private ProxyParserFactory(){}
    private static final Map<String,Parser> map = new HashMap<String, Parser>();

    /**
     * 根据class对象获取解析器实力
     * @param clz
     * @return
     * @see Parser
     */
    public static Parser getParser(@NotNull Class clz){
        String name = clz.getSimpleName();
        if(map.containsKey(name)){
            return map.get(name);
        }else{
            try {
                Parser parser = (Parser) clz.newInstance();
                map.put(name,parser);
                return parser;
            } catch (InstantiationException e) {
                log.error(e.getMessage());
            } catch (IllegalAccessException e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }
}
