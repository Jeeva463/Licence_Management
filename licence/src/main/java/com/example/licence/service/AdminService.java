package com.example.licence.service;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.licence.encryptionutil.EncryptionDecryption;
import com.example.licence.repository.RepositoryLicence;

@Service
public class AdminService {
	
	@Autowired
	RepositoryLicence repositoryLicence;
	private SecretKey secretKey;
	
	 public String decryptData(String data) throws Exception {
	        return EncryptionDecryption.decrypt(data, secretKey);
	    }
	    public void setSecretKey(String key) {
	        byte[] decodedKey = Base64.getDecoder().decode(key);
	        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	    }

}
