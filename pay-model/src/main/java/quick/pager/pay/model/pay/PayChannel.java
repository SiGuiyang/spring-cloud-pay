package quick.pager.pay.model.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import quick.pager.pay.model.common.IModel;

import java.math.BigDecimal;

/**
 * 支付渠道配置表<br />
 * <code>t_pay_channel</code>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class PayChannel extends IModel {
    private static final long serialVersionUID = 6537601436311070912L;
    /**
     * 支付中心配置的Id
     */
    private Long payChannelCenterId;
    /**
     * 支付渠道名称
     */
    private String channelName;
    /**
     * 支付配置AppId
     */
    private String appId;
    /**
     * 微信公众号支付需要的openId
     */
    private String openId;
    /**
     * 支付商户mchId <br />
     * 这里的值是第三方提供的支付商户渠道 <br />
     * 例如：支付宝，微信提供的mchId <br />
     */
    private String mchId;
    /**
     * 支付密钥
     */
    private String secureKey;
    /**
     * 支付公钥
     */
    public String pubKey;
    /**
     * 是否是当前节点<br />
     * true: 当前，反之亦然
     */
    private Boolean currentNode;
    /**
     * 可用的支付渠道配置
     * true: 可用，反之亦然
     */
    private Boolean currentEnable;
    /**
     * 支付渠道费率<br />
     * 百分比
     */
    private BigDecimal rate;
    /**
     * 支付渠道扣量<br />
     * 百分比
     */
    private BigDecimal deduction;
}
