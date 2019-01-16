package quick.pager.pay.app.properties;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 支付方法映射
 *
 * @author siguiyang
 */
@ConfigurationProperties("pay.channel")
@Component
@Data
public class PayProperties {

    private Map<String, String> methods;
}
