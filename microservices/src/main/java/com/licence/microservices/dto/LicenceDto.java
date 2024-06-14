package com.licence.microservices.dto;

import java.util.UUID;

import com.licence.microservices.enumaration.ExpiredStatus;
import com.licence.microservices.enumaration.Status;

import lombok.Data;

@Data
public class LicenceDto {
	
	private UUID id;
	private String companyName;
	private String companyAddress;
	private String contactNumber;
	private String emailId;
	private String licenceKey;
	private Status status;
	private ExpiredStatus expiredStatus;
	private String expiryDate;
	private String activationDate;
	private String gracePeriodEndDate;
	
}
