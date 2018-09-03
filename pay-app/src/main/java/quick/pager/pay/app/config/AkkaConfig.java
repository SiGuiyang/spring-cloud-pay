package quick.pager.pay.app.config;

import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿卡配置
 * @author siguiyang
 */
@Configuration
public class AkkaConfig {

    private static final String AKKA_SYSTEM_NAME = "pay-akka";

    @Bean
    public ActorSystem actorSystem() {
        return ActorSystem.create(AKKA_SYSTEM_NAME, ConfigFactory.load());
    }
}
