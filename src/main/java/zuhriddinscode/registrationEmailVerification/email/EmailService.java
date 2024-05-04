package zuhriddinscode.registrationEmailVerification.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
//@Component
public class EmailService  {
    private final JavaMailSender mailSender;
    public EmailService(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }
    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }
}