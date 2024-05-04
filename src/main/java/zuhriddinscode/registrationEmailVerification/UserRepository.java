package zuhriddinscode.registrationEmailVerification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zuhriddinscode.model.UserEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

        List<UserEntity> findByUserName(String user_name);

        Optional<UserEntity> findByUsername (String username);// garatjon, minusD
        boolean existsByUserName(String username);//minusD
        boolean existsByEmail( String userEmail);//minusD


        UserEntity findByUserEmailIgnoreCase(String emailId);
        Boolean existsByUserEmail(String email);

}