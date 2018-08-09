package quick.pager.pay.dto.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import quick.pager.pay.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class ChannelCenterDto extends BaseDto {
    private static final long serialVersionUID = 5248393104117893500L;

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
    @ApiModelProperty(value ="服务状态")
    private Integer serverStatus;

    private String token;
}
