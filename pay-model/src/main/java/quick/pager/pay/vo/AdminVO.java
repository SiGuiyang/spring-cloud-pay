package quick.pager.pay.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminVO extends VO{
    private static final long serialVersionUID = 848122814545485141L;

    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private String gender;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * token 值
     */
    private String token;

    private List<String> roles;
    /**
     * 页面权限路由
     */
    private List<MenuVO> routers;
    /**
     * 头像地址
     */
    private String avatar;
}
