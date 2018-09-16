package quick.pager.pay.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import quick.pager.pay.model.common.IModel;

/**
 * meta <br />
 * t_meta表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Meta extends IModel {
    private static final long serialVersionUID = 4530054560923700428L;
    /**
     * 标题名
     */
    private String title;
    /**
     * 标题icon图标
     */
    private String icon;
    /**
     * 页面缓存控制标识
     */
    private boolean noCache;
    /**
     * 资源Id
     */
    private Long resId;
}
