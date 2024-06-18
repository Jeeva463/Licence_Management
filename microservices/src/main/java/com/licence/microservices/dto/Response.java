package com.licence.microservices.dto;

import lombok.Data;

@Data
public class Response {
	
	private String data;
	private String secretKey;
	//public String licencekey;
	
	public Response(String data,String secretKey) {
		this.data = data;
		this.secretKey = secretKey;
		
	}

}
