package quick.pager.pay.app.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.dispatch.Mapper;
import akka.dispatch.OnComplete;
import akka.pattern.Patterns;
import akka.util.Timeout;
import org.springframework.web.context.request.async.DeferredResult;
import quick.pager.pay.app.config.SpringContext;
import quick.pager.pay.app.function.DeferredResultFunction;
import quick.pager.pay.dto.DTO;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * actor 执行器
 * @author siguiyang
 */
public class ExecuteActor {

    public static <T> DeferredResult<T> execute(Class<? extends AbstractActor> requireType, String actorName, DTO dto,
                                                DeferredResultFunction<T> function) {

        ActorRef actorRef = SpringContext.getActorRef(requireType, actorName);
        Future<Object> future = Patterns.ask(actorRef, dto, new Timeout(Duration.apply(6000L, TimeUnit.SECONDS)));
        Future<T> f = future.map(new Mapper<Object, T>() {
            @Override
            public T apply(Object parameter) {
                if (null == parameter) {
                    return null;
                }
                return (T) parameter;
            }
        }, SpringContext.dispatcher());

        DeferredResult<T> result = new DeferredResult<>();
        f.onComplete(new OnComplete<T>() {
            @Override
            public void onComplete(Throwable failure, T success) throws Throwable {
                function.call(failure, success, result);
            }
        }, SpringContext.dispatcher());
        return result;
    }

}
