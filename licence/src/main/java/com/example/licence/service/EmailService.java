package com.example.licence.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.licence.dto.EmailStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Value("${spring.mail.username}")
	String fromMail;
	
	@Autowired
	JavaMailSender javaMailSender;//indha class mail sent panna and extendsla SimpleMailMessage edhu irukku
	
	public void getMail(String toEmail, String subject, String body) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();//indha class adhula irukkira field set panna

		mailMessage.setTo(toEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(body);
		javaMailSender.send(mailMessage);
			
	}

	public void postMail(String mail, EmailStructure emailStructure) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromMail);
		message.setSubject(emailStructure.getSubject());
		message.setText(emailStructure.getBody());
		message.setBcc(emailStructure.getBcc());
		message.setCc(emailStructure.getCc());
		message.setTo(mail);
		javaMailSender.send(message);
	}

//	public void post(EmailStructure emailStructure) {
//		MimeMessageHelper helper = new MimeMessageHelper(message, true);
//		helper.setFrom(fromMail);
//		helper.setTo(emailStructure.getToEmail());
//		helper.setSubject(emailStructure.getSubject());
//		helper.setText(emailStructure.getBody());
//		//MimeMessage message = javaMailSender.createMimeMessage(); 
//		
//		FileSystemResource fileSystemResource = new FileSystemResource(new File(emailStructure.getAttachment()));
//
//		helper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
//
//		javaMailSender.send(message);
//		
//	}
	public void post(String toEmail, String subject, String text, String attachment) {
	    MimeMessage message = javaMailSender.createMimeMessage();

	    try {
	        // Create the MimeMessageHelper with multipart support
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        helper.setFrom(fromMail);
	        helper.setTo(toEmail);
	        helper.setSubject(subject);
	        helper.setText(text);

	        // Add an attachment
	        FileSystemResource file = new FileSystemResource(new File(attachment));
	        helper.addAttachment(file.getFilename(), file);

	        javaMailSender.send(message);
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        // Handle exception
	    }
	}


}
