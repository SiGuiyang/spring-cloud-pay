package quick.pager.pay.app.actor;

import akka.actor.AbstractActor;
import akka.actor.Actor;
import akka.actor.ActorContext;
import akka.actor.ActorRef;
import akka.dispatch.Mapper;
import akka.dispatch.OnComplete;
import akka.pattern.Patterns;
import akka.util.Timeout;
import org.springframework.web.context.request.async.DeferredResult;
import quick.pager.pay.app.config.SpringContext;
import quick.pager.pay.app.function.DeferredResultFunction;
import quick.pager.pay.dto.DTO;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * actor 执行器
 *
 * @author siguiyang
 */
public class ActorExecutor {


    /**
     * 执行 actor
     *
     * @param requireType actor class对象
     * @param actorName   actor 名称
     * @param dto         消息传递dto对象
     * @param function    函数式编程回调
     * @param <T>         actor 执行返回的数据对象
     * @param <R>         业务端返回数据模型
     */
    public static <T, R> DeferredResult<R> execute(Class<? extends AbstractActor> requireType, String actorName, DTO dto,
                                                   DeferredResultFunction<T, R> function) {

        StringBuilder builder = new StringBuilder();
        builder.append(actorName).append(UUID.randomUUID().toString().replace("-", "0").toUpperCase());

        ActorRef actorRef = SpringContext.getActorRef(requireType, builder.toString());
        Future<Object> future = Patterns.ask(actorRef, dto, new Timeout(Duration.create(30000L, TimeUnit.SECONDS)));
        Future<T> f = future.map(new Mapper<Object, T>() {
            @Override
            public T apply(Object parameter) {
                return (T) parameter;
            }
        }, SpringContext.dispatcher());

        DeferredResult<R> result = new DeferredResult<>();

        f.onComplete(new OnComplete<T>() {
            @Override
            public void onComplete(Throwable failure, T success) throws Throwable {
                function.call(failure, success, result);
            }
        }, SpringContext.dispatcher());
        return result;
    }

    /**
     * actor 内部执行通信
     *
     * @param context 自身actorContext
     * @param clazz   执行的Spring actor 的class对象
     * @param obj     传递的值
     */
    public static Object execute(ActorContext context, Class<? extends Actor> clazz, Object obj) throws Exception {
        ActorRef actorRef = context.actorOf(SpringContext.props(clazz));
        Future<Object> ask = Patterns.ask(actorRef, obj, new Timeout(Duration.create(10L, TimeUnit.SECONDS)));

        return Await.result(ask, Duration.apply(10L, TimeUnit.SECONDS));
    }

}
