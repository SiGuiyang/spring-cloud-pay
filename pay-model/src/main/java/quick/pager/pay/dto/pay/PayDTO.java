package quick.pager.pay.dto.pay;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.DTO;

/**
 * actor 支付数据传输对象
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class PayDTO extends DTO {
    private static final long serialVersionUID = -2323283330393364548L;

    private String merchantOrderNo;

    private String merchantNo;

    private String payAmount;
}
