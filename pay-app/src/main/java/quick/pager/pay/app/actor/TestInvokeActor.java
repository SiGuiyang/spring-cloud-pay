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
public class TestInvokeActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(TestDTO.class, testDto -> {
            testDto.setVersion("33333333333");

            PayResponse response = new PayResponse();
            response.setMerchantNo(testDto.getMerchantNo()+"11!11111dfsfsdfsfdsf");
            sender().tell(response,self());
        }).build();
    }
}
