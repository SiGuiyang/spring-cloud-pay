package quick.pager.pay.weixin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

/**
 * 退款服务
 *
 * @author siguiyang
 */
@Service
@Slf4j
public class WeChatRefundService implements IService {

    @Override
    public Response doService(BaseDTO dto) {
        return new Response();
    }
}
