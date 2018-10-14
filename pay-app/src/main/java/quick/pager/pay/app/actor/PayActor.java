package quick.pager.pay.app.actor;

import akka.actor.AbstractActor;
import cn.hutool.core.util.URLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import quick.pager.pay.Constants;
import quick.pager.pay.app.config.SpringContext;
import quick.pager.pay.app.function.DeferredResultFunction;
import quick.pager.pay.dto.pay.ChannelDTO;
import quick.pager.pay.dto.pay.SubmitPayDTO;
import quick.pager.pay.mapper.pay.OrderMapper;
import quick.pager.pay.model.pay.Order;
import quick.pager.pay.response.PayResponse;
import quick.pager.pay.response.Response;

/**
 * 支付actor
 *
 * @author siguiyang
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class PayActor extends AbstractActor {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(SubmitPayDTO.class, submit -> {
            PayResponse<String> resp = new PayResponse<>();
            log.info("开始生成订单 merchantNo = {},merchantOrderNo", submit.getMerchantNo(), submit.getMerchantOrderCode());

            Order order = new Order();

            order.setMerchantNo(submit.getMerchantNo());
            order.setMerchantOrderCode(submit.getMerchantOrderCode());
            order.setNotifyUrl(submit.getNotifyUrl());
            order.setPayBody(URLUtil.decode(submit.getBody()));
            order.setPayAmount(submit.getPayAmount());
            order.setPayClientId(submit.getClientIp());
            order.setOrderCode("");
            order.setPayStatus(Constants.PayStatus.created);

            orderMapper.insertSelective(order);
            ChannelDTO channelDTO = ChannelDTO.builder().build();

            // 获取返回的支付渠道
//            context().actorOf()actorOf

            sender().tell("",self());

        }).build();
    }
}
