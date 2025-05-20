package securitytask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securitytask.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    boolean existsByUsername(String username);
}
