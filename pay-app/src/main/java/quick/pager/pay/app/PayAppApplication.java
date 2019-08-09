package quick.pager.pay.app;

import java.util.List;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringCloudApplication
@MapperScan(basePackages={"quick.pager.pay.app.mapper"})
public class PayAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PayAppApplication.class, args);
	}


	@Autowired
	private DiscoveryClient discoveryClient;


	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		RestTemplateBuilder builder = new RestTemplateBuilder();

		return builder.build();
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> services = discoveryClient.getServices();

		System.out.println("===========================");
		System.out.println(services);
	}
}
