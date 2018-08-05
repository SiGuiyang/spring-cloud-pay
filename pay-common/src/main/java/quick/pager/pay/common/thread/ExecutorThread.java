package quick.pager.pay.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 *
 * @author siguiyang
 */
public class ExecutorThread {

    /**
     * 初始化设置线程池
     */
    private static ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 3,
            Integer.MAX_VALUE, 60000L, TimeUnit.MILLISECONDS, new SynchronousQueue<>());


    /**
     * 执行线程
     */
    public static void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    /**
     * 销毁线程
     */
    public static void destroy() {
        executorService.shutdown();
    }


}
