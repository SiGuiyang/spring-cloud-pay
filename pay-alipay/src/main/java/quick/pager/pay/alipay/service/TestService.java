package quick.pager.pay.alipay.service;

import org.springframework.stereotype.Service;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.dto.actor.TestDTO;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

/**
 * 测试服务
 * @author siguiyang
 */
@Service
public class TestService implements IService {
    @Override
    public Response doService(BaseDTO dto) {
        TestDTO testDto = (TestDTO) dto;
        Response<TestDTO> response = new Response<>();
        response.setData(testDto);
        return response;
    }
}
