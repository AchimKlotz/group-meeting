package org.groupevents.configuration;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EMailConfiguration {
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("localhost");
	    mailSender.setPort(25);	     
	    Properties props = mailSender.getJavaMailProperties();	    
	    props.put("mail.debug", "true");	     
	    return mailSender;
	}
}
