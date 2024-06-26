package com.example.licence.dto;

import lombok.Data;

@Data
public class EmailStructure {
	
	 String toEmail;
	 String body;
	 String subject;
     String attachment;
	 String cc;
	 String bcc; 

}
