package quick.pager.pay.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.pay.admin.service.UserInfoService;
import quick.pager.pay.admin.service.UserLoginService;
import quick.pager.pay.admin.service.UserLogoutService;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.dto.TokenDTO;
import quick.pager.pay.dto.admin.UserDTO;
import quick.pager.pay.request.admin.UserRequest;
import quick.pager.pay.response.Response;

@RestController
@RequestMapping(Constants.ADMIN_MODULE)
@Api(description = "账户管理模块")
public class AccountController {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserLogoutService userLogoutService;

    @PostMapping("/login")
    @ApiOperation("系统用户登录管理平台")
    public Response login(UserRequest request ){

        Response resp = new Response();

        if (StringUtils.isEmpty(request.getUsername()) || StringUtils.isEmpty(request.getPassword())) {
            resp.setCode(ResponseStatus.PARAMETER_ERROR.code);
            resp.setMsg(ResponseStatus.PARAMETER_ERROR.msg);
            return resp;
        }

        UserDTO dto = new UserDTO();
        dto.setUsername(request.getUsername());
        dto.setPassword(request.getPassword());
        return userLoginService.doService(dto);
    }

    @PostMapping("/info")
    @ApiOperation("获取用户权限信息")
    public Response info(String token) {

        if (StringUtils.isEmpty(token)) {
            return new Response(ResponseStatus.PARAMETER_ERROR.code, ResponseStatus.PARAMETER_ERROR.msg);
        }

        TokenDTO dto = new TokenDTO();
        dto.setToken(token);
        return userInfoService.doService(dto);
    }


    @PostMapping("/logout")
    @ApiOperation("退出登陆")
    public Response logout(String token) {
        if (StringUtils.isEmpty(token)) {
            return new Response(ResponseStatus.PARAMETER_ERROR.code, ResponseStatus.PARAMETER_ERROR.msg);
        }

        TokenDTO dto = new TokenDTO();
        dto.setToken(token);
        return userLogoutService.doService(dto);
    }
}
