package quick.pager.pay.dto.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends BaseDto{
    private static final long serialVersionUID = 802583718198644652L;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
}
