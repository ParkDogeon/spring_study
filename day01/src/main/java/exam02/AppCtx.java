package exam02;

import modles.member.JoinService;
import modles.member.MemberDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {
    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }
    @Bean
    public JoinService joinService(){
        JoinService joinService = new JoinService(memberDao());

        return joinService;
    }
}
