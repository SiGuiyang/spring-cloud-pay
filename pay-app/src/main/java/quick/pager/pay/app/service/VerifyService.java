package quick.pager.pay.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quick.pager.pay.dto.BaseDto;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

/**
 * 验证签名
 *
 * @author siguiyang
 */
@Service
@Slf4j
public class VerifyService implements IService {

    @Override
    public Response doService(BaseDto dto) {
        return null;
    }
}
