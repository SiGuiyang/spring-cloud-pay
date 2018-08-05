package quick.pager.pay.common.client;


import quick.pager.pay.common.client.request.WeiXinOrderRequest;
import quick.pager.pay.common.client.response.WeiXinPayOrderResponse;
import quick.pager.pay.common.constants.ResponseStatus;


/**
 * 微信支付
 */
public abstract class WeiXinPayClient extends BasePayClient {

    private static final String WEIXIN_PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    @Override
    public PayResponse payOrder(IRequest request) {
        PayResponse resp = new PayResponse();

        if (request instanceof WeiXinOrderRequest) {
            WeiXinOrderRequest orderRequest = (WeiXinOrderRequest) request;
            resp.setData(this.execute(orderRequest, WEIXIN_PAY_URL, new WeiXinPayOrderResponse()));
        } else {
            resp.setCode(ResponseStatus.WEIXIN_REQUEST_TYPE_EXCEPTION.code);
            resp.setMsg(ResponseStatus.WEIXIN_REQUEST_TYPE_EXCEPTION.msg);
        }
        return resp;
    }

    @Override
    public PayResponse refundOrder(IRequest request) {


        return null;
    }

    @Override
    public PayResponse wapPay(IRequest request) {
        return null;
    }

    @Override
    public PayResponse appPay(IRequest request) {
        return null;
    }

    @Override
    public PayResponse webPay(IRequest request) {
        return null;
    }
}
