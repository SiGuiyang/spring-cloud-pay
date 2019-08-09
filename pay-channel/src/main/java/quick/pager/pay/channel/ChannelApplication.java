package quick.pager.pay.channel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringCloudApplication
@MapperScan(basePackages={"quick.pager.pay.channel.mapper"})
public class ChannelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChannelApplication.class, args);
	}


	@Bean
	public RestTemplate restTemplate(){
		RestTemplateBuilder builder = new RestTemplateBuilder();

		return builder.build();
	}
}
