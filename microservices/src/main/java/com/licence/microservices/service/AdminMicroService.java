package com.licence.microservices.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.licence.microservices.config.Decryption;
import com.licence.microservices.dto.LicenceDto;


@Service
public class AdminMicroService {
//
//	@Value("${licence.baseUrl}")
//	String licenceBaseUrl;
	
	@Autowired
	 RestTemplate restTemplate;
	private SecretKey secretKey;
	
//	//String Type--String.class
//	public String getByid(UUID id) {
//		String id1 = restTemplate.getForObject("http://localhost:8080/api/licence/getlicence/aa909b93-de62-43f4-a3ee-ffae10675c09", String.class);		
//		return id1;
//	}
	//Obj Type--getForObject
//	public LicenceDto getByid(UUID id) {
//		LicenceDto id1 = restTemplate.getForObject("http://localhost:8080/api/licence/getlicence/aa909b93-de62-43f4-a3ee-ffae10675c09", LicenceDto.class, id);		
//		return id1;
//	}
	//Use in exchange
//	public ResponseEntity<List<LicenceDto>> getdetails() {
//		ResponseEntity<List<LicenceDto>> licencedto=restTemplate.exchange("http://localhost:8080/api/licence/getall", HttpMethod.GET, null, new ParameterizedTypeReference<List<LicenceDto>>() {});
//		return licencedto;
//		
//	}
//	//Entity Type--getForEntity
//		public ResponseEntity<LicenceDto> getByid(UUID id) {
//			ResponseEntity<LicenceDto> id1 = restTemplate.getForEntity("http://localhost:8080/api/licence/getlicence/aa909b93-de62-43f4-a3ee-ffae10675c09", LicenceDto.class, id);		
//			return id1;
//		}
	
	 public String decryptData(String data) throws Exception {
	        return Decryption.decrypt(data, secretKey);
	        
	    }
	    public void setSecretKey(String key) {
	        byte[] decodedKey = Base64.getDecoder().decode(key);
	        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	        
	    }
		public LicenceDto getdetails(String licencekey) {
			
		LicenceDto id1 = restTemplate.getForObject("http://localhost:8080/api/licence/get/NBAH7O0WBTBCMC01", LicenceDto.class, licencekey);		
		return id1;
			
		}

}
