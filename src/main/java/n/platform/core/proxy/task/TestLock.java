package n.platform.core.proxy.task;/**
 * ���������������޳�û������������
 * ��������������������
 * ���������ߩ��������ߩ�
 * ����������������������
 * ����������������������
 * ���������ש������ס���
 * ����������������������
 * �������������ߡ�������
 * ����������������������
 * ����������������������Code is far away from bug with the animal protecting
 * ������������������    ���ޱ���,������bug
 * ������������������
 * ��������������������������
 * �������������������������ǩ�
 * ����������������������������
 * �������������������ש�����
 * �������������ϩϡ����ϩ�
 * �������������ߩ������ߩ�
 * <p>
 * �������������о������թ�����������
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
       // new Thread(ticket, "1�Ŵ���").start();
       // new Thread(ticket, "2�Ŵ���").start();
        //new Thread(ticket, "3�Ŵ���").start();
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
                    System.out.println(Thread.currentThread().getName() + " �����Ʊ����ƱΪ��" + --tick);
                }
            }
        }
    }
}
