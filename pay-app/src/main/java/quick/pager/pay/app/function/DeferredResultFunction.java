package quick.pager.pay.app.function;

import org.springframework.web.context.request.async.DeferredResult;

/**
 * 线程回调
 *
 * @param <T>
 * @author siguiyang
 */
@FunctionalInterface
public interface DeferredResultFunction<T> {

    void call(Throwable failure, T response, DeferredResult result);
}
