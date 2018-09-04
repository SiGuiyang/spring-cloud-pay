package quick.pager.pay.app.actor;

import akka.actor.AbstractActor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import quick.pager.pay.dto.pay.PayDTO;
import quick.pager.pay.response.PayResponse;

/**
 * 支付actor
 *
 * @author siguiyang
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class PayActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(PayDTO.class, payDTO -> {
            PayResponse<String> response = new PayResponse<>();
            response.setCode(200);
            response.setMsg(payDTO.getMerchantNo() + "=" + payDTO.getMerchantOrderNo() + "=" + payDTO.getPayAmount());
            sender().tell(response, self());
            context().stop(self());

        }).build();
    }
}
