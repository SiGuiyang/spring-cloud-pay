package quick.pager.pay.weixin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信最终支付配置
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatPayConfigDTO extends WeChatBaseResponseDTO {
    private static final long serialVersionUID = -4191772402708096450L;

    // 预支付交易会话标识
    private String prepay_id;
    // 交易类型
    private String trade_type;
    // 二维码链接
    private String code_url;
    // 签名类型
    private String sign_Type;

    private String timeStamp;

}
