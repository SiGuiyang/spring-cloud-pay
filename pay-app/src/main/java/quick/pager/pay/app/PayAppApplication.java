package quick.pager.pay.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@MapperScan(basePackages={"quick.pager.pay.mapper.common","quick.pager.pay.mapper.merchant","quick.pager.pay.mapper.pay"})
public class PayAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayAppApplication.class, args);
	}
}
