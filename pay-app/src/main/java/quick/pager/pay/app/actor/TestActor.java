package quick.pager.pay.app.actor;

import akka.actor.AbstractActor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import quick.pager.pay.dto.actor.TestDTO;
import quick.pager.pay.response.PayResponse;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class TestActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(TestDTO.class, testDto -> {
            log.info(" 进入test Actor");

            PayResponse resp = new PayResponse();
            resp.setMerchantNo(testDto.getMerchantNo()+"testActor");
            sender().tell(resp, self());
        }).build();
    }
}
