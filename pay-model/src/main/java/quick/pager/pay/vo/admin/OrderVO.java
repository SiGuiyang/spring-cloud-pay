package quick.pager.pay.vo.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import quick.pager.pay.vo.VO;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class OrderVO extends VO {
    private static final long serialVersionUID = -6715359479377589359L;


    /**
     * 通知状态<br />
     * 0:未下发 1:已下发 2:已通知<br />
     */
    private Integer notificationStatus;
    /**
     * 支付状态<br />
     * 0：已创建 1：已取消 2：成功 3：失败<br />
     */
    private Integer payStatus;
    /**
     * 订单号
     */
    private String orderCode;
    /**
     * 商户订单号
     */
    private String merchantOrderCode;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 支付方式
     */
    private String payType;

}
