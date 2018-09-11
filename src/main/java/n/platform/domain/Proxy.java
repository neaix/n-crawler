package n.platform.domain;

import com.sun.istack.internal.NotNull;
import lombok.*;

import java.io.Serializable;


/**
 * @Author: Near
 * @Date: 2018/9/5 10:26
 * @Description: proxy domain
 */
@ToString(of = {"ip","port"})
@EqualsAndHashCode(of = {"ip","port"})
public class Proxy implements Serializable {

    @Getter
    @Setter
    @NotNull
    private String ip;

    @Getter
    @Setter
    @NotNull
    private int port;

    //最后一次请求成功耗时
    @Getter
    @Setter
    private transient long lastSuccessfulTime;

    //请求成功总耗时
    @Getter
    @Setter
    private transient long successfulTotalTime;

    //成功次数
    @Getter
    @Setter
    private transient int successfulTimes;

    //是否可用
    @Getter
    @Setter
    private transient boolean available;

    public Proxy(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
