package quick.pager.pay.alipay.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseDTO;

/**
 * 支付宝数据传输对象基类
 *
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseAlipayDTO extends BaseDTO {
    private static final long serialVersionUID = 653673030737706696L;

    // 平台订单号
    private String orderCode;

    // 支付主题
    private String body;
    /**
     * 签名
     */
    private String sign;

    /**
     * 支付时间戳
     */
    private String timestamp;

    /**
     * 随机字符串
     */
    private String nonceStr;

    /**
     * 签名类型
     */
    private String signType;

}
