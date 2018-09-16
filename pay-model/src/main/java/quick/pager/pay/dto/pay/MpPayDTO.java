package quick.pager.pay.dto.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BasePayDTO;

/**
 * 公众号支付参数
 *
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MpPayDTO extends BasePayDTO {
    private static final long serialVersionUID = 2863343284201321086L;

    private String openId;

    private String signType;

    private String clientIp;


}
