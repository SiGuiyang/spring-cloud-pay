package quick.pager.pay.common.client.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝web端支付
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayWebRequest  extends BaseAlipayRequest {

    private static final long serialVersionUID = 1995027944804890058L;
    private String terminalType;
    private String terminalInfo;
    private String prodCode;
    private String notifyUrl;
    private String returnUrl;

}
