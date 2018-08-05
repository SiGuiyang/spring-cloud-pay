package quick.pager.pay.app.service;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 微信支付client
 */
@FeignClient(value="${feign.client.node.weixin}")
public interface WeiXinPayOrderClient {
}
