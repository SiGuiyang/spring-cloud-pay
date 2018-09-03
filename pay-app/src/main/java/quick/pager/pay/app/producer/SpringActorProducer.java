package quick.pager.pay.app.producer;

import akka.actor.AbstractActor;
import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * Spring Actor 执行器
 *
 * @author siguiyang
 */
public class SpringActorProducer implements IndirectActorProducer {

    private ApplicationContext applicationContext;

    private Class<? extends AbstractActor> requireType;

    @Override
    public Actor produce() {
        return applicationContext.getBean(requireType);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return this.requireType;
    }
}
