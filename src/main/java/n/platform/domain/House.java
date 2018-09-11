package n.platform.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: Near
 * @Date: 2018/9/4 17:46
 * @Description:house domain
 */
@ToString
@EqualsAndHashCode
public class House {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String area;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String describe;

    @Getter
    @Setter
    private String size;

    @Getter
    @Setter
    private String houseModel;

    @Getter
    @Setter
    private String listingTime;

    @Getter
    @Setter
    private String imgUrl;

}
