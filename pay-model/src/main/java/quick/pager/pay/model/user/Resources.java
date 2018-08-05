package quick.pager.pay.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import quick.pager.pay.model.IModel;

/**
 * 菜单资源<br />
 * <code>t_resources</code>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Resources extends IModel {
    private static final long serialVersionUID = 4707800645451345014L;
    /**
     * 父级菜单资源Id
     */
    private Long parentId;
    /**
     * 菜单URL
     */
    private String path;
    /**
     * 组件标题名称
     */
    private String componentName;
    /**
     * 组件地址
     */
    private String component;
    /**
     * 重定向地址
     */
    private String redirect;
    /**
     * 权限（多个使用逗号分隔）
     */
    private String permission;
    /**
     * 类型<br />
     * 0： 路由<br />
     * 1： 按钮<br />
     */
    private Integer resourceType;
    /**
     * 序号
     */
    private Integer sequence;

}
