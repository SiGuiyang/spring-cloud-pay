package quick.pager.pay.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 支付中心渠道配置表<br />
 * <code>t_pay_channel_center</code>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class PayChannelCenter extends IModel {
    private static final long serialVersionUID = 6537601436311070912L;
    /**
     * 支付渠道名称
     */
    private String channelCenterName;
    /**
     * 支付方式<br />
     * WECHAT_H5: 微信H5支付<br />
     * WECHAT_APP: 微信APP支付<br />
     * WECHAT_PUBLIC_NUMBER: 微信公众号支付<br />
     * WECHAT_SCAN_QR: 微信扫码支付<br />
     * ALIPAY_H5: 支付宝H5（Wap）支付<br />
     * AlIPAY_APP: 支付宝APP支付<br />
     * ALIPAY_WEB: 支付宝网站支付<br />
     * ALIPAY_SCAN_QR: 支付宝扫码支付<br />
     */
    private String payType;
    /**
     * 支付中心配置状态<br />
     * 0: 禁用<br />
     * 1: 启用<br />
     */
    private Integer serverStatus;
    /**
     * 创建人<br />    
     */
    private String createUserName;
    /**
     * 修改人<br />
     */
    private String updateUserName;
}
