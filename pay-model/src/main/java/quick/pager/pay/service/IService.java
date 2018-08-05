package quick.pager.pay.service;

import quick.pager.pay.dto.BaseDto;
import quick.pager.pay.response.Response;

/**
 * spring service 业务层顶级接口<br />
 * 不建议开发人员定义其它接口，并且每一个接口只允许存在一种业务<br />
 * 接口的名称必须表明此接口的详细意义<br />
 *
 * @author siguiyang
 */
public interface IService {

    /**
     * 所有service都必须实现此接口
     *
     * @param dto 业务数据传输对象
     */
    Response doService(BaseDto dto);
}
