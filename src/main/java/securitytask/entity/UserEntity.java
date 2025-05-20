package securitytask.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(unique = true)
    private String username;
    private String password;

    private String role; // 로그인을 진행한 사용자에게 권한을 부여하기 위한 필드 ex. admin/user
}
