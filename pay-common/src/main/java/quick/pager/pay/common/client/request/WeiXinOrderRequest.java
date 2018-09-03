package quick.pager.pay.common.client.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 统一下单
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("xml")
@Data
public class WeiXinOrderRequest extends WeiXinBaseRequest {

    private static final long serialVersionUID = -5957460267959606864L;
    /**
     * 设备号
     */
    @XStreamAlias("device_info")
    private String deviceInfo;
    /**
     * 商品描述
     */
    @XStreamAlias("body")
    private String body;
    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    /**
     * 总金额
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;
    /**
     * 终端IP
     */
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;
    /**
     * 通知地址
     */
    @XStreamAlias("notify_url")
    private String notifyUrl;
    /**
     * 交易类型
     */
    @XStreamAlias("trade_type")
    private String tradeType;
    /**
     * 场景信息
     */
    @XStreamAlias("scene_info")
    private String sceneInfo;

    @XStreamAlias("openid")
    private String openId;
}
