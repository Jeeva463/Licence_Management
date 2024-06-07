package com.example.licence.service;

import java.time.LocalDateTime;
import java.util.Base64;
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
	
	 public String decryptData(String data) throws Exception {
	        return EncryptionDecryption.decrypt(data, secretKey);
	        
	    }
	    public void setSecretKey(String key) {
	        byte[] decodedKey = Base64.getDecoder().decode(key);
	        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	        
	    }
	    public Licence getdetails(String licenceKey) {
			return repositoryLicence.findBylicenceKey(licenceKey);
			
		}
		public Licence putDetails(UUID id) {
			Optional<Licence> obj = repositoryLicence.findById(id);
			Licence obj1 = obj.get();
			
			LocalDateTime activationDate =  LocalDateTime.now();
            LocalDateTime expiryDate = activationDate.minusYears(-1);
            LocalDateTime gracePeriodEndDate = expiryDate.minusDays(-15);
			
			obj1.setStatus(Status.ACTIVE);
			obj1.setExpiredStatus(ExpiredStatus.NON_EXPIRED);
			obj1.setActivationDate(activationDate.toString());
			obj1.setExpiryDate(expiryDate.toString());
			obj1.setGracePeriodEndDate(gracePeriodEndDate.toString());
			return repositoryLicence.save(obj1);
			
		}
		

}
