package com.example.licence.service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.licence.entity.Licence;
import com.example.licence.repository.RepositoryLicence;

@Service
public class ServiceLicence {
	
	private static final String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int licenceKey_length=16;
	private static  final  SecureRandom random = new SecureRandom();
	
	
	@Autowired
	RepositoryLicence repositoryLicence;

	public void createlicence(Licence licence) {
		
		repositoryLicence.save(licence);
		}

	public Optional<Licence> getByid(UUID id) {
	
		return repositoryLicence.findById(id);
	}

	public String generateLicencekey() {
		StringBuilder licenseKey = new StringBuilder(licenceKey_length);
        for (int i = 0; i < licenceKey_length; i++) {
            int index = random.nextInt(characters.length());
            licenseKey.append(characters.charAt(index));
        }
        return licenseKey.toString();
		
	}

	
}
