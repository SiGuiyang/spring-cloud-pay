package quick.pager.pay.app.function;

import org.springframework.web.context.request.async.DeferredResult;

/**
 * 线程回调
 *
 * @param <T>
 * @author siguiyang
 */
@FunctionalInterface
public interface DeferredResultFunction<T, R> {
    /**
     * 回调函数
     *
     * @param failure  异常信息
     * @param response 支付返回数据对象
     * @param result   业务端响应数据对象
     */
    void call(Throwable failure, T response, DeferredResult<R> result);
}
