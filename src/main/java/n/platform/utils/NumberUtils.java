package n.platform.utils;

import java.util.Random;

/**
 * @Author: Near
 * @Date: 2018/9/10 15:17
 * @Description:���ּ��㹤����
 */

public class NumberUtils {
    private NumberUtils(){}

    private static final Random random = new Random();

    /**
     * ������+1
     * @param a ����
     * @param b ������
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
