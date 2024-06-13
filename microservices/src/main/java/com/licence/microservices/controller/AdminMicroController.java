package com.licence.microservices.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.licence.dto.DataResponse;
import com.example.licence.entity.Licence;
import com.licence.microservices.service.AdminMicroService;

@RestController
@RequestMapping("/api/micro")
public class AdminMicroController {
	
	@Autowired
	AdminMicroService microserviceService;
	    //Practice
	//String Type
	@GetMapping("/getmicro/{id}")
	public String getByid(@PathVariable ("id") UUID id){		
		return microserviceService.getByid(id);
	}
	/*//Obj Type
	@GetMapping("/getmicro/{id}")
	public Licence getByid(@PathVariable ("id") UUID id){		
		return microserviceService.getByid(id);
	}*/
    @PostMapping("/decrypt")
	
    public String decryptData(@RequestBody DataResponse response) {
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
	

}
