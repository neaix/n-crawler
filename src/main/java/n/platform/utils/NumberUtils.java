package n.platform.utils;

import java.util.Random;

/**
 * @Author: Near
 * @Date: 2018/9/10 15:17
 * @Description:数字计算工具类
 */

public class NumberUtils {
    private NumberUtils(){}

    private static final Random random = new Random();

    /**
     * 除不尽+1
     * @param a 除数
     * @param b 被除数
     * @return
     */
    public static int div(int a,int b){
        if(a % b > 0){
            return a / b + 1;
        }else{
            return a / b;
        }
    }

    public static int random(int length){
        return random.nextInt(length);
    }
}
