package quick.pager.pay.common.client.request;

import quick.pager.pay.common.client.IRequest;

import java.io.Serializable;

/**
 * 支付宝支付公共参数基类
 */
public class BaseAlipayRequest implements IRequest {
    private static final long serialVersionUID = 202363687189113382L;
    /**
     * 支付宝分配给开发者的应用ID
     */
    private String appId;
    /**
     * 接口名称
     */
    private String method;
    /**
     * 仅支持JSON
     */
    private String format;
    /**
     * 请求使用的编码格式，如utf-8,gbk,gb2312等
     */
    private String charset;
    /**
     * 商户生成签名字符串所使用的签名算法类型<br />
     * 目前支持RSA2和RSA，推荐使用RSA2
     */
    private String signType;
    /**
     * 商户请求参数的签名串
     */
    private String sign;
    /**
     * 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
     */
    private String timestamp;
    /**
     * 调用的接口版本，固定为：1.0
     */
    private String version;
    /**
     * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。
     */
    private String notifyUrl;
    /**
     * 应用授权token
     */

    private String appAuthToken;
    /**
     * 请求参数的集合，最大长度不限<br />
     * 除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
     */
    private String bizContent;
    /**
     * 请求地址，沙箱环境还是正式地址
     */
    private String requestUrl;

    /**
     * 支付宝分配的私钥
     */
    private String privateKey;
    /**
     * 支付宝分配至的公钥
     */
    private String alipayPublicKey;
    /**
     * 返回地址url
     */
    private String returnUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getAppAuthToken() {
        return appAuthToken;
    }

    public void setAppAuthToken(String appAuthToken) {
        this.appAuthToken = appAuthToken;
    }

    public String getBizContent() {
        return bizContent;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
