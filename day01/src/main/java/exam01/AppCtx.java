package exam01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {
    @Bean
    public Hello hello(){
        Hello hello = new Hello();
        return hello;
    }
}
