package quick.pager.pay.dto.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderDTO extends BaseDTO{

    @ApiModelProperty("通知状态,0:未下发 1:已下发 2:已通知")
    private Integer notificationStatus;
    @ApiModelProperty("支付状态,0：已创建 1：已取消 2：成功 3：失败")
    private Integer payStatus;
    @ApiModelProperty("订单号")
    private String orderCode;
    @ApiModelProperty("商户订单号")
    private String merchantOrderCode;
    @ApiModelProperty("交易流水号")
    private String tradeCode;
    @ApiModelProperty("商户号")
    private String merchantNo;
    @ApiModelProperty("支付方式")
    private String payType;

}
