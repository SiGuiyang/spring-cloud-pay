package quick.pager.pay.dto.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseDto;

/**
 * 公众号授权码
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MpCodeDto extends BaseDto {
    private static final long serialVersionUID = 2863343284201321086L;
}
