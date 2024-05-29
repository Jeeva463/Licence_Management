package com.example.licence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.licence.entity.Licence;
import com.example.licence.service.ServiceLicence;

@RestController
public class EmployeeLicence {
	@Autowired
	ServiceLicence serviceLicence;
	
	@PostMapping("/create")
	public Licence createlicence(@RequestBody Licence licence){
		serviceLicence.createlicence(licence);
		return licence;
		
	}

}
