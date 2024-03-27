package com.ReviewCollection.review.service;

import java.util.HashMap;
import java.util.Map;

public class EmailService {

    private final String username;
    private final String password;
    private Map<String, String> otpMap = new HashMap<>();

    public EmailService(String username, String password) {
        this.username = username;
        this.password = password;
    }

//    public boolean sendOTP(String email, String otp) {
//        String subject = "Your OTP";
//        String messageBody = "Your OTP is: " + otp;
//        return sendEmail(email, subject, messageBody);
//    }

//    public boolean sendCredentials(String email, String username, String password) {
//        String subject = "Your Credentials";
//        String messageBody = "Your username: " + username + "\nYour password: " + password;
//        return sendEmail(email, subject, messageBody);
//    }
public String getStoredOTP(String email) {
    // Retrieve the stored OTP for the given email address from the map
    return otpMap.get(email);
}


//    private boolean sendEmail(String recipient, String subject, String messageBody) {
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
//            message.setSubject(subject);
//            message.setText(messageBody);
//
//            Transport.send(message);
//            return true;
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}