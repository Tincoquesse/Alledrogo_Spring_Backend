package pl.alledrogo.alledrogo_spring_lab.appEmail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailService implements EmailSender{

    private final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private JavaMailSender emailSender;

    public EmailService(JavaMailSender mailSender) {
        this.emailSender = mailSender;
    }


    @Override
    @Async
    public void send(String to, String email) {
    try {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(email, true);
        helper.setTo(to);
        helper.setSubject("Confirm your Email");
        helper.setFrom("kamilforex87@gmail.com");
        emailSender.send(mimeMessage);

    } catch (MessagingException e) {
        LOGGER.error("failed to send email", e);
        throw new IllegalStateException("failed to send email");
    }
    }


}
