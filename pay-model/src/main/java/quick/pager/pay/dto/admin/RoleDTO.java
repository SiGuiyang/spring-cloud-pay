package quick.pager.pay.dto.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseDTO;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDTO extends BaseDTO{
    private static final long serialVersionUID = -2248896661015410133L;

    @ApiModelProperty("角色Id")
    private Long roleId;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色代码")
    private String roleCode;
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("资源Ids")
    private List<String> resIds;
    @ApiModelProperty("取消勾选选择框")
    private List<String> noneCheckedResIds;
}
