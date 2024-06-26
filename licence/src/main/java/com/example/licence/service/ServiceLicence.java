package com.example.licence.service;


import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.licence.dto.DataResponse;
import com.example.licence.dto.EmailStructure;
import com.example.licence.dto.LicenceDto;
import com.example.licence.encryptionutil.EncryptionDecryption;
import com.example.licence.entity.Licence;
import com.example.licence.enumaration.Status;
import com.example.licence.repository.RepositoryLicence;

import jakarta.mail.internet.MimeMessage;

@Service
public class ServiceLicence {
	
	private static final String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int licenceKey_length=16;
	private static  final  SecureRandom random = new SecureRandom();
	
	@Autowired
	JavaMailSender javaMailSender;
	RepositoryLicence repositoryLicence;
	private SecretKey secretKey;
	DataResponse response;

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
	public Map getDetails() {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("repositoryLicence", repositoryLicence.findAll());
			map.put("time", LocalDateTime.now());
			return  map;
			 	
		}
    public String GenerateSec() throws Exception {
        // Generate a new secret key during service initialization
        this.secretKey = EncryptionDecryption.secretkeyGen();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());//getEncoded(): getEncoded() முறையானது முக்கியப் பொருளை பைட் வரிசை வடிவத்தில் வழங்குகிறது. 
                                                                          //இந்த குறியிடப்பட்ட படிவம் விசையை பாதுகாப்பாக சேமிக்க அல்லது அனுப்ப பயனுள்ளதாக இருக்கும்.
                                                                          //Base64.getEncoder().encodeToString(...): Encodes the byte array into a Base64 string.
   
    }
    public String encryptData(String data) throws Exception {
        return EncryptionDecryption.encrypt(data, secretKey);
        
    } 
	public ResponseEntity<?> getBylicencekey(String licencekey) {
		Optional<Licence>obj = repositoryLicence.findBylicenceKey(licencekey);
		Licence lice =obj.get();
		LicenceDto dto = new LicenceDto();
		dto.setId(lice.getId());
		dto.setCompanyName(lice.getCompanyName());
		dto.setCompanyAddress(lice.getCompanyAddress());
		dto.setContactNumber(lice.getContactNumber());
		dto.setEmailId(lice.getEmailId());
		dto.setLicenceKey(lice.getLicenceKey());
		dto.setStatus(lice.getStatus());
		dto.setExpiredStatus(lice.getExpiredStatus());
		dto.setExpiryDate(lice.getExpiryDate());
		dto.setActivationDate(lice.getActivationDate());
		dto.setGracePeriodEndDate(lice.getGracePeriodEndDate());
		return ResponseEntity.ok(dto);
		
	}
	public ResponseEntity<?> update(LicenceDto licenceDto) {
		Optional<Licence> obj = repositoryLicence.findById(licenceDto.getId());
		Licence licen = obj.get();
		licen.setActivationDate(licenceDto.getActivationDate());
		licen.setExpiryDate(licenceDto.getExpiryDate());
		licen.setExpiredStatus(licenceDto.getExpiredStatus());
		licen.setGracePeriodEndDate(licenceDto.getGracePeriodEndDate());
		licen.setStatus(licenceDto.getStatus());
		repositoryLicence.save(licen);

		LicenceDto dto = new LicenceDto();
		dto.setId(licen.getId());
		dto.setCompanyName(licen.getCompanyName());
		dto.setCompanyAddress(licen.getCompanyAddress());
		dto.setContactNumber(licen.getContactNumber());
		dto.setEmailId(licen.getEmailId());
		dto.setLicenceKey(licen.getLicenceKey());
		dto.setStatus(licen.getStatus());
		dto.setExpiredStatus(licen.getExpiredStatus());
		dto.setExpiryDate(licen.getExpiryDate());
		dto.setActivationDate(licen.getActivationDate());
		dto.setGracePeriodEndDate(licen.getGracePeriodEndDate());
		return ResponseEntity.ok(dto);
	}
//	public List<Licence> get() {
//		return repositoryLicence.findAll();
//		
//	}
//	public Optional<Licence> getByid(UUID id) {
//		return repositoryLicence.findById(id);
//		
//	}
	public void getMail(EmailStructure emailStructure) throws Exception {
            String toEmail = emailStructure.getToEmail();
            String subject = emailStructure.getSubject();
            
            // Generate secret key and encrypt data
            SecretKey secretKey = EncryptionDecryption.secretkeyGen();
            String data = response.getData();
            String encryptedData = EncryptionDecryption.encrypt(data, secretKey);

            // Compose the email body
            String body = String.format("{\n    \"secretKey\": \"%s\",\n    \"data\": \"%s\"\n}", secretKey.toString(), encryptedData);

            // Create MimeMessage and MimeMessageHelper for complex email content
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body);

            // Send the email
            javaMailSender.send(mimeMessage);
}
}

