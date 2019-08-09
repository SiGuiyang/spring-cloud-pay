package quick.pager.pay.channel.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.pay.model.pay.PayChannel;
import quick.pager.pay.request.pay.ChannelRequest;
import quick.pager.pay.response.Response;

/**
 * 渠道控制器<br />
 *
 * @author siguiyang
 */
@RestController
public class ChannelController {


    /**
     * 支付渠道选择
     * @param request
     * @return
     */
    @PostMapping("/channel/query")
    public Response<PayChannel> queryChannel(@RequestBody ChannelRequest request) {
        System.out.println("支付渠道选择");
        PayChannel payChannel = new PayChannel();
        payChannel.setServiceId("pay-weixin");
        Response<PayChannel> response = new Response<>();
        response.setData(payChannel);
        return response;
    }


}
