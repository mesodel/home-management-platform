package com.unibuc.homemanagementplatform;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class JavaMailSender {

    static private JavaMailSenderImpl instance = null;

    static public JavaMailSenderImpl getInstance() {
        if (instance != null) {
            return instance;
        }

        instance = new JavaMailSenderImpl();
        instance.setHost("smtp.gmail.com");
        instance.setPort(587);

        instance.setUsername("noreply.projectjava@gmail.com");
        instance.setPassword("Parola1234!");

        Properties props = instance.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return instance;
    }
}
