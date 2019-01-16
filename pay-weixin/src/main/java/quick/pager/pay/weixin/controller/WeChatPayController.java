package quick.pager.pay.weixin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.weixin.dto.AccessTokenDTO;
import quick.pager.pay.weixin.dto.MpWeChatPayDTO;
import quick.pager.pay.weixin.dto.WeChatPayConfigDTO;
import quick.pager.pay.weixin.dto.WeChatPayDTO;
import quick.pager.pay.weixin.dto.WeChatRefundDTOWeChat;
import quick.pager.pay.weixin.dto.WeChatVerifyDTOWeChat;
import quick.pager.pay.request.pay.WeChatSubmitPayRequest;
import quick.pager.pay.request.pay.WeiXinRefundRequest;
import quick.pager.pay.response.Response;
import quick.pager.pay.weixin.service.AccessOpenIdService;
import quick.pager.pay.weixin.service.MpPayService;
import quick.pager.pay.weixin.service.WeChatRefundService;
import quick.pager.pay.weixin.service.VerifySignService;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信支付
 *
 * @author siguiyang
 */
@Controller
@Slf4j
public class WeChatPayController {

    @Autowired
    private MpPayService mpPayService;
    @Autowired
    private VerifySignService verifySignService;
    @Autowired
    private WeChatRefundService weChatRefundService;
    @Autowired
    private AccessOpenIdService accessOpenIdService;

    /**
     * 微信提交订单<br />
     * 此处验签
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String paySubmit(HttpServletRequest httpServletRequest, WeChatSubmitPayRequest request) throws Exception {

        System.out.println("weChat pay.....");
        WeChatVerifyDTOWeChat dto = new WeChatVerifyDTOWeChat();

        dto.setOrderCode(request.getOrderCode());
        dto.setNotifyUrl(request.getNotifyUrl());
        dto.setNonceStr(request.getNonceStr());
        dto.setScope(request.getScope());
        dto.setTimestamp(request.getTimestamp());
        dto.setSign(request.getSign());
        // 对参数开始验证签名
        Response response = verifySignService.doService(dto);

        StringBuffer buffer = new StringBuffer("redirect:");
        // 验证签名成功
//        if (ResponseStatus.SUCCESS.code == response.getCode()) {
//            // 重定向到微信获取授权码
//            buffer.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=").append("")
//                    .append("&").append("redirect_uri").append("=").append("")
//                    .append("&").append("response_type").append("=").append("code")
//                    .append("&").append("scope").append("=").append(request.getScope())
//                    .append("&").append("state").append("=").append(request.getOrderCode())
//                    .append("#").append("wechat_redirect");
//            return buffer.toString();
//        }

        httpServletRequest.setAttribute("msg", response.getMsg());
        return "wechat-pay-error";
    }

    /**
     * @param code  授权码
     * @param state 扩展参数,支付订单的订单号
     */
    @RequestMapping("/wechat/pay/access")
    public String payAccess(HttpServletRequest request, String code, String state) {

        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setCode(code);
        dto.setOrderCode(state);
        // 获取公众号支付的openId
        Response<MpWeChatPayDTO> response = accessOpenIdService.doService(dto);

        if (ResponseStatus.SUCCESS.code != response.getCode()) {
            request.setAttribute("msg", response.getMsg());
            return "wechat-pay-error";
        }


        MpWeChatPayDTO data = response.getData();

        WeChatPayDTO weChatPayDTO = new WeChatPayDTO();
        weChatPayDTO.setAppid(data.getAppId());
        weChatPayDTO.setMchId(data.getMchId());
        weChatPayDTO.setTotalFee(data.getPayAmount().toString());
        weChatPayDTO.setOutTradeNo(data.getOrderCode());
        weChatPayDTO.setTradeType(Constants.TradeType.WECHAT_JSAPI_TRADE_TYPE);
        weChatPayDTO.setNotifyUrl(data.getNotifyUrl());
        weChatPayDTO.setBody(data.getBody());
        weChatPayDTO.setSpbillCreateIp(data.getClientIp());

        // 公众号支付
        Response<WeChatPayConfigDTO> payResp = mpPayService.doService(weChatPayDTO);

        if (ResponseStatus.SUCCESS.code != payResp.getCode()) {
            request.setAttribute("msg", payResp.getMsg());
            return "wechat-pay-error";
        }
        WeChatPayConfigDTO payConfigDTO = payResp.getData();
        payConfigDTO.setSign_Type(Constants.SignType.MD5.name());
        payConfigDTO.setTimeStamp(System.currentTimeMillis() + "");

        request.setAttribute("payConfigDTO", payResp.getData());

        return "wechat-pay";
    }

    /**
     * 退款
     */
    @PostMapping("/refund")
    public String refund(HttpServletRequest httpServletRequest, WeiXinRefundRequest request) {

        // 验证入参
        Response<String> checkResponse = checkRefundParams(request);

        if (ResponseStatus.SUCCESS.code != checkResponse.getCode()) {

            httpServletRequest.setAttribute("msg", checkResponse.getMsg());

            return "wechat-pay-error";
        }

        WeChatVerifyDTOWeChat dto = new WeChatVerifyDTOWeChat();
        dto.setNotifyUrl(request.getNotifyUrl());
        dto.setOrderCode(request.getOrderCode());
        dto.setNonceStr(request.getNonceStr());
        dto.setTimestamp(request.getTimestamp());
        dto.setSign(request.getSign());

        //  验证签名
        Response response = verifySignService.doService(dto);

        // 验签成功
        if (ResponseStatus.SUCCESS.code != response.getCode()) {

            httpServletRequest.setAttribute("msg", response.getMsg());
            return "wechat-pay-error";
        }

        // 开始请求退款
        WeChatRefundDTOWeChat weChatRefundDTO = new WeChatRefundDTOWeChat();
        weChatRefundDTO.setOrderCode(request.getOrderCode());

        weChatRefundService.doService(weChatRefundDTO);

        return "";
    }

    /**
     * 检查微信退款入参校验
     *
     * @param request 入参
     */
    private Response<String> checkRefundParams(WeiXinRefundRequest request) {


        if (StringUtils.isEmpty(request.getNonceStr())) {
            log.info("请求唯一标志不能为空");
            return new Response<>(ResponseStatus.CHECK_PARAMS_NONCE_STR.code, ResponseStatus.CHECK_PARAMS_NONCE_STR.msg);
        }
        if (StringUtils.isEmpty(request.getOrderCode())) {
            log.info("平台商户订单号不能为空");
            return new Response<>(ResponseStatus.CHECK_PARAMS_ORDER_CODE.code, ResponseStatus.CHECK_PARAMS_ORDER_CODE.msg);

        }
        if (StringUtils.isEmpty(request.getTimestamp())) {
            log.info("请求时间戳不能为空");
            return new Response<>(ResponseStatus.CHECK_PARAMS_TIMESTAMP.code, ResponseStatus.CHECK_PARAMS_TIMESTAMP.msg);

        }
        if (StringUtils.isEmpty(request.getSign())) {
            log.info("请求支付签名不能为空");
            return new Response<>(ResponseStatus.CHECK_PARAMS_SIGN.code, ResponseStatus.CHECK_PARAMS_SIGN.msg);

        }

        return new Response<>();
    }
}
