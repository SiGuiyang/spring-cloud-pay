package quick.pager.pay.common.client;


import quick.pager.pay.common.client.response.WeiXinBaseResponse;

/**
 * 顶级支付接口
 */
public interface IClient {
    /**
     * 微信所有请求都使用此接口
     *
     * @param request 请求入参对象
     * @param url     请求地址
     * @return 返回结果
     */
    WeiXinBaseResponse execute(IRequest request, String url, WeiXinBaseResponse response);

    /**
     * 统一下单
     *
     * @param request 请求入参对象
     */
    PayResponse payOrder(IRequest request);

    /**
     * 退款
     *
     * @param request 请求入参对象
     */
    PayResponse refundOrder(IRequest request);

    /**
     * H5|手机网站支付
     *
     * @param request 请求入参对象
     */
    PayResponse wapPay(IRequest request);

    /**
     * 电脑网站支付
     * @param request 请求入参对象
     */
    PayResponse webPay(IRequest request);

    /**
     * 手机APP支付
     *
     * @param request 请求入参对象
     */
    PayResponse appPay(IRequest request);
}
