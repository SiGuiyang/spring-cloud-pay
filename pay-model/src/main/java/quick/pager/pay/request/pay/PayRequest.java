package quick.pager.pay.request.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付请求对象
 */
@Data
@ApiModel(description = "支付请求对象")
public class PayRequest implements Serializable {
    private static final long serialVersionUID = 59698744732748652L;
    @ApiModelProperty(value = "商户号", name = "merchantNo")
    private String merchantNo;
    @ApiModelProperty(value = "商户订单号", name = "merchantOrderNo")
    private String merchantOrderCode;
    @ApiModelProperty(value = "回调地址", name = "notifyUrl")
    private String notifyUrl;
    @ApiModelProperty(value = "客户端请求Ip地址", name = "clientIp")
    private String clientIp;
    @ApiModelProperty(value = "支付金额", name = "payAmount")
    private BigDecimal payAmount;
    @ApiModelProperty(value = "银行卡号", name = "bankCardNum")
    private String bankCardNum;
    @ApiModelProperty(value = "银行编号", name = "bankCode")
    private String bankCode;
    @ApiModelProperty(value = "支付类型", name = "payType")
    private String payType;
    @ApiModelProperty(value = "支付主题", name = "body")
    private String body;
    @ApiModelProperty(value = "支付时时间戳", name = "timestamp")
    private String timestamp;
    @ApiModelProperty(value = "签名类型", name = "signType")
    private String signType;
    @ApiModelProperty(value = "签名", name = "sign")
    private String sign;
}
