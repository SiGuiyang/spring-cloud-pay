package quick.pager.pay.model.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.model.common.IModel;

/**
 * 服务访问接口
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceUrl extends IModel {
    private static final long serialVersionUID = 3957190256975269928L;
    /**
     * 接口服务名称
     */
    private String interfaceName;
    /**
     * 接口服务地址
     */
    private String interfaceUrl;
    /**
     * 接口服务类型
     */
    private String interfaceType;
}
