package quick.pager.pay.request.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信退款
 *
 * @author siguiyang
 */
@Data
@ApiModel
public class WeiXinRefundRequest implements Serializable {
    private static final long serialVersionUID = 5306297450820356010L;
    @ApiModelProperty("公众账号ID")
    private String appId;
    @ApiModelProperty("商户号")
    private String mchId;
    @ApiModelProperty("签名")
    private String sign;
    @ApiModelProperty("签名类型")
    private String signType;
    @ApiModelProperty("商户订单号")
    private String orderCode;
}
