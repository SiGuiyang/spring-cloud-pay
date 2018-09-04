package quick.pager.pay.request;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 支付请求对象
 */
@Data
@Builder
@RequiredArgsConstructor
public class PayRequest implements Serializable {
    private static final long serialVersionUID = 59698744732748652L;
}
