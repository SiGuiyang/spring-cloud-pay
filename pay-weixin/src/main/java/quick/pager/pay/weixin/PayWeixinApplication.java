package quick.pager.pay.weixin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"quick.pager.pay.mapper.common", "quick.pager.pay.mapper.merchant", "quick.pager.pay.mapper.pay"})
public class PayWeixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayWeixinApplication.class, args);
    }
}
