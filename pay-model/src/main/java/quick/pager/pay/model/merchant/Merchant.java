package quick.pager.pay.model.merchant;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import quick.pager.pay.model.common.IModel;

import java.math.BigDecimal;

/**
 * 商户对象<br />
 * <code>t_merchant</code>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Merchant extends IModel {
    private static final long serialVersionUID = 2417878970503037229L;
    /**
     * 商户名
     */
    private String merchantName;
    /**
     * 商户联系人
     */
    private String contractName;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态<br />
     * 0：开户中<br />
     * 1：已销户<br />
     * 2：审核完成<br />
     * 3：审核失败
     */
    private Integer serverStatus;
    /**
     * 联系人手机号
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
     * 身份证正面地址
     */
    private String IdCardFront;
    /**
     * 身份证反面地址
     */
    private String IdCardBehind;
    /**
     * 共钥
     */
    private String publicKey;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 商户居住地址
     */
    private String address;
    /**
     * 营业执照<br />
     * 营业执照地址
     */
    private String license;
    /**
     * 费率<br />
     * 百分比
     */
    private BigDecimal rate;
    /**
     * 扣量<br />
     * 百分比
     */
    private BigDecimal deduction;
    /**
     * 是否是高质量的商户<br />
     * 0 : 否<br />
     * 1 : 是<br />
     */
    private Integer highQuality;


}
