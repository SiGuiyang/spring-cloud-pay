package quick.pager.pay.alipay.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝验证签名
 *
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VerifySignDTO extends BaseAlipayDTO {
    private static final long serialVersionUID = 3397336350681481933L;
}
