package quick.pager.pay.common.client.request;


import java.math.BigDecimal;

/**
 * 统一收单交易支付
 */
public class AlipayRequest extends BaseAlipayRequest {
    private static final long serialVersionUID = -8497004645794182194L;
    /**
     * 商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
     */
    private String outTradeNo;

    /**
     * 支付场景
     * 条码支付，取值：bar_code
     * 声波支付，取值：wave_code
     */
    private String scene;
    /**
     * 支付授权码，25~30开头的长度为16~24位的数字，实际字符串长度以开发者获取的付款码长度为准
     */
    private String authCode;
    /**
     * 销售产品码
     */
    private String productCode;
    /**
     * 订单标题
     */
    private String subject;
    /**
     * 买家的支付宝用户id，如果为空，会从传入了码值信息中获取买家ID
     */
    private String buyerId;
    /**
     * 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
     */
    private String sellerId;
    /**
     * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * 如果同时传入【可打折金额】和【不可打折金额】，该参数可以不用传入；
     * 如果同时传入了【可打折金额】，【不可打折金额】，【订单总金额】三者，则必须满足如下条件：【订单总金额】=【可打折金额】+【不可打折金额】
     */
    private BigDecimal totalAmount;
    /**
     * 标价币种,total_amount 对应的币种单位。<br />
     * 支持<br />
     * 英镑：GBP<br />
     * 港币：HKD<br />
     * 美元：USD<br />
     * 新加坡元：SGD<br />
     * 日元：JPY<br />
     * 加拿大元：CAD<br />
     * 澳元：AUD<br />
     * 欧元：EUR<br />
     * 新西兰元：NZD<br />
     * 韩元：KRW<br />
     * 泰铢：THB<br />
     * 瑞士法郎：CHF<br />
     * 瑞典克朗：SEK<br />
     * 丹麦克朗：DKK<br />
     * 挪威克朗：NOK<br />
     * 马来西亚林吉特：MYR<br />
     * 印尼卢比：IDR<br />
     * 菲律宾比索：PHP<br />
     * 毛里求斯卢比：MUR<br />
     * 以色列新谢克尔：ILS<br />
     * 斯里兰卡卢比：LKR<br />
     * 俄罗斯卢布：RUB<br />
     * 阿联酋迪拉姆：AED<br />
     * 捷克克朗：CZK<br />
     * 南非兰特：ZAR<br />
     * 人民币：CNY
     */
    private String transCurrency;
    /**
     * 商户指定的结算币种<br />
     * 支持：<br />
     * 英镑：GBP<br />
     * 港币：HKD<br />
     * 美元：USD<br />
     * 新加坡元：SGD<br />
     * 日元：JPY<br />
     * 加拿大元：CAD<br />
     * 澳元：AUD<br />
     * 欧元：EUR<br />
     * 新西兰元：NZD<br />
     * 韩元：KRW<br />
     * 泰铢：THB<br />
     * 瑞士法郎：CHF<br />
     * 瑞典克朗：SEK<br />
     * 丹麦克朗：DKK<br />
     * 挪威克朗：NOK<br />
     * 马来西亚林吉特：MYR<br />
     * 印尼卢比：IDR<br />
     * 菲律宾比索：PHP<br />
     * 毛里求斯卢比：MUR<br />
     * 以色列新谢克尔：ILS<br />
     * 斯里兰卡卢比：LKR<br />
     * 俄罗斯卢布：RUB<br />
     * 阿联酋迪拉姆：AED<br />
     * 捷克克朗：CZK<br />
     * 南非兰特：ZAR<br />
     * 人民币：CNY
     */
    private String settleCurrency;
    /**
     * 如果该值未传入，但传入了【订单总金额】和【不可打折金额】，则该值默认为【订单总金额】-【不可打折金额】<br />
     * 例如： 8.88
     */
    private BigDecimal discountableAmount;
    /**
     * 订单描述,<br />
     * 例如：Iphone6 16G
     */
    private String body;
    /**
     * 商户操作员编号
     */
    private String operatorId;
    /**
     * 商户门店编号
     */
    private String storeId;
    /**
     * 商户机具终端编号
     */
    private String terminalId;
    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m<br />
     * 例如：90m
     */
    private String timeoutExpress;
    /**
     * 预授权确认模式，授权转交易请求中传入，适用于预授权转交易业务使用，目前只支持PRE_AUTH(预授权产品码)<br />
     * COMPLETE：转交易支付完成结束预授权，解冻剩余金额; NOT_COMPLETE：转交易支付完成不结束预授权，不解冻剩余金额<br />>
     * 例如：COMPLETE：转交易支付完成结束预授权;NOT_COMPLETE：转交易支付完成不结束预授权<br />
     */
    private String authConfirmMode;
    /**
     * 商户传入终端设备相关信息，具体值要和支付宝约定<br />
     * 例如：{"key":"value"}
     */
    private String terminalParams;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransCurrency() {
        return transCurrency;
    }

    public void setTransCurrency(String transCurrency) {
        this.transCurrency = transCurrency;
    }

    public String getSettleCurrency() {
        return settleCurrency;
    }

    public void setSettleCurrency(String settleCurrency) {
        this.settleCurrency = settleCurrency;
    }

    public BigDecimal getDiscountableAmount() {
        return discountableAmount;
    }

    public void setDiscountableAmount(BigDecimal discountableAmount) {
        this.discountableAmount = discountableAmount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTimeoutExpress() {
        return timeoutExpress;
    }

    public void setTimeoutExpress(String timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
    }

    public String getAuthConfirmMode() {
        return authConfirmMode;
    }

    public void setAuthConfirmMode(String authConfirmMode) {
        this.authConfirmMode = authConfirmMode;
    }

    public String getTerminalParams() {
        return terminalParams;
    }

    public void setTerminalParams(String terminalParams) {
        this.terminalParams = terminalParams;
    }
}
