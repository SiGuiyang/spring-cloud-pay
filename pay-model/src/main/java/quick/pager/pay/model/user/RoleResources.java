package quick.pager.pay.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import quick.pager.pay.model.IModel;

/**
 * 角色资源菜单<br />
 * <code>t_role_resources</code>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class RoleResources extends IModel {
    private static final long serialVersionUID = 642601889750429383L;
    /**
     * 角色Id
     */
    private Long roleId;
    /**
     * 菜单资源Id
     */
    private Long resId;

}
