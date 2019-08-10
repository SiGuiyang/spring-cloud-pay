package quick.pager.pay.app.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import quick.pager.pay.app.properties.PayProperties;
import quick.pager.pay.app.service.message.TradeMessage;
import quick.pager.pay.model.pay.PayChannel;
import quick.pager.pay.response.PayResponse;


/**
 * 支付服务
 */
@Service
@Slf4j
public class PayService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private PayProperties payProperties;
    // 服务地址缓存
    private static final Map<String, ServiceInstance> SERVICE_INSTANCE_MAP = new ConcurrentHashMap<>();

    /**
     * 根据服务名称获取真实的访问地址
     *
     * @param serviceId 服务名称
     * @return http://127.0.0.1:8080/
     */
    private String getServiceUrl(String serviceId) {
        ServiceInstance serviceInstance;
        if (!SERVICE_INSTANCE_MAP.containsKey(serviceId)) {
            serviceInstance = loadBalancerClient.choose(serviceId);
            SERVICE_INSTANCE_MAP.put(serviceId, serviceInstance);
        } else {
            serviceInstance = SERVICE_INSTANCE_MAP.get(serviceId);
        }
        return String.format("%s://%s:%s", "http", serviceInstance.getHost(), serviceInstance.getPort());
    }

    /**
     * 查看方法是否包含版本控制信息
     *
     * @param payMethod 请求方法
     */
    private String getVersionMethods(String payMethod, String version) {
        // 如果包含版本控制
        if (payMethod.contains("{version}")) {
            return payMethod.replace("{version}", version);
        }
        return payMethod;
    }

    /**
     * 支付服务
     *
     * @param channel 支付渠道信息
     * @param message 支付主体
     */
    public PayResponse pay(PayChannel channel, TradeMessage message) {
        String requestUrl = getServiceUrl(channel.getServiceId());
        if (CollectionUtils.isEmpty(payProperties.getMethods())) {
            log.error("支付方法没有配置");
            return null;
        }
        String payMethod = payProperties.getMethods().get("pay");
        if (StringUtils.isEmpty(payMethod)) {
            log.error("支付方法没有配置");
        }
        requestUrl = requestUrl + getVersionMethods(payMethod, channel.getVersion());
        return doPay(requestUrl, channel, message);
    }


    /**
     * 提交支付方法，调用支付接口
     *
     * @param url        真实访问地址
     * @param payChannel 支付渠道
     * @param message    支付主体
     */
    private PayResponse doPay(String url, PayChannel payChannel, TradeMessage message) {

        PayResponse response = new PayResponse();
        String result = restTemplate.postForObject(url, message, String.class);
        log.info("支付返回结果 result = {}", result);
        return response;
    }
}
