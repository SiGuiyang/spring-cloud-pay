package quick.pager.pay.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringCloudApplication
@EnableFeignClients
@MapperScan(basePackages = {"quick.pager.pay.app.mapper"})
public class PayAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayAppApplication.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();

        return builder.build();
    }
}
