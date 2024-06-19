package com.example.licence.dto;

import java.util.UUID;

import com.example.licence.enumaration.ExpiredStatus;
import com.example.licence.enumaration.Status;

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
