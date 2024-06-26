package com.example.licence.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.licence.dto.DataResponse;
import com.example.licence.dto.EmailStructure;
import com.example.licence.dto.LicenceDto;
import com.example.licence.encryptionutil.EncryptionDecryption;
import com.example.licence.entity.Licence;
import com.example.licence.service.ServiceLicence;

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
	@GetMapping("/getall")
	public Map getDetails(){
		return serviceLicence.getDetails();
		
	}
    @PostMapping("/generate-and-encrypt")
    public DataResponse generateAndEncrypt(@RequestBody String data) {
        try {
            // Generate a new secret key
            String secretKey = serviceLicence.GenerateSec();
            
            // Encrypt the license key
            String response = serviceLicence.encryptData(data);
            
            // Return both the encrypted license key and the secret key in the response
            return new DataResponse(response, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
            // Return an error response (you can customize this as needed)
            return new DataResponse("Error encrypting data", "Error generating secret key");
        }
    }
    @GetMapping("/get/{licencekey}")
    
    public ResponseEntity<?> getBylicencekey(@PathVariable String licencekey) {
    	return serviceLicence.getBylicencekey(licencekey);
		
    }
    @PostMapping("/update")
    
    public ResponseEntity<?>update(@RequestBody LicenceDto licenceDto){
    	return serviceLicence.update(licenceDto);
    }
//	@GetMapping("/getlicence/{id}")
//	
//	public Optional <Licence> getByid(@PathVariable ("id") UUID id) {
//		return serviceLicence.getByid(id);
//		
//	}
//	@GetMapping("/getDetails")
//	
//	public List<Licence> get(){
//		return serviceLicence.get();	
//	}
    @PostMapping("/sendMail")
    public String getMail(@RequestBody EmailStructure emailStructure ) throws Exception {
    	serviceLicence.getMail(emailStructure);
		return "sent a mail";
    }
}

