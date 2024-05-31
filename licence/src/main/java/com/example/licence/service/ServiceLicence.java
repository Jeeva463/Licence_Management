package com.example.licence.service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.licence.entity.Licence;
import com.example.licence.enumaration.Status;
import com.example.licence.repository.RepositoryLicence;

@Service
public class ServiceLicence {
	
	private static final String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int licenceKey_length=16;
	private static  final  SecureRandom random = new SecureRandom();
	
	
	@Autowired
	RepositoryLicence repositoryLicence;

	public void createlicence(Licence licence) {
		licence.setStatus(Status.ACTIVE);
		
		repositoryLicence.save(licence);
		}

	public Optional<Licence> getByid(UUID id) {
	
		return repositoryLicence.findById(id);
	}

	public Licence generateLicencekey(UUID id, String companyName) {
		StringBuilder licenseKey = new StringBuilder(licenceKey_length);
        for (int i = 0; i < licenceKey_length; i++) {
            int index = random.nextInt(characters.length());
            licenseKey.append(characters.charAt(index));
        }
        
        Optional<Licence> licence = repositoryLicence.findById(id);
        
        Licence licence1 = licence.get();
        licence1.setLicenceKey(licenseKey.toString());//licenseKey edhu StringBuilder-il iruppadhaal toString aaga maatrugirom
        return repositoryLicence.save(licence1);
        //return licence1.toString();
		
	}

	
}
