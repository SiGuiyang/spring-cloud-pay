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

    @ApiModelProperty("签名")
    private String sign;
    @ApiModelProperty("签名类型")
    private String signType;
    @ApiModelProperty("商户订单号")
    private String orderCode;
    @ApiModelProperty("随机字符串")
    private String nonceStr;
    @ApiModelProperty("支付时间戳")
    private String timestamp;
    @ApiModelProperty("回调通知地址")
    private String notifyUrl;
}
