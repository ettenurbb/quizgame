package com.quizgame.quizgame.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String token) {
        String subject = "Подтверждение email";
        String link = "http://localhost:8080/api/users/verify?token=" + token;
        String htmlMsg = "<h2>Подтвердите регистрацию</h2><br>" +
                "<a href=\"" + link + "\">Нажмите здесь для подтверждения email</a>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        try {
            helper.setText(htmlMsg, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("noreply@quizgame.com");
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Ошибка при отправке email", e);
        }
    }

    public void resendVerificationEmail(String to, String token) {
        String subject = "Повторное подтверждение email";
        String link = "http://localhost:8080/api/users/verify?token=" + token;
        String htmlMsg = "<h2>Подтвердите ваш email</h2><br>" +
                "<p>Вы запросили повторную отправку ссылки подтверждения.</p>" +
                "<a href=\"" + link + "\">Нажмите здесь для подтверждения email</a>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        try {
            helper.setText(htmlMsg, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("noreply@quizgame.com");
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Ошибка при повторной отправке email", e);
        }
    }
}