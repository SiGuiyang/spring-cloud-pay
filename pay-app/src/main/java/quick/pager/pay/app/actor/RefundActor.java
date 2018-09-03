package quick.pager.pay.app.actor;

import akka.actor.AbstractActor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 退款
 * @author siguiyang
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class RefundActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return null;
    }
}
