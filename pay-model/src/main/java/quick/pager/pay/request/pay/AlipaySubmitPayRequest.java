package quick.pager.pay.request.pay;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝支付提交请求类
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AlipaySubmitPayRequest extends Request{
    private static final long serialVersionUID = -2787475863608619662L;

    @ApiModelProperty("随机字符串")
    private String nonceStr;
    @ApiModelProperty(value = "订单号，平台的订单号")
    private String orderCode;
    @ApiModelProperty("签名")
    private String sign;
    @ApiModelProperty("支付时间戳")
    private String timestamp;
    @ApiModelProperty("签名类型")
    private String signType;

}
