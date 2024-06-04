package com.example.licence.controller;

import java.security.Key;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.licence.dto.DataResponse;
import com.example.licence.encryptionutil.EncryptionDecryption;
import com.example.licence.entity.Licence;
import com.example.licence.service.ServiceLicence;

import lombok.Data;

@RestController
@RequestMapping("/api/licence")
public class EmployeeLicence {
	@Autowired
	ServiceLicence serviceLicence;
	EncryptionDecryption encryptionDecryption;
	
	@PostMapping("/create")
	public Licence createlicence(@RequestBody Licence licence){
		serviceLicence.createlicence(licence);
		return licence;
		
	}
	@GetMapping("/getlicence/{id}")
	
	public Optional <Licence> getByid(@PathVariable ("id") UUID id) {
		return serviceLicence.getByid(id);
		
	}
//	@PutMapping("/generatekey/{id}/{companyName}")
//	
//	public Licence generateLicencekey(@PathVariable UUID id,@PathVariable String companyName) {
//		return serviceLicence.generateLicencekey(id,companyName);
//		
//	}
//	@GetMapping("/secretkey")
//	
//	public String getSecretkey() {
//		return serviceLicence.getSecretkey();
//	
//	}
//	@PostMapping("/encrypt/{licenseKey}")
//    public String encryptLicenseKey(@PathVariable String licenseKey) {
//        try {
//            return serviceLicence.encryptLicenseKey(licenseKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error encrypting license key";
//        }
//    }

    @PostMapping("/generate-and-encrypt/{licenseKey}")
    public DataResponse generateAndEncrypt(@PathVariable String licenseKey) {
        try {
            // Generate a new secret key
            String secretKey = serviceLicence.GenerateSec();
            
            // Encrypt the license key
            String encryptedLicenseKey = serviceLicence.encryptData(licenseKey);
            
            // Return both the encrypted license key and the secret key in the response
            return new DataResponse(encryptedLicenseKey, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
            // Return an error response (you can customize this as needed)
            return new DataResponse("Error encrypting data", "Error generating secret key");
        }
    }
    @PostMapping("/decrypt")
    public String decryptData(@RequestParam String encryptedLicenseKey, @RequestParam String secretKey) {
        try {
            // Set the secret key
        	serviceLicence.setSecretKey(secretKey);
            
            // Decrypt the license key
            return serviceLicence.decryptData(encryptedLicenseKey);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error decrypting data";

        }
        }
}

