package quick.pager.pay.weixin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseWeChatPayDTO;

/**
 * 微信退款
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatRefundDTOWeChat extends BaseWeChatPayDTO {
    private static final long serialVersionUID = -8817311074434370725L;
}
