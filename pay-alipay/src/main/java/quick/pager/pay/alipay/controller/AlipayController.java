package quick.pager.pay.alipay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.pay.response.Response;

/**
 * controller
 *
 * @author siguiyang
 */
@RestController
public class AlipayController {


    @RequestMapping(value = "/alipay/app", method = RequestMethod.POST)
    public Response payAPP() {

        return null;
    }


    @RequestMapping(value = "/pay/wap", method = RequestMethod.POST)
    public Response payWap() {
        return null;
    }
}
