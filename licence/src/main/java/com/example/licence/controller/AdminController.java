package com.example.licence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.licence.dto.DataResponse;
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
	

}
