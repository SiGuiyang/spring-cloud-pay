package quick.pager.pay.dto.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BasePayDTO;

/**
 * 微信退款
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatRefundDTO extends BasePayDTO {
    private static final long serialVersionUID = -8817311074434370725L;
}
