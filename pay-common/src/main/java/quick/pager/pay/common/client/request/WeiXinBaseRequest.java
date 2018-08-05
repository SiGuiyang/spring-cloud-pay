package quick.pager.pay.common.client.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import quick.pager.pay.common.client.IRequest;


/**
 * 微信请求入参基类
 */
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
