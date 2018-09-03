package quick.pager.pay.common.client.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 统一下单
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeiXinPayOrderResponse extends WeiXinBaseResponse {

    private static final long serialVersionUID = -3844198643371690228L;
    /**
     * 设备号
     * 调用接口提交的终端设备号
     */
    private String deviceInfo;
    /**
     * 交易类型
     * 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，,H5支付固定传MWEB
     */
    private String tradeType;
    /**
     * 预支付交易会话标识
     * 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时,针对H5支付此参数无特殊用途
     */
    private String prepayId;
    /**
     * 支付跳转链接
     * mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。
     * https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?prepay_id=wx2016121516420242444321ca0631331346&package=1405458241
     */
    private String mwebUrl;

}
