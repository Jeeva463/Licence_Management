package com.example.licence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.licence.dto.EmailStructure;
import com.example.licence.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/mail")
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/sendMail")
	
	public String getMail(@RequestParam String toEmail,@RequestParam String subject,@RequestParam String body) {
		emailService.getMail(toEmail,subject,body);
		return "Mail sent";
	}
	@PostMapping("/mail")
	
	public String postMail(@RequestParam String mail,@RequestBody  EmailStructure emailStructure) {
		emailService.postMail(mail,emailStructure);
		return "Send a MaiL";
	}
	@PostMapping("/send")
	
	public String post(@RequestParam String toEmail,@RequestParam String subject,@RequestParam String text,@RequestParam String attachment) throws MessagingException {
		emailService.post(toEmail,subject,text,attachment);
		return "Send a mail";
	}

}
