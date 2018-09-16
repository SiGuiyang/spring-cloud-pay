package quick.pager.pay.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TokenDTO extends BaseDTO{
    private static final long serialVersionUID = 4782145297544126046L;

    private String token;
}
