package n.platform.domain;

import com.sun.istack.internal.NotNull;
import lombok.*;

/**
 * @Author: Near
 * @Date: 2018/9/5 10:07
 * @Description: html page
 */

@ToString(of = {"url","statusCode"})
@EqualsAndHashCode(of = {"url","statusCode"})
@RequiredArgsConstructor
public class Page {

    @Getter
    @Setter
    @NotNull
    private String url;

    @Getter
    @Setter
    @NotNull
    private String html;

    @Getter
    @Setter
    private int statusCode;


    public Page(String url, String html) {
        this.url = url;
        this.html = html;
    }

    public Page(String url, String html, int statusCode) {
        this.url = url;
        this.html = html;
        this.statusCode = statusCode;
    }
}
