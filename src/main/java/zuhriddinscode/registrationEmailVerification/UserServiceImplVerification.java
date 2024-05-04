package zuhriddinscode.registrationEmailVerification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import zuhriddinscode.model.UserEntity;
import zuhriddinscode.registrationEmailVerification.email.EmailService;

public class UserServiceImplVerification implements UserServiceVerification {

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    EmailService emailService;

    @Override
    public ResponseEntity<?> saveUser(UserEntity user) {

        if (userRepository.existsByUserEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8989/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository
                .findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            UserEntity user = userRepository.findByUserEmailIgnoreCase(
                    token.getUserEntity().getEmail());
            user.setIsActive(true);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }
}