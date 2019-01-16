package quick.pager.pay.app.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseDTO;

/**
 * 支付渠道dto
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ChannelDTO extends BaseDTO {
    private static final long serialVersionUID = 8023643524327483948L;
}
