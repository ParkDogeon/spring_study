package config;

import models.member.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.format.DateTimeFormatter;

@Configuration
public class AppCtx {
    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }
    @Bean
    public JoinService joinService(){
        return new JoinService();
    }
    @Bean
    public ListService listService(){
        return new ListService();
    }
    @Bean
    public InfoService infoService(){
        return new InfoService(memberDao());
    }

    @Bean
    public DateTimeFormatter dateTimeFormatter(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return formatter;
    }
}
