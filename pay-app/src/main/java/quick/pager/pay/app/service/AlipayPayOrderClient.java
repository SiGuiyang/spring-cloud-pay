package quick.pager.pay.app.service;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 支付宝支付
 */
@FeignClient(value="${feign.client.node.alipay}")
public interface AlipayPayOrderClient {
}
