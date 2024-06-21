package com.example.licence.encryptionutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.licence.entity.Licence;
import com.example.licence.enumaration.ExpiredStatus;
import com.example.licence.enumaration.Status;
import com.example.licence.repository.RepositoryLicence;

@Configuration
@EnableScheduling
public class SchedulingConfig {
	@Autowired
	RepositoryLicence repositoryLicence;
	 
	@Scheduled(fixedRate = 1000) // Runs every minute
	public void updateExpiredStatuses() {
		List<Licence> licences = repositoryLicence.findAll();
		LocalDateTime now = LocalDateTime.now();
		List<Licence> arrayList = new ArrayList<>();
		for (Licence licence : licences) {
			LocalDateTime expiryDate = LocalDateTime.parse(licence.getExpiryDate());//parse edhu use panna reason expiryDate string irundhadhala
			LocalDateTime gracePeriodEndDate = LocalDateTime.parse(licence.getGracePeriodEndDate());

			if (licence.getStatus() != null && licence.getExpiredStatus() == null) {
				licence.setExpiredStatus(ExpiredStatus.NON_EXPIRED);
			}
			if (licence.getStatus() != null && licence.getExpiredStatus() != null) {

				if (now.isAfter(expiryDate) && now.isBefore(gracePeriodEndDate)) {
																
					licence.setExpiredStatus(ExpiredStatus.EXPIRED_SOON);
				}
				if (gracePeriodEndDate.isBefore(now)) {// gracePeriodEndDate enbadhu,now mun(isBefore)irukkuradhu 
					licence.setExpiredStatus(ExpiredStatus.EXPIRED);
					licence.setStatus(Status.INACTIVE);
				}
				arrayList.add(licence);
			}
		}		
			repositoryLicence.saveAll(arrayList);		
	}
}
