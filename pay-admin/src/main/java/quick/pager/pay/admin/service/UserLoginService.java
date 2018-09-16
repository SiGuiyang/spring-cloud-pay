package quick.pager.pay.admin.service;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.dto.LoginDTO;
import quick.pager.pay.mapper.user.UserMapper;
import quick.pager.pay.model.user.User;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;
import quick.pager.pay.vo.admin.AdminVO;

import java.util.Arrays;
import java.util.UUID;

/**
 * 管理员登陆
 */
@Service
@Slf4j
public class UserLoginService implements IService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;
    @Override
    public Response doService(BaseDTO dto) {
        LoginDTO loginDto = (LoginDTO) dto;

        log.info("开始调用登录服务，用户名 username = {},登录密码 verifyCode = {}", loginDto.getUsername(), loginDto.getPassword());

        Response<AdminVO> resp = new Response<>();
        User user = userMapper.selectUserByUsernameOrMobile(loginDto.getUsername(), Constants.Common.COMMON_ZERO);
        // 用户是否存在

        if (ObjectUtils.isEmpty(user)) {
            resp.setCode(ResponseStatus.USER_NOT_EXISTS.code);
            resp.setMsg(ResponseStatus.USER_NOT_EXISTS.msg);
            return resp;
        }

        // MD5加密
        String securePassword = SecureUtil.md5(loginDto.getPassword());
        // 用户名或者密码是否一致
        if (!securePassword.equals(user.getPassword())) {
            resp.setCode(ResponseStatus.USERNAME_OR_PASSWORD_INCORRECT.code);
            resp.setMsg(ResponseStatus.USERNAME_OR_PASSWORD_INCORRECT.msg);
            return resp;
        }

        String token = UUID.randomUUID().toString();
        AdminVO vo = new AdminVO();
        vo.setAge(user.getAge());
        vo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        vo.setGender(user.getGender());
        vo.setBirthday(user.getBirthday());
        vo.setUsername(user.getUsername());
        vo.setMobile(user.getMobile());
        vo.setRoles(Arrays.asList("editor"));
        vo.setToken(token);
        resp.setCode(ResponseStatus.SUCCESS.code);
        resp.setMsg(ResponseStatus.SUCCESS.msg);
        resp.setData(vo);

        redisService.set(token, "" + user.getId(), 10 * 24 * 60 * 60);
        return resp;
    }
}
