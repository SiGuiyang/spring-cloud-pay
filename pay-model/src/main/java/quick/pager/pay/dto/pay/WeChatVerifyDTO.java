package quick.pager.pay.dto.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseDTO;


/**
 * 微信平台验签dto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatVerifyDTO extends BaseDTO {
    private static final long serialVersionUID = 7295877036694394037L;
    /**
     * 公众账号ID
     */
    private String appId;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 回调通知地址
     */
    private String notifyUrl;
    /**
     * 订单号
     */
    private String orderCode;
    /**
     * 应用授权作用域
     */
    private String scope;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 当前支付时间戳
     */
    private String timestamp;
    /**
     * 签名
     */
    private String sign;


}
