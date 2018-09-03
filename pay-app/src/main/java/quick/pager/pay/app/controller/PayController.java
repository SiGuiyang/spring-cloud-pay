package quick.pager.pay.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.async.DeferredResult;
import quick.pager.pay.request.PayRequest;
import quick.pager.pay.request.QueryOrderRequest;
import quick.pager.pay.request.RefundRequest;

/**
 * 支付控制器<br />
 * 此接口对外暴露<br />
 *
 * @author siguiyang
 */
@Controller
public class PayController {

    /**
     * 支付接口
     */
    public DeferredResult pay(@RequestBody PayRequest request) {
        return null;
    }

    /**
     * 退款接口
     */
    public DeferredResult refund(@RequestBody RefundRequest request) {
        return null;
    }

    /**
     * 查询订单
     */
    public DeferredResult queryOrder(@RequestBody QueryOrderRequest request) {
        return null;
    }
}
