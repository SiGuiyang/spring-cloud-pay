package quick.pager.pay.app.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import quick.pager.pay.app.service.WeiXinPayOrderClient;
import quick.pager.pay.request.WeiXinSubmitPayRequest;
import quick.pager.pay.response.Response;

/**
 * 微信支付
 */
@Controller
public class WeiXinController {

    @Autowired
    private WeiXinPayOrderClient weiXinPayOrderClient;

    @ApiOperation("微信原生支付")
    @PostMapping("/weixin/pay/app")
    public Response payAPP(WeiXinSubmitPayRequest request){
        return null;
    }


    @ApiOperation("微信H5支付")
    @PostMapping("/weixin/pay/h5")
    public Response payH5(WeiXinSubmitPayRequest request){
        return null;
    }
}
