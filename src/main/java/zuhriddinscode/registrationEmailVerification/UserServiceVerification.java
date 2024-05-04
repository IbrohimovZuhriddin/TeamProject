package zuhriddinscode.registrationEmailVerification;

import org.springframework.http.ResponseEntity;
import zuhriddinscode.model.UserEntity;

public interface UserServiceVerification {
    ResponseEntity<?> saveUser(UserEntity user); //verificationEmail
    ResponseEntity<?> confirmEmail(String confirmationToken); //verificationEmail
}