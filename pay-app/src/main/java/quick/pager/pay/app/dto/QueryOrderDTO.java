package quick.pager.pay.app.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.DTO;

/**
 * actor 查询订单数据传输对象
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class QueryOrderDTO extends DTO {
    private static final long serialVersionUID = 1889549616156203070L;
}
