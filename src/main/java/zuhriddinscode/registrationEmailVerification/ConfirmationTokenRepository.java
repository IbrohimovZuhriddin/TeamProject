package zuhriddinscode.registrationEmailVerification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zuhriddinscode.registrationEmailVerification.ConfirmationToken;

@Repository("confirmationTokenRepository")
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);

}