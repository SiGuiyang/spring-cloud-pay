package quick.pager.pay.app.config;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import quick.pager.pay.app.producer.SpringActorProducer;
import scala.concurrent.ExecutionContextExecutor;

/**
 * spring 上下文通用处理工具类
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> requireType) {
        return applicationContext.getBean(requireType);
    }

    public static <T> T getBean(String beanName, Class<T> requireType) {
        return applicationContext.getBean(beanName, requireType);
    }

    /**
     * 从spring 上下文获取ActorSystem
     */
    public static ActorSystem getActorSystem() {
        return getBean(ActorSystem.class);
    }


    public static ExecutionContextExecutor dispatcher() {
        return getActorSystem().dispatcher();
    }

    public static Props props(Class<? extends Actor> requireType) {
        return Props.create(SpringActorProducer.class, applicationContext, requireType);
    }

    /**
     * 获取执行的actor引用
     *
     * @param requireType 类型
     * @param actorName   actor名称
     */
    public static ActorRef getActorRef(Class<? extends Actor> requireType, String actorName) {
        return getActorSystem().actorOf(props(requireType), actorName);
    }


}
