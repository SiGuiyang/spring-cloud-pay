package quick.pager.pay.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统配置对象<br />
 * <code>t_system_config</code>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class SystemConfig extends IModel {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8783914463426720407L;
    /**
     * 配置项名称
     */
    private String configName;
    /**
     * 配置项值
     */
    private String configValue;
    /**
     * 状态<br />
     * 0： 禁用<br />
     * 1：启用<br />
     */
    private Integer serverStatus;
    /**
     * 配置项说明
     */
    private String description;
    /**
     * 序号
     */
    private Integer sequence;
    /**
     * 0：不存在重复的key <br />
     * 1：存在重复的key <br />
     */
    private Integer repeatType;
    /**
     * 创建时的版本
     */
    private String version;
}
