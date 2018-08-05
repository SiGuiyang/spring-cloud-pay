package quick.pager.pay.common.client;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import quick.pager.pay.common.client.request.AlipayAppRequest;
import quick.pager.pay.common.client.request.AlipayRefundRequest;
import quick.pager.pay.common.client.request.AlipayRequest;
import quick.pager.pay.common.client.request.AlipayWapRequest;
import quick.pager.pay.common.constants.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝支付
 */
public abstract class PayClient extends BasePayClient {

    @Override
    public PayResponse payOrder(IRequest request) {

        PayResponse resp = new PayResponse();
        if (request instanceof AlipayRequest) {
            AlipayRequest orderRequest = (AlipayRequest) request;
            AlipayClient client = new DefaultAlipayClient(orderRequest.getRequestUrl(), orderRequest.getAppId(), orderRequest.getPrivateKey(), "json", "UTF-8", orderRequest.getAlipayPublicKey(), orderRequest.getSignType());
            // 参数组装到第三方支付宝
            AlipayTradePayModel alipayTradePayModel = new AlipayTradePayModel();
            alipayTradePayModel.setAuthCode(orderRequest.getAuthCode());
            alipayTradePayModel.setBody(orderRequest.getBody());
            alipayTradePayModel.setBuyerId(orderRequest.getBuyerId());
            alipayTradePayModel.setOperatorId(orderRequest.getOperatorId());
            alipayTradePayModel.setOutTradeNo(orderRequest.getOutTradeNo());
            alipayTradePayModel.setTotalAmount(orderRequest.getTotalAmount().toPlainString());
            alipayTradePayModel.setSubject(orderRequest.getSubject());
            alipayTradePayModel.setTransCurrency(orderRequest.getTransCurrency());
            alipayTradePayModel.setSettleCurrency(orderRequest.getSettleCurrency());
            alipayTradePayModel.setTerminalId(orderRequest.getTerminalId());
            alipayTradePayModel.setTimeoutExpress(orderRequest.getTimeoutExpress());
            alipayTradePayModel.setAuthConfirmMode(orderRequest.getAuthConfirmMode());
            alipayTradePayModel.setTerminalParams(orderRequest.getTerminalParams());
            alipayTradePayModel.setSellerId(orderRequest.getSellerId());
            alipayTradePayModel.setScene(orderRequest.getScene());
            alipayTradePayModel.setProductCode(orderRequest.getProductCode());

            AlipayTradePayRequest alipayTradePayRequest = new AlipayTradePayRequest();
            alipayTradePayRequest.setBizModel(alipayTradePayModel);
            alipayTradePayRequest.setNotifyUrl(orderRequest.getNotifyUrl());
            alipayTradePayRequest.setNeedEncrypt(true);
            alipayTradePayRequest.setReturnUrl(orderRequest.getReturnUrl());

            try {
                // 请求支付
                AlipayTradePayResponse response = client.execute(alipayTradePayRequest);
                if (response.isSuccess()) {
                    resp.setCode(ResponseStatus.SUCCESS.code);
                    resp.setMsg(ResponseStatus.SUCCESS.msg);
                    Map<String, String> result = new HashMap<>();
                    result.put("tradeNo", response.getTradeNo());
                    result.put("outTradeNo", response.getOutTradeNo());
                    result.put("openId", response.getOpenId());
                    resp.setData(result);
                } else {
                    resp.setCode(ResponseStatus.ALIPAY_REQUEST_RESPONSE.code);
                    resp.setMsg(ResponseStatus.ALIPAY_REQUEST_RESPONSE.msg);
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
                resp.setCode(ResponseStatus.ALIPAY_REQUEST_EXCEPTION.code);
                resp.setMsg(ResponseStatus.ALIPAY_REQUEST_EXCEPTION.msg);
            }

        } else {
            resp.setCode(ResponseStatus.ALIPAY_REQUEST_TYPE_EXCEPTION.code);
            resp.setMsg(ResponseStatus.ALIPAY_REQUEST_TYPE_EXCEPTION.msg);
        }
        return resp;
    }

    @Override
    public PayResponse refundOrder(IRequest request) {
        PayResponse resp = new PayResponse();
        if (request instanceof AlipayRefundRequest) {
            AlipayRefundRequest refundRequest = (AlipayRefundRequest) request;
            AlipayClient client = new DefaultAlipayClient(refundRequest.getRequestUrl(), refundRequest.getAppId(), refundRequest.getPrivateKey(), "json", "UTF-8", refundRequest.getAlipayPublicKey(), refundRequest.getSignType());

            AlipayTradeRefundModel alipayTradeRefundModel = new AlipayTradeRefundModel();

            alipayTradeRefundModel.setOutTradeNo(refundRequest.getOutTradeNo());
            alipayTradeRefundModel.setTradeNo(refundRequest.getTradeNo());
            alipayTradeRefundModel.setOutRequestNo(refundRequest.getOutRequestNo());
            alipayTradeRefundModel.setOperatorId(refundRequest.getOperatorId());
            alipayTradeRefundModel.setRefundAmount(refundRequest.getRefundAmount().toPlainString());
            alipayTradeRefundModel.setRefundCurrency(refundRequest.getRefundCurrency());
            alipayTradeRefundModel.setRefundReason(refundRequest.getRefundReason());
            alipayTradeRefundModel.setTerminalId(refundRequest.getTerminalId());
            alipayTradeRefundModel.setStoreId(refundRequest.getStoreId());

            AlipayTradeRefundRequest alipayTradeRefundRequest = new AlipayTradeRefundRequest();

            alipayTradeRefundRequest.setBizModel(alipayTradeRefundModel);
            alipayTradeRefundRequest.setNeedEncrypt(true);
            alipayTradeRefundRequest.setNotifyUrl(refundRequest.getNotifyUrl());
            alipayTradeRefundRequest.setReturnUrl(refundRequest.getReturnUrl());

            try {
                AlipayTradeRefundResponse response = client.execute(alipayTradeRefundRequest);

                if (response.isSuccess()) {
                    resp.setCode(ResponseStatus.SUCCESS.code);
                    resp.setMsg(ResponseStatus.SUCCESS.msg);
                    Map<String, String> result = new HashMap<>();
                    result.put("tradeNo", response.getTradeNo());
                    result.put("outTradeNo", response.getOutTradeNo());
                    result.put("amount", response.getRefundFee());
                    resp.setData(result);
                } else {
                    resp.setCode(ResponseStatus.ALIPAY_REQUEST_RESPONSE.code);
                    resp.setMsg(ResponseStatus.ALIPAY_REQUEST_RESPONSE.msg);
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
                resp.setCode(ResponseStatus.ALIPAY_REQUEST_EXCEPTION.code);
                resp.setMsg(ResponseStatus.ALIPAY_REQUEST_EXCEPTION.msg);
            }

        } else {
            resp.setCode(ResponseStatus.ALIPAY_REQUEST_TYPE_EXCEPTION.code);
            resp.setMsg(ResponseStatus.ALIPAY_REQUEST_TYPE_EXCEPTION.msg);
        }
        return resp;
    }

    @Override
    public PayResponse wapPay(IRequest request) {
        PayResponse resp = new PayResponse();
        if (request instanceof AlipayWapRequest) {
            AlipayWapRequest wapRequest = (AlipayWapRequest) request;
            AlipayClient client = new DefaultAlipayClient(wapRequest.getRequestUrl(), wapRequest.getAppId(), wapRequest.getPrivateKey(), "json", "UTF-8", wapRequest.getAlipayPublicKey(), wapRequest.getSignType());

            AlipayTradeWapPayModel alipayTradeWapPayModel = new AlipayTradeWapPayModel();
            alipayTradeWapPayModel.setBody(wapRequest.getBody());
            alipayTradeWapPayModel.setOutTradeNo(wapRequest.getOutTradeNo());
            alipayTradeWapPayModel.setTotalAmount(wapRequest.getTotalAmount().toPlainString());
            alipayTradeWapPayModel.setSubject(wapRequest.getSubject());
            alipayTradeWapPayModel.setProductCode(wapRequest.getProductCode());
            alipayTradeWapPayModel.setTimeoutExpress(wapRequest.getTimeoutExpress());
            //创建API对应的request
            AlipayTradeWapPayRequest alipayTradeWapPayRequest = new AlipayTradeWapPayRequest();
            alipayTradeWapPayRequest.setBizModel(alipayTradeWapPayModel);
            alipayTradeWapPayRequest.setNotifyUrl(wapRequest.getNotifyUrl());
            alipayTradeWapPayRequest.setReturnUrl(wapRequest.getReturnUrl());
            alipayTradeWapPayRequest.setNeedEncrypt(true);
            try {
                AlipayTradeWapPayResponse response = client.pageExecute(alipayTradeWapPayRequest);
                if (response.isSuccess()) {
                    resp.setCode(ResponseStatus.SUCCESS.code);
                    resp.setMsg(ResponseStatus.SUCCESS.msg);
                    Map<String, String> result = new HashMap<>();
                    result.put("payUrl", response.getBody());
                    resp.setData(result);
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
                resp.setCode(ResponseStatus.ALIPAY_REQUEST_EXCEPTION.code);
                resp.setMsg(ResponseStatus.ALIPAY_REQUEST_EXCEPTION.msg);
            }

        } else {
            resp.setCode(ResponseStatus.ALIPAY_REQUEST_TYPE_EXCEPTION.code);
            resp.setMsg(ResponseStatus.ALIPAY_REQUEST_TYPE_EXCEPTION.msg);
        }
        return resp;
    }

    @Override
    public PayResponse appPay(IRequest request) {
        PayResponse resp = new PayResponse();
        if (request instanceof AlipayAppRequest) {
            AlipayAppRequest appRequest = (AlipayAppRequest) request;
            AlipayClient client = new DefaultAlipayClient(appRequest.getRequestUrl(), appRequest.getAppId(), appRequest.getPrivateKey(), "json", "UTF-8", appRequest.getAlipayPublicKey(), appRequest.getSignType());

            AlipayTradeAppPayModel alipayTradeAppPayModel = new AlipayTradeAppPayModel();
            alipayTradeAppPayModel.setBody(appRequest.getBody());
            alipayTradeAppPayModel.setOutTradeNo(appRequest.getOutTradeNo());
            alipayTradeAppPayModel.setTotalAmount(appRequest.getTotalAmount().toPlainString());
            alipayTradeAppPayModel.setSubject(appRequest.getSubject());
            //创建API对应的request
            AlipayTradeAppPayRequest alipayTradeAppPayRequest = new AlipayTradeAppPayRequest();
            alipayTradeAppPayRequest.setBizModel(alipayTradeAppPayModel);
            alipayTradeAppPayRequest.setNotifyUrl(appRequest.getNotifyUrl());
            alipayTradeAppPayRequest.setReturnUrl(appRequest.getReturnUrl());
            alipayTradeAppPayRequest.setNeedEncrypt(true);
            try {
                AlipayTradeAppPayResponse response = client.sdkExecute(alipayTradeAppPayRequest);
                if (response.isSuccess()) {
                    resp.setCode(ResponseStatus.SUCCESS.code);
                    resp.setMsg(ResponseStatus.SUCCESS.msg);
                    Map<String, String> result = new HashMap<>();
                    result.put("payPrams", response.getBody());
                    resp.setData(result);
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
                resp.setCode(ResponseStatus.ALIPAY_REQUEST_EXCEPTION.code);
                resp.setMsg(ResponseStatus.ALIPAY_REQUEST_EXCEPTION.msg);
            }

        } else {
            resp.setCode(ResponseStatus.ALIPAY_REQUEST_TYPE_EXCEPTION.code);
            resp.setMsg(ResponseStatus.ALIPAY_REQUEST_TYPE_EXCEPTION.msg);
        }
        return resp;
    }

    @Override
    public PayResponse webPay(IRequest request) {
        PayResponse resp = new PayResponse();


        return null;
    }
}
