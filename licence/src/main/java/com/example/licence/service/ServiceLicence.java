package com.example.licence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.licence.entity.Licence;
import com.example.licence.repository.RepositoryLicence;

@Service
public class ServiceLicence {
	@Autowired
	RepositoryLicence repositoryLicence;

	public void createlicence(Licence licence) {
		
		repositoryLicence.save(licence);
		
		
	}

}
