package quick.pager.pay.alipay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.pay.alipay.dto.AlipayDTO;
import quick.pager.pay.alipay.dto.VerifySignDTO;
import quick.pager.pay.alipay.service.AlipayVerifySignService;
import quick.pager.pay.alipay.service.AlipayWapPayOrderService;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.request.pay.AlipaySubmitPayRequest;
import quick.pager.pay.response.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付宝支付
 *
 * @author siguiyang
 */
@RestController
public class AlipayController {

    @Autowired
    private AlipayWapPayOrderService alipayWapPayOrderService;
    @Autowired
    private AlipayVerifySignService alipayVerifySignService;


    @RequestMapping(value = "/alipay/app", method = RequestMethod.POST)
    public String payAPP(HttpServletRequest httpRequest, HttpServletResponse httpResponse, AlipaySubmitPayRequest request) {


        return null;
    }


    @RequestMapping(value = "/pay/wap", method = RequestMethod.POST)
    public String payWap(HttpServletRequest httpRequest, HttpServletResponse httpResponse, AlipaySubmitPayRequest request) {
        Response response = checkPayParams(request);
        // 参数验证
        if (ResponseStatus.SUCCESS.code != response.getCode()) {

            httpRequest.setAttribute("msg", response.getMsg());
            return "pay-error";
        }

        VerifySignDTO verifySignDTO = new VerifySignDTO();
        verifySignDTO.setOrderCode(request.getOrderCode());

        Response verifySignResponse = alipayVerifySignService.doService(verifySignDTO);

        // 签名不正确
        if (ResponseStatus.SUCCESS.code != verifySignResponse.getCode()) {

            httpRequest.setAttribute("msg", response.getMsg());
            return "pay-error";
        }

        AlipayDTO alipayDTO = new AlipayDTO();
        alipayDTO.setOrderCode(request.getOrderCode());
        Response<String> payResponse = alipayWapPayOrderService.doService(alipayDTO);

        // wap 支付不成功
        if (ResponseStatus.SUCCESS.code != verifySignResponse.getCode()) {
            httpRequest.setAttribute("msg", response.getMsg());
            return "pay-error";
        }

        try {
            httpResponse.setContentType("text/html;charset=UTF-8");
            httpResponse.getWriter().write(payResponse.getData());//直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证支付宝请求入参
     */
    private Response checkPayParams(AlipaySubmitPayRequest request) {
        return new Response();
    }
}
