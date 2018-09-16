package quick.pager.pay.request.pay;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 查询订单请求对象
 */
@Data
@Builder
@RequiredArgsConstructor
public class QueryOrderRequest implements Serializable {
    private static final long serialVersionUID = 4047768104321158669L;
}
