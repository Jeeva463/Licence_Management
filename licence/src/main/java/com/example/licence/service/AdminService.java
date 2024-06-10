package com.example.licence.service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.licence.encryptionutil.EncryptionDecryption;
import com.example.licence.entity.Licence;
import com.example.licence.enumaration.ExpiredStatus;
import com.example.licence.enumaration.Status;
import com.example.licence.repository.RepositoryLicence;

@Service
public class AdminService {
	
	@Autowired
	RepositoryLicence repositoryLicence;
	
	private SecretKey secretKey;
//	private Object expiredStatus;
//	private Object expiryDate;
//	private Object activationDate;
//	private Object gracePeriodEndDate;
	
	 public String decryptData(String data) throws Exception {
	        return EncryptionDecryption.decrypt(data, secretKey);
	        
	    }
	    public void setSecretKey(String key) {
	        byte[] decodedKey = Base64.getDecoder().decode(key);
	        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	        
	    }
	    public Map getdetails(String licenceKey) {
	    	Optional<Licence> licence = repositoryLicence.findBylicenceKey(licenceKey);
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("time", LocalDateTime.now());
	    	map.put("Licence", licence);
			return map;
			
		}
	    
		public Licence putDetails(UUID id) {
			Optional<Licence> obj = repositoryLicence.findById(id);
			Licence obj1 = obj.get();
			
			LocalDateTime activationDate =  LocalDateTime.now();
            LocalDateTime expiryDate = activationDate.plusMinutes(1);
            LocalDateTime gracePeriodEndDate = expiryDate.plusMinutes(1);               			
			obj1.setStatus(Status.ACTIVE);
			obj1.setExpiredStatus(ExpiredStatus.NON_EXPIRED);
			obj1.setActivationDate(activationDate.toString());
			obj1.setExpiryDate(expiryDate.toString());
			obj1.setGracePeriodEndDate(gracePeriodEndDate.toString());
			return repositoryLicence.save(obj1);
		}
}