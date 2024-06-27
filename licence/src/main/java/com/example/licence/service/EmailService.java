package com.example.licence.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
	public void post(String toEmail, String subject, String text, String attachment) throws MessagingException {
	    MimeMessage message = javaMailSender.createMimeMessage();//message-இது ஒரு MimeMessage பொருள், இது நீங்கள் 
	                                                             //உருவாக்கும் மின்னஞ்சல் செய்தியைக் குறிக்கிறது.

	        // Create the MimeMessageHelper with multipart support
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        helper.setFrom(fromMail);
	        helper.setTo(toEmail);
	        helper.setSubject(subject);
	        helper.setText(text,true);// true indicates the text is HTML

	        // Add an attachment
	        FileSystemResource filesystem = new FileSystemResource(new File(attachment));
	        helper.addAttachment(attachment, filesystem);
//C:/Users/jeeva/OneDrive/Pictures/Licence_Management/licence/Mail.Attachment/Image.jpeg
//image irukkura place path and image name and image type @RequestParam la send pannanum(/ ipdi irukkanum)
	        javaMailSender.send(message);
	}


}
