package com.licence.microservices.service;

import java.util.Base64;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AdminMicroService {
//
//	@Value("${licence.baseUrl}")
//	String licenceBaseUrl;
	
	@Autowired
	 RestTemplate restTemplate;
	private SecretKey secretKey;
	
	//String Type--String.class
	public String getByid(UUID id) {
		String id1 = restTemplate.getForObject("http://localhost:8080/api/licence/getlicence/aa909b93-de62-43f4-a3ee-ffae10675c09", String.class);		
		return id1;
	}
	/*//Obj Type--Licence.class
	public Licence getByid(UUID id) {
		Licence id1 = restTemplate.getForObject("http://localhost:8080/api/licence/getlicence/aa909b93-de62-43f4-a3ee-ffae10675c09", Licence.class, id);		
		return id1;
	}*/
	 public String decryptData(String data) throws Exception {
	        return EncryptionDecryption.decrypt(data, secretKey);
	        
	    }
	    public void setSecretKey(String key) {
	        byte[] decodedKey = Base64.getDecoder().decode(key);
	        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	        
	    }

}
