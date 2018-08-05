package quick.pager.pay.request.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.request.Request;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChannelRequest extends Request{
    private static final long serialVersionUID = -3406819371988928125L;
    @ApiModelProperty(value = "支付中心配置的Id")
    private Long payChannelCenterId;
    @ApiModelProperty(value = "支付渠道名称")
    private String channelName;
    @ApiModelProperty("支付配置AppId")
    private String appId;
    @ApiModelProperty(value = "微信公众号支付需要的openId")
    private String openId;
    @ApiModelProperty(value = "支付商户mchId，支付宝，微信提供的mchId")
    private String mchId;
    @ApiModelProperty(value = "支付密钥")
    private String secureKey;
    @ApiModelProperty("是否是当前节点,true: 当前，反之亦然")
    private Boolean currentNode;
    @ApiModelProperty("可用的支付渠道配置,true: 可用，反之亦然")
    private Boolean currentEnable;
    @ApiModelProperty("支付渠道费率,百分比")
    private String rate;
    @ApiModelProperty("支付渠道扣量,百分比")
    private String deduction;
    @ApiModelProperty("支付渠道名称")
    private String channelCenterName;
    @ApiModelProperty("支付方式=>WECHAT_H5: 微信H5支付," +
            "WECHAT_APP: 微信APP支付," +
            "WECHAT_PUBLIC_NUMBER: 微信公众号支付," +
            "WECHAT_SCAN_QR: 微信扫码支付," +
            "ALIPAY_H5: 支付宝H5（Wap）支付," +
            "AlIPAY_APP: 支付宝APP支付," +
            "ALIPAY_WEB: 支付宝网站支付," +
            "ALIPAY_SCAN_QR: 支付宝扫码支付")
    private String payType;
    @ApiModelProperty("创建人")
    private String operationUserName;
}
