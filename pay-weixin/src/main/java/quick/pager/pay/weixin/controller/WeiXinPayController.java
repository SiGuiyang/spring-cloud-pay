package quick.pager.pay.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quick.pager.pay.response.Response;

import java.security.PublicKey;

/**
 * 微信支付
 */
@Controller
public class WeiXinPayController {


    @RequestMapping(value = "/weixin/pay/app",method = RequestMethod.POST)
    public Response pay(){
        return null;
    }
}
