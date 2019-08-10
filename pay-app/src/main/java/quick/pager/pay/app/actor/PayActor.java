package quick.pager.pay.app.actor;

import akka.actor.AbstractActor;
import cn.hutool.core.util.URLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import quick.pager.pay.Constants;
import quick.pager.pay.app.dto.ChannelDTO;
import quick.pager.pay.app.dto.SubmitPayDTO;
import quick.pager.pay.app.mapper.OrderMapper;
import quick.pager.pay.app.service.PayService;
import quick.pager.pay.model.pay.Order;
import quick.pager.pay.model.pay.PayChannel;
import quick.pager.pay.response.PayResponse;

/**
 * 支付actor <br />
 * 生成支付中心订单号 <br />
 * 通过渠道找到最优的支付渠道 <br />
 * 再通过支付渠道调用不同的支付服务 <br />
 * 调用自己的支付服务时不牵扯数据传输加密与解密 <br />
 * 只有在支付服务中调用第三方支付服务才会进行对请求入参加密操作 <br />
 *
 * @author siguiyang
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class PayActor extends AbstractActor {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PayService payService;

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(SubmitPayDTO.class, submit -> {
            PayResponse resp;
            log.info("开始生成订单 merchantNo = {}, merchantOrderNo", submit.getMerchantNo(), submit.getMerchantOrderCode());

            Order order = new Order();

            order.setMerchantNo(submit.getMerchantNo());
            order.setMerchantOrderCode(submit.getMerchantOrderCode());
            order.setNotifyUrl(submit.getNotifyUrl());
            order.setPayBody(URLUtil.decode(submit.getBody()));
            order.setPayAmount(submit.getPayAmount());
            order.setPayClientId(submit.getClientIp());
            order.setOrderCode("");
            order.setPayStatus(Constants.PayStatus.created);

            // orderMapper.insertSelective(order);

            ChannelDTO channelDTO = ChannelDTO.builder().build();
            // 模拟选择最优的支付渠道入参


            // 得到支付渠道
            Object result = ActorExecutor.execute(context(), ChannelActor.class, channelDTO);
            // 没有得到支付渠道
            if (!(result instanceof PayChannel)) {
                // 添加错误信息
                sender().tell("", self());
                context().stop(self());
                return;
            }

            PayChannel payChannel = (PayChannel) result;

            resp = payService.pay(payChannel, null);
            sender().tell(resp, self());

        }).build();
    }
}
