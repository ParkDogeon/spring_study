package controllers.members;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class Join {
    @NotBlank(message = "아이디를 입력하세요")
    @Size(min=6, max=16)
    private String userId;
    @NotBlank(message = "비밀번호를 입력하세요")
    @Size(min=8, max=16)
    private String userPw;
    @NotBlank(message = "비밀번호를 확인하세요")
    private String userPwRe;
    @NotBlank(message = "이름을 입력하세요")
    private String userNm;
    @Email
    private String email;
    @AssertTrue(message = "동의해주세요")
    private boolean agree;
}
