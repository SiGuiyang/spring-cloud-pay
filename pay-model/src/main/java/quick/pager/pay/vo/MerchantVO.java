package quick.pager.pay.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class MerchantVO extends VO {
    private static final long serialVersionUID = 5618806796368601333L;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行卡号
     */
    private String bankNum;
    /**
     * 身份证号
     */
    private String IdCard;
    /**
     * 状态
     */
    private Integer serverStatus;
}
