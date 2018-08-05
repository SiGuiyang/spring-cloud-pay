package quick.pager.pay.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 银行对象<br />
 * <code>t_bank</code>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Bank extends IModel {
    private static final long serialVersionUID = 4937489458971679442L;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行编码
     */
    private String bankCode;
    /**
     * 银行卡号
     */
    private String bankNum;
    /**
     * 状态
     */
    private Integer serverStatus;

}
