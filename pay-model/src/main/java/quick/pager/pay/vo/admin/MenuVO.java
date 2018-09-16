package quick.pager.pay.vo.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.vo.VO;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class MenuVO extends VO {
    private static final long serialVersionUID = 2938727279251690362L;


    /**
     * 路径
     */
    private String path;
    /**
     * 名称
     */
    private String name;
    /**
     * 适配页面权限名称
     */
    private String label;
    /**
     * 组件
     */
    private String component;

    /**
     * 隐藏的标识
     */
    private boolean hidden;
    /**
     * 权限
     */
    private List<String> permission;

    private List<MenuVO> children;
}
