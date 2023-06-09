package controllers.members;

import models.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class InfoController {
    @GetMapping("/member/info")
    public String info(Model model) { // Model 을 사용해서 정보를 가져왔다

        Member member = new Member();
        member.setUserId("user01");
        member.setUserNm("사용자01");
        member.setMobile("010-0000-0000");
        //member.setIntro("<h1>자기소개!!!</h1>");
        member.setRegDt(LocalDateTime.now());

        model.addAttribute("member", member);

        return "member/info";
    }
}
