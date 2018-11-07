package n.platform.utils;

import com.sun.istack.internal.NotNull;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @Author: Near
 * @Date: 2018/9/12 11:21
 * @Description: 序列化工具类
 */

@Slf4j
public class SerializableUtils {

    private SerializableUtils(){
    }

    /**
     * 序列化
     * @param o
     * @param path
     */
    public static void serializable(@NotNull Object o, @NotNull String path){
        try {
            @Cleanup OutputStream os = new FileOutputStream(path);
            @Cleanup ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(o);

        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 反序列化
     * @param path
     * @return
     */
    public static Object deSerializable(@NotNull String path){
        try {
            @Cleanup InputStream is = new FileInputStream(path);
            @Cleanup ObjectInputStream ois = new ObjectInputStream(is);
            Object o = ois.readObject();
            return o;
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        int a = 6;
        a = a >> 1;
        System.out.println(a);
    }
}
