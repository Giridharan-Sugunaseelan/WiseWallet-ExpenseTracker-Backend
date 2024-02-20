package com.project.financial_tracker.service.implementation;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

//    @Value("spring.mail.host")
//    private String host;
//    @Value("spring.mail.userName")
//    private String userName;
//    @Value("spring.mail.password")
//    private String password;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("slbg.giridharan@gmail.com");
        mailSender.setPassword("igaavdpooljzseds");

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.transport.protocol", "smtp");
        return mailSender;
    }

    public void sendResetPasswordMail(String to, String mailSubject, String mailBody) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(mailSubject);
        mail.setText(mailBody);
        mailSender().send(mail);
    }

}
