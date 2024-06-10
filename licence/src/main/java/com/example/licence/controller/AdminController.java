package com.example.licence.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.licence.dto.DataResponse;
import com.example.licence.entity.Licence;
import com.example.licence.service.AdminService;

@RestController
@RequestMapping("api/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@PostMapping("/decrypt")
	
    public String decryptData(@RequestBody DataResponse response) {
        try {
            // Set the secret key
        	adminService.setSecretKey(response.getSecretKey());
            
            // Decrypt the license key
            return adminService.decryptData(response.getData());
        } catch (Exception e) {
            e.printStackTrace();
            return "Error decrypting data";
        }
        
        }
	@GetMapping("/get/{licenceKey}")
	
	public Map getdetails(@PathVariable String licenceKey) {
		return adminService.getdetails(licenceKey);
		
	}
	@PutMapping("update/{id}")
	
	public Licence putDetails(@PathVariable UUID id) throws Exception {
		return adminService.putDetails(id);
		
	}
	
}
