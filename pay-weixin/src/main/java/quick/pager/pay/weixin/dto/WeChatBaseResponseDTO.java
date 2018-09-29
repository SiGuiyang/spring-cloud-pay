package quick.pager.pay.weixin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.DTO;

/**
 * 调用微信返回的对象基类
 *
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
class WeChatBaseResponseDTO extends DTO {
    private static final long serialVersionUID = -8101654478662707793L;

    // 公众账号ID
    private String appid;
    // 商户号
    private String mch_id;
    // 返回状态码
    private String return_code;
    // 返回信息
    private String return_msg;
    // 业务结果
    private String result_code;
    // 随机字符串
    private String nonce_str;
    // 签名
    private String sign;

}
