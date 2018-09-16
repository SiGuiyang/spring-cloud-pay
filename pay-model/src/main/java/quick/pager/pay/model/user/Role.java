package quick.pager.pay.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import quick.pager.pay.model.common.IModel;

/**
 * 角色<br />
 * <code>t_role</code>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Role extends IModel {
    private static final long serialVersionUID = -4660108573184137852L;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色代码
     */
    private String roleCode;
    /**
     * 状态<br />
     * 0： 禁用<br />
     * 1：启用<br />
     */
    private Integer serverStatus;
    /**
     * 备注
     */
    private String remark;

}
