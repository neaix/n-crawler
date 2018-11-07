package n.platform.core.proxy.task;/**
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

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Near
 * @Date: 2018/10/30 14:23
 * @Description:
 */

public class TestLock {
    public static void main(String[] args) {
        //Ticket ticket = new Ticket();
       // new Thread(ticket, "1号窗口").start();
       // new Thread(ticket, "2号窗口").start();
        //new Thread(ticket, "3号窗口").start();
        System.out.println(1<<30);
    }
}

class Ticket implements Runnable{
    private int tick = 10000;
   // private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        while(true){

            synchronized (this){
                if(tick > 0){
                   // try {
                      //  Thread.sleep(300);
                    //} catch (InterruptedException e) {
                    //}
                    System.out.println(Thread.currentThread().getName() + " 完成售票，余票为：" + --tick);
                }
            }
        }
    }
}
