package n.platform.core.monitor;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;
/**
 * @Author: Near
 * @Date: 2018/9/11 15:19
 * @Description: 线程池监控任务
 */
@Slf4j
@RequiredArgsConstructor
public class MonitorThreadPoolTask implements Runnable{

    public static volatile boolean isStop = false;

    @Getter
    @Setter
    private String name;

    @Setter
    private ThreadPoolExecutor executor;



    public MonitorThreadPoolTask(@NotNull String name, @NotNull ThreadPoolExecutor executor) {
        this.name = name;
        this.executor = executor;
    }


    @Override
    public void run() {
        while(!isStop){
            log.info("{}[monitor] [{}/{}] Active: {}, Completed: {}, queueSize: {}," +
                                    " Task: {}, isShutdown: {}, isTerminated: {}",
                            name,
                            this.executor.getPoolSize(),
                            this.executor.getCorePoolSize(),
                            this.executor.getActiveCount(),
                            this.executor.getCompletedTaskCount(),
                            this.executor.getQueue().size(),
                            this.executor.getTaskCount(),
                            this.executor.isShutdown(),
                            this.executor.isTerminated());


        }
    }


    public void stop(){
        isStop = true;
    }
}
