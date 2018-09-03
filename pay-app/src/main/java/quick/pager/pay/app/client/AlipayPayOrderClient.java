package quick.pager.pay.app.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 支付宝支付
 * @author siguiyang
 */
@FeignClient(value="${feign.client.node.alipay}")
public interface AlipayPayOrderClient {
}
