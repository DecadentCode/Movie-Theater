package com.example.backendtheater;

import com.example.backendtheater.product.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Properties;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class BackendTheaterApplication {

	public static void main(String[] args) {

		SpringApplication.run(BackendTheaterApplication.class, args);
		Ticket testTicket = new Ticket();
		testTicket.setCurrency("USD");
		testTicket.setPrice(5);
		testTicket.setShowTime(LocalDateTime.now());
		testTicket.setUnits(1);
		testTicket.setProductionName("Cats");
		testTicket.setId(0);
		var session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
		var tx = session.beginTransaction();
		session.saveOrUpdate(testTicket);
		tx.commit();
	}

	@Bean
	public static JavaMailSender getJavaMailSender() {
		ConfigProperties configProps = new ConfigProperties();
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(configProps.getConfigValue("spring.mail.host"));
		mailSender.setPort(Integer.parseInt(Objects.requireNonNull(configProps.getConfigValue("spring.mail.port"))));
		mailSender.setUsername(configProps.getConfigValue("spring.mail.username"));
		mailSender.setPassword(configProps.getConfigValue("spring.mail.password"));
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		return mailSender;
	}
}
