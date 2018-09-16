package quick.pager.pay.weixin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.dto.pay.AccessTokenDTO;
import quick.pager.pay.dto.pay.MpPayDTO;
import quick.pager.pay.dto.pay.PayConfigDTO;
import quick.pager.pay.dto.pay.WeChatPayDTO;
import quick.pager.pay.dto.pay.WeChatVerifyDTO;
import quick.pager.pay.request.pay.WeChatSubmitPayRequest;
import quick.pager.pay.response.Response;
import quick.pager.pay.weixin.service.AccessOpenIdService;
import quick.pager.pay.weixin.service.MpPayService;
import quick.pager.pay.weixin.service.WeChatRefundService;
import quick.pager.pay.weixin.service.VerifySignService;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信H5支付
 *
 * @author siguiyang
 */
@Controller
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
    @RequestMapping(value = "/wechat/pay/submit", method = RequestMethod.POST)
    public String paySubmit(WeChatSubmitPayRequest request) throws Exception {

        WeChatVerifyDTO dto = new WeChatVerifyDTO();

        dto.setOrderCode(request.getOrderCode());
        dto.setNotifyUrl(request.getNotifyUrl());
        dto.setAppId(request.getAppId());
        dto.setMchId(request.getMchId());
        dto.setNonceStr(request.getNonceStr());
        dto.setScope(request.getScope());
        dto.setTimestamp(request.getTimestamp());
        dto.setSign(request.getSign());

        Response response = verifySignService.doService(dto);

        StringBuffer buffer = new StringBuffer("redirect:");
        // 验证签名成功
        if (ResponseStatus.SUCCESS.code == response.getCode()) {
            buffer.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=").append("")
                    .append("&").append("redirect_uri").append("=").append("")
                    .append("&").append("response_type").append("=").append("code")
                    .append("&").append("scope").append("=").append(request.getScope())
                    .append("&").append("state").append("=").append(request.getOrderCode())
                    .append("#").append("wechat_redirect");
            return buffer.toString();
        }
        return buffer.append("/wechat-pay-error").append("?msg=").append(response.getMsg()).toString();
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

        Response<MpPayDTO> response = accessOpenIdService.doService(dto);

        if (ResponseStatus.SUCCESS.code != response.getCode()) {
            request.setAttribute("msg", response.getMsg());
            return "wechat-pay-error";
        }


        MpPayDTO data = response.getData();

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
        Response<PayConfigDTO> payResp = mpPayService.doService(weChatPayDTO);

        if (ResponseStatus.SUCCESS.code != payResp.getCode()) {
            request.setAttribute("msg", payResp.getMsg());
            return "wechat-pay-error";
        }
        PayConfigDTO payConfigDTO = payResp.getData();
        payConfigDTO.setSign_Type(Constants.SignType.MD5.name());
        payConfigDTO.setTimeStamp(System.currentTimeMillis() + "");

        request.setAttribute("payConfigDTO", payResp.getData());

        return "wechat-pay";
    }

    /**
     * 退款
     */
    @PostMapping("/wechat/refund")
    public Response refund(WeChatSubmitPayRequest request) {

        WeChatVerifyDTO dto = new WeChatVerifyDTO();

        dto.setOrderCode(request.getOrderCode());
        dto.setAppId(request.getAppId());
        dto.setMchId(request.getMchId());
        dto.setNonceStr(request.getNonceStr());
        dto.setScope(request.getScope());
        dto.setTimestamp(request.getTimestamp());
        dto.setSign(request.getSign());

        Response response = verifySignService.doService(dto);

        weChatRefundService.doService(null);
        return null;
    }
}
