package quick.pager.pay.request.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.request.Request;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRequest extends Request {
    private static final long serialVersionUID = -9163566801405925625L;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("性别")
    private String gender;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("生日")
    private String birthday;
    @ApiModelProperty("角色Id")
    private Long roleId;
}
