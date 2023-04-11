package config;

import config.ProxyCalculator2;
import exam02.Calculator;
import exam02.ImplCalculator;
import exam02.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
//@EnableAspectJAutoProxy(proxyTargetClass = true) // 자동 프록시 생성
@EnableAspectJAutoProxy
public class AppCtx {

    @Bean
    public Calculator calculator(){

        return new RecCalculator();
    }

    @Bean
    public  ProxyCalculator2 proxyCalculator2(){

        return new ProxyCalculator2();
    }

    @Bean
    public CommonPointcut commonPointcut(){
        return new CommonPointcut();
    }


    /*
    @Bean
    public Calculator calculator(){
        return new RecCalculator();
    }

    @Bean
    public ImplCalculator calculato2(){
        return new ImplCalculator();
    }

    @Bean
    public ProxyCalculator2 proxyCalculator2(){

        return new ProxyCalculator2();
    }
    */
}
