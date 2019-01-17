package quick.pager.pay.app.actor;

import akka.actor.AbstractActor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import quick.pager.pay.app.client.ChannelClient;
import quick.pager.pay.app.dto.ChannelDTO;
import quick.pager.pay.model.pay.PayChannel;
import quick.pager.pay.request.pay.ChannelRequest;
import quick.pager.pay.response.Response;

/**
 * channel 支付渠道 actor
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class ChannelActor extends AbstractActor {

    @Autowired
    private ChannelClient channelClient;

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(ChannelDTO.class, channelDTO -> {
            log.info("进入选择渠道。。。");
            ChannelRequest request = new ChannelRequest();
            Response<PayChannel> response = channelClient.queryChannel(request);
            sender().tell(response.getData(), self());

        }).build();
    }
}
