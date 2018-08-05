package quick.pager.pay.common.client.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 统一下单
 */
@XStreamAlias("xml")
public class WeiXinOrderRequest extends WeiXinBaseRequest {

    private static final long serialVersionUID = -5957460267959606864L;
    /**
     * 设备号
     */
    @XStreamAlias("device_info")
    private String deviceInfo;
    /**
     * 商品描述
     */
    @XStreamAlias("body")
    private String body;
    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    /**
     * 总金额
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;
    /**
     * 终端IP
     */
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;
    /**
     * 通知地址
     */
    @XStreamAlias("notify_url")
    private String notifyUrl;
    /**
     * 交易类型
     */
    @XStreamAlias("trade_type")
    private String tradeType;
    /**
     * 场景信息
     */
    @XStreamAlias("scene_info")
    private String sceneInfo;

    @XStreamAlias("openid")
    private String openId;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }


    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
