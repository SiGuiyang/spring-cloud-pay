package quick.pager.pay.alipay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.pay.alipay.service.AlipayVerifySignService;
import quick.pager.pay.alipay.service.AlipayWapPayOrderService;
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
@Slf4j
public class AlipayController {

    @Autowired
    private AlipayWapPayOrderService alipayWapPayOrderService;
    @Autowired
    private AlipayVerifySignService alipayVerifySignService;


    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String payAPP(HttpServletRequest httpRequest, HttpServletResponse httpResponse, AlipaySubmitPayRequest request) {

        System.out.println("pay ========= ");
        alipayVerifySignService.doService(null);
        alipayWapPayOrderService.doService(null);
        return "alipay";
    }


    /**
     * 验证支付宝请求入参
     */
    private Response checkPayParams(AlipaySubmitPayRequest request) {
        return new Response();
    }
}
