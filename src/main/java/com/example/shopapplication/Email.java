package com.example.shopapplication;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Email {
    final String senderUsername = "michaelfaho@gmail.com";
    final String senderPassword = "lywldvxdynmqqoja";

    public Email(String recipientEmail, String messageText) {
        // SMTP server properties
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");

        // Create a Session object to authenticate the sender
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderUsername, senderPassword);
            }
        });

        try {
            // Create a MimeMessage object for the email
            Message message = new MimeMessage(session);

            // Set the sender, recipient, subject, and message body
            message.setFrom(new InternetAddress(senderUsername));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Shop application");
            message.setText(messageText);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}