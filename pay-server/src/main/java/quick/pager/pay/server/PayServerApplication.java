package quick.pager.pay.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayServerApplication.class, args);
	}
}
