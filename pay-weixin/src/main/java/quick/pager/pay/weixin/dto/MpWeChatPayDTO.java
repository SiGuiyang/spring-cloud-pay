package quick.pager.pay.weixin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseWeChatPayDTO;

/**
 * 公众号支付参数
 *
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MpWeChatPayDTO extends BaseWeChatPayDTO {
    private static final long serialVersionUID = 2863343284201321086L;

    private String openId;

    private String signType;

    private String clientIp;


}
