package securitytask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import securitytask.dto.JoinDTO;
import securitytask.entity.UserEntity;
import securitytask.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입 메소드
    public void joinProcess(JoinDTO joinDTO){

        // DB 에 동일한 username 을 가진 회원이 존재하는지 검증
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if(isUser){
            return;
        }

        UserEntity data = new UserEntity();
        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword())); // 비밀번호 암호화
        data.setRole("ROLE_ADMIN"); // 롤 부여


        userRepository.save(data);
    }

}
