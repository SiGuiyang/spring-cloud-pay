package quick.pager.pay.request.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信支付请求主体类
 *
 * @author siguiyang
 */
@Data
@ApiModel(description = "微信支付请求主体类")
public class WeChatSubmitPayRequest implements Serializable {
    private static final long serialVersionUID = -8866298241183396595L;

    @ApiModelProperty(value = "订单号，平台的订单号")
    private String orderCode;
    @ApiModelProperty("签名")
    private String sign;
    @ApiModelProperty("签名类型")
    private String signType;
    @ApiModelProperty("回调通知地址")
    private String notifyUrl;
    @ApiModelProperty("支付时间戳")
    private String timestamp;
    /**
     * snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息 ）
     */
    @ApiModelProperty("应用授权作用域")
    private String scope = "snsapi_base";
    @ApiModelProperty("订单金额")
    private BigDecimal amount;
    @ApiModelProperty("随机字符串")
    private String nonceStr;


}
