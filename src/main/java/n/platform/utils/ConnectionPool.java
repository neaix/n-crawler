package n.platform.utils;
/**
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

import lombok.extern.slf4j.Slf4j;
import n.platform.constants.Config;
import org.apache.http.annotation.ThreadSafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Semaphore;

/**
 * @Author: Near
 * @Date: 2018/11/14 13:42
 * @Description: jdbc连接池
 */

@ThreadSafe
@Slf4j
public class ConnectionPool {

    private final String driver;

    private final String url;

    private final int poolSize;

    private final String username;

    private final String password;

    private final Semaphore semaphore;

    private static final int DEFAULT_MAX_SIZE = 20;


    public ConnectionPool(){
        this(DEFAULT_MAX_SIZE);
    }

   public ConnectionPool(String driver, String url, int poolSize, String username, String password){
       this.driver = driver;
       this.url = url;
       this.username = username;
       this.password = password;
       this.poolSize = poolSize;
       semaphore = new Semaphore(poolSize);
       //初始化加载驱动
       initDriver();
   }
    public ConnectionPool(int poolSize){
        this(Config.getString("jdbc.driver"),
            Config.getString("jdbc.url"),
            poolSize,
            Config.getString("jdbc.username"),
            Config.getString("jdbc.password")
        );
    }

    private void initDriver(){
        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            log.error("load database driver failure.",e);
            throw new RuntimeException(e);
        }
    }

    private Connection loadConnection(){
        // 避免锁住整个方法或者类
        synchronized (this.driver){
            try {
               return DriverManager.getConnection(url,username,password);
            } catch (SQLException e) {
               log.error("load jdbc connection failure.",e);
               throw new RuntimeException(e);
            }
        }

    }

    /**
     * 获取一个数据库连接
     * @return
     */
    public Connection getConnection(){
        //获取许可，直到池子中有可用的连接，否则阻塞
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return loadConnection();
    }

    /**
     * 销毁Connection对象
     * @param conn
     */
    public void destroy(Connection conn){
        synchronized (conn){
            if(null != conn){
                try {
                    conn.close();
                    semaphore.release();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
