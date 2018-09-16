package quick.pager.pay.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginDTO extends BaseDTO {
    private static final long serialVersionUID = -4109231481124042751L;

    // 用户名
    private String username;
    // 手机号
    private String mobile;
    // 密码
    private String password;
    // 短信验证码
    private String verifyCode;
}
