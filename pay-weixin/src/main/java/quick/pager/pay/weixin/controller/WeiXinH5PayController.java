package quick.pager.pay.weixin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.dto.pay.WeiXinSubmitPayDto;
import quick.pager.pay.request.WeiXinRefundRequest;
import quick.pager.pay.request.WeiXinSubmitPayRequest;
import quick.pager.pay.response.Response;
import quick.pager.pay.vo.pay.WeiXinSignVO;
import quick.pager.pay.weixin.service.PayService;
import quick.pager.pay.weixin.service.RefundService;
import quick.pager.pay.weixin.service.VerifySignService;

import java.net.URLEncoder;

/**
 * 微信H5支付
 *
 * @author siguiyang
 *
 */
@Controller
public class WeiXinH5PayController {

    @Autowired
    private PayService payService;
    @Autowired
    private VerifySignService verifySignService;
    @Autowired
    private RefundService refundService;

    /**
     * 微信提交订单<br />
     * 此处验签
     */
    @RequestMapping(value = "/weixin/pay/submit", method = RequestMethod.POST)
    public String payH5(WeiXinSubmitPayRequest request) throws Exception {

        WeiXinSubmitPayDto dto = new WeiXinSubmitPayDto();
        dto.setAmount(request.getAmount());
        dto.setBody(URLEncoder.encode(request.getBody(), "UTF-8"));
        dto.setMerchantOrderNo(request.getMerchantOrderNo());
        dto.setNotifyUrl(request.getNotifyUrl());
        dto.setSign(request.getSign());
        dto.setSignType(request.getSignType());
        dto.setTimestamp(request.getTimestamp());

        Response response = verifySignService.doService(dto);
        StringBuffer buffer = new StringBuffer();
        // 验证签名成功
        if (ResponseStatus.SUCCESS.code == response.getCode()) {
            WeiXinSignVO vo = (WeiXinSignVO) response.getData();
            return buffer.append("redirect:").append("/weixin/pay/app").append("?code=").append("").toString();
        }

        return buffer.append("redirect:").append("/wechat-pay-error").append("?msg=").append(response.getMsg()).toString();
    }

    /**
     * 退款
     */
    @PostMapping("/weixin/refund")
    public Response refund(WeiXinRefundRequest request) {
        return null;
    }
}
