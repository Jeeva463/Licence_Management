package com.licence.microservices.controller;

import java.util.List;
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
import com.licence.microservices.dto.LicenceDto;
import com.licence.microservices.dto.Response;
import com.licence.microservices.service.AdminMicroService;

@RestController
@RequestMapping("/api/micro")
public class AdminMicroController {
	
	@Autowired
	AdminMicroService microserviceService;
	    //Practice
//	//String Type
//	@GetMapping("/getmicro/{id}")
//	public String getByid(@PathVariable ("id") UUID id){		
//		return microserviceService.getByid(id);
//	}
//	//Obj Type
//	@GetMapping("/getmicro/{id}")
//	public LicenceDto getByid(@PathVariable ("id") UUID id){		
//		return microserviceService.getByid(id);
//	}
	//Entity Type
//		@GetMapping("/getmicro/{id}")
//		public ResponseEntity<LicenceDto> getByid(@PathVariable ("id") UUID id){		
//			return microserviceService.getByid(id);
//		}
//	//exchange Type
//	@GetMapping("getall")
//	public ResponseEntity<List<LicenceDto>> getdetails(){
//		return microserviceService.getdetails();
//	}
	
    @PostMapping("/decrypt")
	
    public String decryptData(@RequestBody Response response) {
        try {
            // Set the secret key
        	microserviceService.setSecretKey(response.getSecretKey());        	  
            // Decrypt the license key
            return microserviceService.decryptData(response.getData());
        } catch (Exception e) {
            e.printStackTrace();
            return "Error decrypting data";
        }
        
        }
    @PutMapping("/update/{licencekey}")
    public ResponseEntity<?> updateDetails(@PathVariable String  licencekey ) {
    	microserviceService.updateDetails(licencekey);
		
    }

}
