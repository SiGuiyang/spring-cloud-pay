package quick.pager.pay.request.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.request.Request;

@EqualsAndHashCode(callSuper = true)
@Data
public class MerchantRequest extends Request{
    private static final long serialVersionUID = 3175891006113977591L;

    @ApiModelProperty("商户名")
    private String merchantName;
    @ApiModelProperty("商户联系人")
    private String contractName;
    @ApiModelProperty("商户号")
    private String merchantNo;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("联系人手机号")
    private String mobile;
    @ApiModelProperty("银行名称")
    private String bankName;
    @ApiModelProperty("银行卡号")
    private String bankNum;
    @ApiModelProperty("身份证号")
    private String IdCard;
    @ApiModelProperty("身份证正面地址")
    private String IdCardFront;
    @ApiModelProperty("身份证反面地址")
    private String IdCardBehind;
    @ApiModelProperty("共钥")
    private String publicKey;
    @ApiModelProperty("私钥")
    private String privateKey;
    @ApiModelProperty("商户居住地址")
    private String address;
    @ApiModelProperty("营业执照,营业执照地址")
    private String license;
    @ApiModelProperty("费率,百分比")
    private String rate;
    @ApiModelProperty("扣量,百分比")
    private String deduction;
    @ApiModelProperty("是否是高质量的商户,0 : 否,1 : 是")
    private Integer highQuality;
}
