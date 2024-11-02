package com.mock.service;//package com.mock_claim_management.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendEmail(String toEmail, String subject, String body, String ccEmail) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("claimre.ad@gmail.com");
//        message.setTo(toEmail);
//        message.setSubject(subject);
//        message.setText(body);
//        message.setCc(ccEmail);
//
//        mailSender.send(message);
//        System.out.println("Mail Sent successfully...");
//    }
//
//    public String createET1Body(String pmName, String projectName, String staffName, String staffId, String linkToItem) {
//        return "Dear " + pmName + ",\n\n"
//                + "Claim Request for " + projectName + " " + staffName + " - " + staffId + " is submitted and\n"
//                + "waiting for approval.\n"
//                + "Please access the Claim via the following link:\n"
//                + linkToItem + "\n\n"
//                + "Sincerely,\n"
//                + "System Administrator\n"
//                + "Note: This is an auto-generated email, please do not reply.";
//    }
//}
//
