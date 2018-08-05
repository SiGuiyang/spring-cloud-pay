package quick.pager.pay.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quick.pager.pay.response.Response;

/**
 * 微信H5支付
 */
@Controller
public class WeiXinH5PayController {

    @RequestMapping(value = "/weixin/pay/h5", method = RequestMethod.POST)
    public Response payH5() {
        return null;
    }
}
