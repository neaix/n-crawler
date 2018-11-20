package n.platform.utils;/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 */

import com.sun.istack.internal.NotNull;
import n.platform.domain.House;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Near
 * @Date: 2018/11/14 14:57
 * @Description: 反射工具类
 */

public class ReflectUtils {

    private ReflectUtils(){
    }

    public static String [] getAllFields(Class clz){
        Field [] fields =  clz.getDeclaredFields();
        String [] fieldNames = new String[fields.length];
        for(int i = 0; i < fields.length; i++){
            Field  field = fields[i];
            field.setAccessible(true);
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    public static Map<String,Object> BeanToMap(@NotNull Object obj){

        Map<String,Object> map = new HashMap<String, Object>(100);
        Class clz = obj.getClass();
        Field [] fields =  clz.getDeclaredFields();
        try {
        for(Field field : fields){
            field.setAccessible(true);
            map.put(field.getName(),field.get(obj));

        }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;

    }
}
