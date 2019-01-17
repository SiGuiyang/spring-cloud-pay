package quick.pager.pay.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quick.pager.pay.model.pay.PayChannel;
import quick.pager.pay.request.pay.ChannelRequest;
import quick.pager.pay.response.Response;

/**
 * 支付渠道使用feign 实现
 *
 * @author siguiyang
 */
@FeignClient(value = "pay-channel", fallback = ChannelClientFallback.class)
public interface ChannelClient {

    @RequestMapping(value = "/channel/query", method = RequestMethod.POST)
    Response<PayChannel> queryChannel(@RequestBody ChannelRequest request);
}
