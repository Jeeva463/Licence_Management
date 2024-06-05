package com.example.licence.dto;

import lombok.Data;

@Data
public class DataResponse {
	 private String encryptedLicenseKey;
	 private String encryptedemailId;
	    private String secretKey;
	    String data = encryptedLicenseKey+encryptedemailId;

	    public DataResponse(String data, String secretKey) {
	        //this.encryptedLicenseKey = encryptedLicenseKey;
	        this.secretKey = secretKey;
	        //this.encryptedemailId = encryptedemailId;
	        this.data = data;
	    }
	    
	    
}
