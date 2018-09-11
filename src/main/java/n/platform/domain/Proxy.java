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

    //���һ������ɹ���ʱ
    @Getter
    @Setter
    private transient long lastSuccessfulTime;

    //����ɹ��ܺ�ʱ
    @Getter
    @Setter
    private transient long successfulTotalTime;

    //�ɹ�����
    @Getter
    @Setter
    private transient int successfulTimes;

    //�Ƿ����
    @Getter
    @Setter
    private transient boolean available;

    public Proxy(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
