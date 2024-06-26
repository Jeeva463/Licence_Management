//package com.example.licence.common;
//
//import java.io.File;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//
//@Service
//public class SimpleMailSender {
//	
//	@Autowired
//	JavaMailSender javaMailSender;//SimpleMailMessage ii javaMailSender indha class extends sent panradhanala edhai use panrom 
//	public void MimeMessageSend(String toEmail, String subject, String body, MimeMessage message,String attachment)
//			throws MessagingException {
//		MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//		helper.setTo(toEmail);
//		helper.setSubject(subject);
//		helper.setText(body);
//		FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
//
//		helper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
//
//		javaMailSender.send(message);
//	}
//}
