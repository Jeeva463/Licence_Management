package com.licence.microservices.service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.licence.microservices.config.Decryption;
import com.licence.microservices.dto.LicenceDto;
import com.licence.microservices.enumaration.ExpiredStatus;
import com.licence.microservices.enumaration.Status;

@Service
public class AdminMicroService {

	@Value("${licence.baseUrl}")
	String licenceBaseUrl;
	
	@Autowired
	 RestTemplate restTemplate;
	private SecretKey secretKey;
	
	//String Type--String.class
//	public String getByid(UUID id) {
//		String id1 = restTemplate.getForObject(licenceBaseUrl+"/getlicence/"+id, String.class);		
//		return id1;
//	}
	//Obj Type--getForObject
//	public LicenceDto getByid(UUID id) {
//		LicenceDto id1 = restTemplate.getForObject(licenceBaseUrl+"/getlicence/"+id, LicenceDto.class, id);		
//		return id1;
//	}
	//Use in exchange
//	public ResponseEntity<List<LicenceDto>> getdetails() {
//		ResponseEntity<List<LicenceDto>> licencedto=restTemplate.exchange(licenceBaseUrl+"/getDetails", HttpMethod.GET, null, new ParameterizedTypeReference<List<LicenceDto>>() {});
//		List<LicenceDto> dto = licencedto.getBody();
//		return ResponseEntity.ok(dto);
//	}
	//Entity Type--getForEntity
//		public ResponseEntity<LicenceDto> getByid(UUID id) {
//			ResponseEntity<LicenceDto> id1 = restTemplate.getForEntity(licenceBaseUrl+"/getlicence/"+id, LicenceDto.class, id);		
//			return id1;
//		}
	
	 public String decryptData(String data) throws Exception {
	        return Decryption.decrypt(data, secretKey);
	        
	    }
	    public void setSecretKey(String key) {
	        byte[] decodedKey = Base64.getDecoder().decode(key);
	        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	        
	    }
//		public LicenceDto getdetails(String licencekey) {
//			
//		LicenceDto id1 = restTemplate.getForObject("http://localhost:8080/api/licence/get/NBAH7O0WBTBCMC01", LicenceDto.class, licencekey);		
//		return id1;
//			
//		}
		public ResponseEntity<?> updateDetails(String licencekey) {
			ResponseEntity<LicenceDto> licenceDto = restTemplate.getForEntity(licenceBaseUrl+"/get/"+licencekey, LicenceDto.class, licencekey);
			LicenceDto licence = licenceDto.getBody();
			
			LocalDateTime activationDate =  LocalDateTime.now();
            LocalDateTime expiryDate = activationDate.plusMinutes(1);
            LocalDateTime gracePeriodEndDate = expiryDate.plusMinutes(1);
            
            LicenceDto dto = LicenceDto.builder().activationDate(activationDate.toString())
					.companyAddress(licence.getCompanyAddress()).companyName(licence.getCompanyName())
					.contactNumber(licence.getContactNumber()).expiredStatus(ExpiredStatus.NON_EXPIRED)
					.expiryDate(expiryDate.toString()).gracePeriodEndDate(gracePeriodEndDate.toString()).id(licence.getId())
					.licenceKey(licence.getLicenceKey()).emailId(licence.getEmailId()).status(Status.ACTIVE)
					.build();
            HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<LicenceDto> requestEntity = new HttpEntity<>(dto,headers);
			restTemplate.postForEntity(licenceBaseUrl+"/update", requestEntity, LicenceDto.class);
			return ResponseEntity.ok(dto);
		}

}
