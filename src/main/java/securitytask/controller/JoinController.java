package securitytask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import securitytask.dto.JoinDTO;
import securitytask.service.JoinService;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinP(){
        return "join"; // 회원가입 페이지 반환
    }


    // 사용자가 입력한 회원가입 데이터를 처리할 메소드
    @PostMapping("/joinProc")
    public String joinProcess(@RequestBody JoinDTO joinDTO){
        System.out.println("JoinController.joinProcess");
        System.out.println("joinDTO = " + joinDTO);


        joinService.joinProcess(joinDTO);

        return  "redirect:/login"; // 로그인페이지로 리다이렉팅
    }
}
