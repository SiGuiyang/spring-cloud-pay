package quick.pager.pay.app.controller;

import cn.hutool.core.util.ReUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import quick.pager.pay.Constants;
import quick.pager.pay.app.actor.ActorExecutor;
import quick.pager.pay.app.actor.PayActor;
import quick.pager.pay.app.exception.PayVerifyException;
import quick.pager.pay.app.function.DeferredResultFunction;
import quick.pager.pay.app.service.VerifyService;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.app.dto.SubmitPayDTO;
import quick.pager.pay.request.pay.PayRequest;
import quick.pager.pay.request.pay.QueryOrderRequest;
import quick.pager.pay.request.pay.RefundRequest;
import quick.pager.pay.response.PayResponse;
import quick.pager.pay.response.Response;

/**
 * 支付控制器<br />
 * 此接口对外暴露<br />
 *
 * @author siguiyang
 */
@Controller
@Api(description = "支付对外接口")
public class PayController {

    @Autowired
    private VerifyService verifyService;

    @ApiOperation("支付接口")
    @PostMapping("/cloud/pay")
    @ResponseBody
    public DeferredResult<Response> pay(@RequestBody PayRequest request) {
        // 验证入参
        verifyRequest(request);

        SubmitPayDTO submit = new SubmitPayDTO();
        submit.setBankCardNum(request.getBankCardNum());
        submit.setBankCode(request.getBankCode());
        submit.setClientIp(request.getClientIp());
        submit.setMerchantNo(request.getMerchantNo());
        submit.setMerchantOrderCode(request.getMerchantOrderCode());
        submit.setNotifyUrl(request.getNotifyUrl());
        submit.setPayAmount(request.getPayAmount());
        submit.setBody(request.getBody());
        submit.setSign(request.getSign());
        submit.setSignType(request.getSignType());
        submit.setTimestamp(request.getTimestamp());
        // 验证签名与商户存在判断
//        Response verifyResp = verifyService.doService(submit);
//        // 如果验证签名不同过，则直接返回
//        if (ResponseStatus.SUCCESS.code != verifyResp.getCode()) {
//            DeferredResult<Response> result = new DeferredResult<>();
//            result.setResult(verifyResp);
//            return result;
//        }
        DeferredResult<Response> deferredResult = new DeferredResult<>();
        // 使用akka执行线程
        ActorExecutor.execute(PayActor.class, "pay-actor", submit, (DeferredResultFunction<PayResponse, Response>) (failure, result, response) -> {
            Response resp = new Response();
            resp.setMsg(result.getMerchantNo());
            deferredResult.setResult(resp);
        });


        return deferredResult;
    }

    @ApiOperation("退款接口")
    @PostMapping("/cloud/refund")
    @ResponseBody
    public DeferredResult refund(@RequestBody RefundRequest request) {
        return null;
    }

    @ApiOperation("查询订单接口")
    @PostMapping("/cloud/queryOrder")
    @ResponseBody
    public DeferredResult queryOrder(@RequestBody QueryOrderRequest request) {
        return null;
    }

    /**
     * 验证请求入参
     *
     * @param request 请求参数
     */
    private void verifyRequest(PayRequest request) {
        // 商户号非空处理，正则验证
        if (StringUtils.isEmpty(request.getMerchantNo()) || !ReUtil.contains(Constants.Reg.ONLY_NUMBERS_AND_LETTERS, request.getMerchantNo())) {
            throw new PayVerifyException(ResponseStatus.UNKNOWN_PAY_MERCHANT_NO.code, ResponseStatus.UNKNOWN_PAY_MERCHANT_NO.msg);
        }
        // 商户订单号非空处理
        if (StringUtils.isEmpty(request.getMerchantOrderCode())) {
            throw new PayVerifyException(ResponseStatus.UNKNOWN_PAY_MERCHANT_ORDER_CODE.code, ResponseStatus.UNKNOWN_PAY_MERCHANT_ORDER_CODE.msg);

        }
        // 商户支付金额非空处理
        if (StringUtils.isEmpty(request.getPayAmount()) || !ReUtil.contains(Constants.Reg.ONLY_NUMBERS_AND_LETTERS, request.getPayAmount().toString())) {
            throw new PayVerifyException(ResponseStatus.UNKNOWN_PAY_AMOUNT.code, ResponseStatus.UNKNOWN_PAY_AMOUNT.msg);

        }

        if (StringUtils.isEmpty(request.getPayType())) {
            throw new PayVerifyException(ResponseStatus.UNKNOWN_PAY_TYPE.code, ResponseStatus.UNKNOWN_PAY_TYPE.msg);
        }

        if (StringUtils.isEmpty(request.getBankCardNum())) {
            throw new PayVerifyException(ResponseStatus.UNKNOWN_PAY_BANK_CARD_NUM.code, ResponseStatus.UNKNOWN_PAY_BANK_CARD_NUM.msg);
        }

        if (StringUtils.isEmpty(request.getBankCode())) {
            throw new PayVerifyException(ResponseStatus.UNKNOWN_PAY_BANK_CODE.code, ResponseStatus.UNKNOWN_PAY_BANK_CODE.msg);

        }

        if (StringUtils.isEmpty(request.getNotifyUrl())) {
            throw new PayVerifyException(ResponseStatus.UNKNOWN_PAY_NOTIFY_URL.code, ResponseStatus.UNKNOWN_PAY_NOTIFY_URL.msg);

        }

        if (StringUtils.isEmpty(request.getSign())) {
            throw new PayVerifyException(ResponseStatus.UNKNOWN_PAY_SIGN.code, ResponseStatus.UNKNOWN_PAY_SIGN.msg);

        }

        if (StringUtils.isEmpty(request.getSignType())) {
            throw new PayVerifyException(ResponseStatus.UNKNOWN_PAY_SIGN_TYPE.code, ResponseStatus.UNKNOWN_PAY_SIGN_TYPE.msg);

        }

        if (StringUtils.isEmpty(request.getTimestamp())) {
            throw new PayVerifyException(ResponseStatus.UNKNOWN_PAY_TIMESTAMP.code, ResponseStatus.UNKNOWN_PAY_TIMESTAMP.msg);
        }
    }
}
