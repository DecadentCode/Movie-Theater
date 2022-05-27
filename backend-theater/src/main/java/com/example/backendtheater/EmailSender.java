package com.example.backendtheater;

import com.example.backendtheater.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Objects;

@PropertySource("classpath:application.properties")
@Component
public class EmailSender {

    private User userToConfirm;
    @Autowired
    private Environment env;
    @Autowired
    private final JavaMailSender emailSender = BackendTheaterApplication.getJavaMailSender();

    public EmailSender(User userToConfirm) {
        this.userToConfirm = userToConfirm;
    }

    public void sendConfirmation() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        message.setTo(userToConfirm.getEmail());
        message.setSubject("Genspark Movies Email Confirmation");
        message.setText(String.format("Welcome %s!\nThank you for using GenSpark-Movies! Please visit the following link" +
                " to confirm your email address. http://localhost:8080/confirm_email/%d%n", userToConfirm.getFirstName(), userToConfirm.getId()));
        emailSender.send(message);
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    public User getUserToConfirm() {
        return userToConfirm;
    }

    public void setUserToConfirm(User userToConfirm) {
        this.userToConfirm = userToConfirm;
    }
}
