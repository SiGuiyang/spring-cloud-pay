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
import quick.pager.pay.dto.TokenDto;
import quick.pager.pay.dto.admin.UserDto;
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

//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, SecureUtil.md5(password));
//        try {
//            subject.login(usernamePasswordToken);
//            String token = subject.getSession().getId().toString();
//
//            User user = userService.getUser(username,Constants.Common.COMMON_ZERO);
//            UserEvent event = new UserEvent();
//            event.setAge(user.getAge());
//            event.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//            event.setGender(user.getGender());
//            event.setBirthday(user.getBirthday());
//            event.setUsername(user.getUsername());
//            event.setMobile(user.getMobile());
//            event.setRoles(Arrays.asList("editor"));
//            event.setToken(token);
//            resp.setData(event);
//        } catch (Exception e) {
//            if (e instanceof UnknownAccountException) {
//                resp.setCode(RespConstants.USER_NOT_EXISTS.code);
//                resp.setMsg(RespConstants.USER_NOT_EXISTS.msg);
//                return resp;
//            } else if (e instanceof IncorrectCredentialsException) {
//                resp.setCode(RespConstants.USERNAME_OR_PASSWORD_INCORRECT.code);
//                resp.setMsg(RespConstants.USERNAME_OR_PASSWORD_INCORRECT.msg);
//                return resp;
//            } else {
//                resp.setCode(RespConstants.SYSTEM_ERROR.code);
//                resp.setMsg(RespConstants.SYSTEM_ERROR.msg);
//                return resp;
//            }
//        }
//        return resp;
        UserDto dto = new UserDto();
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

        TokenDto dto = new TokenDto();
        dto.setToken(token);
        return userInfoService.doService(dto);
    }


    @PostMapping("/logout")
    @ApiOperation("退出登陆")
    public Response logout() {
        return new Response();
    }
}
