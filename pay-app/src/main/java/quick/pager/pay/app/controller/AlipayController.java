package quick.pager.pay.app.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.pay.app.service.AlipayPayOrderClient;
import quick.pager.pay.request.AlipaySubmitPayRequest;
import quick.pager.pay.response.Response;

/**
 * 支付宝支付
 */
@RestController
public class AlipayController {

    @Autowired
    private AlipayPayOrderClient alipayPayOrderClient;

    @ApiOperation("支付宝原生支付")
    @PostMapping("/pay/alipay/app")
    public Response payAPP(AlipaySubmitPayRequest request){
        return null;
    }

    @ApiOperation("支付宝WAP支付")
    @PostMapping("/pay/alipay/wap")
    public Response payWap(AlipaySubmitPayRequest request){
        return null;
    }
}
