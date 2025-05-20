package securitytask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // SpringBoot 에 Config 클래스로 등록
@EnableWebSecurity // 해당 클래스를 Security 로 관리
public class SecurityConfig { // SpringSecurity 를 위한 인가, 검증을 커스텀하기 위한 클래스

    // 비크립트 패스워드 인코드 리턴 메소드
    // Spring Security 는 사용자인증(로그인) 시 비밀번호에 대한 단방향 해시 암호화를 진행하여 저장되어 잇은 비밀번호와 대조함
    // -> 회원가입 시 비밀번호 항목에 대해 암호화를 진행해야함
    // Spring Security 는 암호화를 위해 Bcrypt Password Encoder 를 제공,권장함
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{ // 예외 처리 필수!

        http.authorizeHttpRequests((auth) -> auth// 특정 경로에 따라 요청 허용 또는 거부
                .requestMatchers("/", "/login", "lgoinProc", "/join", "/joinProc").permitAll() //.permitAll() : 모든 사용자 접근 가능
                .requestMatchers("/admin").hasRole("ADMIN") // .hasRole() : 지정한 롤을 가진 사용자만 접근 가능
                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") // ** : 와일드 카드 (특정사용자의 마이페이지)
                .anyRequest().authenticated() // .authenticated() : 로그인 후 모든 사요자 접근 가능
        );

        // 로그인 페이지 설정 (로그인하지 않은 상태로 "/admin" 접근 시 로그인 페이지로 이동됨)
        http.formLogin((auth) -> auth.loginPage("/login")
                .loginProcessingUrl("/loginProc") // .loginProcessingUrl() : html 로그인 페이지에서 입력한 정보를 지정한 경로로 보냄
                .permitAll() // 모두 접근 가능
        );

        // 토큰 일시로 끄기
        http.csrf((auth) -> auth.disable());
        return http.build(); // 매개변수로 받은 HttpSecurity 반환 (경로에 대한 접근 권한 설정)
    }
}
