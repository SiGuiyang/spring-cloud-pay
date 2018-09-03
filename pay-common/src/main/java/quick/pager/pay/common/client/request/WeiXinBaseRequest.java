package quick.pager.pay.common.client.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import quick.pager.pay.common.client.IRequest;


/**
 * 微信请求入参基类
 * @author siguiyang
 */
@Data
public class WeiXinBaseRequest implements IRequest {
    private static final long serialVersionUID = 8049847864320904740L;
    /**
     * 公众账号ID
     */
    @XStreamAlias("appid")
    private String appId;
    /**
     * 商户号
     */
    @XStreamAlias("mch_id")
    private String mchId;
    /**
     * 随机字符串
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;
    /**
     * 签名
     */
    @XStreamAlias("sign")
    private String sign;
    /**
     * 签名类型
     */
    @XStreamAlias("sign_type")
    private String signType;
}
