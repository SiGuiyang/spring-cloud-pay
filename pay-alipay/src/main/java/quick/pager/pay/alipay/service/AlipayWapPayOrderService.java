package quick.pager.pay.alipay.service;

import org.springframework.stereotype.Service;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

/**
 * 支付宝WAP支付
 */
@Service
public class AlipayWapPayOrderService implements IService{
    @Override
    public Response doService(BaseDTO dto) {
        return null;
    }
}
