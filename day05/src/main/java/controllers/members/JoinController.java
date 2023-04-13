package controllers.members;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import models.member.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/member/join")
@RequiredArgsConstructor // final, @NonNull 이 있는 멤버 변수 초기화 생성자
public class JoinController {

    private final JoinValidator validator;

    private final JoinService service;

    @GetMapping // /member/join
    public String join(Model model) {

        Join join = new Join();
        model.addAttribute("join", join);
        return "member/join";
    }

    @PostMapping // /member/join
    public String joinPs(@Valid Join join, Errors errors, Model model) {
        System.out.println(join);

        validator.validate(join, errors);

        if(errors.hasErrors()){
            // 에러가 있으면 처리 X -> 양식
            return "member/join";
        }

        service.join(join);

        // 성공시에는 회원 로그인
        return "redirect:/member/login";
    }
}