package quick.pager.pay.app.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 微信支付client
 *
 * @author siguiyang
 */
@FeignClient(value = "pay-weixin")
public interface WeiXinPayOrderClient {
}
