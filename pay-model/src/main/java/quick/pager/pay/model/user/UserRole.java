package quick.pager.pay.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import quick.pager.pay.model.IModel;

/**
 * 用户角色表<br />
 * <code>t_user_role</code>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserRole extends IModel {
    private static final long serialVersionUID = -5067677153812925416L;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 角色Id
     */
    private Long roleId;
}
