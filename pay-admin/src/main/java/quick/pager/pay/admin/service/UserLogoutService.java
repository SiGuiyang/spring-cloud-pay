package quick.pager.pay.admin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quick.pager.pay.dto.BaseDto;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

/**
 * 管理员退出登陆
 */
@Service
@Slf4j
public class UserLogoutService implements IService {
    @Override
    public Response doService(BaseDto dto) {
        return null;
    }
}
