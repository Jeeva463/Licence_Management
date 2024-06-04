package com.example.licence.dto;

import lombok.Data;

@Data
public class DataResponse {
	 private String encryptedLicenseKey;
	    private String secretKey;

	    public DataResponse(String encryptedLicenseKey, String secretKey) {
	        this.encryptedLicenseKey = encryptedLicenseKey;
	        this.secretKey = secretKey;
	    }

}
