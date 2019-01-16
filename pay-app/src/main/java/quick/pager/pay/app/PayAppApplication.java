package quick.pager.pay.app;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
//@MapperScan(basePackages={"quick.pager.pay.mapper.common","quick.pager.pay.mapper.merchant","quick.pager.pay.mapper.pay"})
public class PayAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayAppApplication.class, args);
	}


	@Bean
	public RestTemplate restTemplate(){
		RestTemplateBuilder builder = new RestTemplateBuilder();

		return builder.build();
	}
}
