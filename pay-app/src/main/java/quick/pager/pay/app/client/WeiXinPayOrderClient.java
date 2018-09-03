package quick.pager.pay.app.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 微信支付client
 * @author siguiyang
 */
@FeignClient(value="${feign.client.node.weixin}")
public interface WeiXinPayOrderClient {
}
