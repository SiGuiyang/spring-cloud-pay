package quick.pager.pay.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringCloudApplication
@EnableZuulProxy
public class PayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayZuulApplication.class, args);
	}
}
