package quick.pager.pay.weixin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccessTokenDTO extends BaseDTO {
    private static final long serialVersionUID = 4314557894654624145L;
    // 授权吗
    private String code;
    // 平台订单号
    private String orderCode;


}
