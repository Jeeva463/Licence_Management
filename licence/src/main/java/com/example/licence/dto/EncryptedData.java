package com.example.licence.dto;

import lombok.Data;

@Data
public class EncryptedData {
	String encryptedlicenseKey;
	String encryptedemailId;
	
	public EncryptedData(String encryptedlicenseKey,String encryptedemailId) {
		this.encryptedlicenseKey = encryptedlicenseKey;
		this.encryptedemailId = encryptedemailId;
	}

}
