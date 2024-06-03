package com.example.licence.service;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.licence.encryptionutil.EncryptionDecryption;
import com.example.licence.entity.Licence;
import com.example.licence.enumaration.Status;
import com.example.licence.repository.RepositoryLicence;

import lombok.Data;

@Service
public class ServiceLicence {
	
	private static final String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int licenceKey_length=16;
	private static  final  SecureRandom random = new SecureRandom();
	
	@Autowired
	RepositoryLicence repositoryLicence;
	private SecretKey secretKey;

	public Licence createlicence(Licence licence) {
		licence.setStatus(Status.INACTIVE);
		 licence.setLicenceKey(generateLicencekey());
		 return repositoryLicence.save(licence);
		
		}
	public String generateLicencekey() {
		StringBuilder licenseKey = new StringBuilder(licenceKey_length);
        for (int i = 0; i < licenceKey_length; i++) {
            int index = random.nextInt(characters.length());
            licenseKey.append(characters.charAt(index));
        }
        return licenseKey.toString();
		
	}
	public Optional<Licence> getByid(UUID id) {
		return repositoryLicence.findById(id);
		
	}
//	public String generateLicencekey() {
//		StringBuilder licenseKey = new StringBuilder(licenceKey_length);
//        for (int i = 0; i < licenceKey_length; i++) {
//            int index = random.nextInt(characters.length());
//            licenseKey.append(characters.charAt(index));
//        }
//        Optional<Licence> licence = repositoryLicence.findById(id);
//        
//        Licence licence1 = licence.get();
//        licence1.setLicenceKey(licenseKey.toString());//licenseKey edhu StringBuilder-il iruppadhaal toString aaga maatrugirom
//        return repositoryLicence.save(licence1);
//        return licenseKey.toString();
//		
//	}
	// Convert SecretKey to Base64 string for storage***
	public String getSecretkey() {
		
	  return Base64.getEncoder().encodeToString(secretKey.getEncoded());//getEncoded(): getEncoded() முறையானது முக்கியப் பொருளை பைட் வரிசை வடிவத்தில் வழங்குகிறது. 
	                                                                                  //இந்த குறியிடப்பட்ட படிவம் விசையை பாதுகாப்பாக சேமிக்க அல்லது அனுப்ப பயனுள்ளதாக இருக்கும்.
	                                                                    //Base64.getEncoder().encodeToString(...): Encodes the byte array into a Base64 string.
		    	
	}
    public ServiceLicence() throws Exception {
        // Generate a new secret key during service initialization
        this.secretKey = EncryptionDecryption.secretkeyGen();
        
    }
    public String encryptLicenseKey(String licenseKey) throws Exception {
        return EncryptionDecryption.encrypt(licenseKey, secretKey);
    }
}
