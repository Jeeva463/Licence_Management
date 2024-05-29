package com.example.licence.entity;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.licence.enumaration.ExpiredStatus;
import com.example.licence.enumaration.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="li_cence")
public class Licence implements Serializable {
	
	private final long serialversionUID=1l;//first koduthth fielda maththi vera field kodukkum podhu 2l versionaaga maarum--1-version number,l--long
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@GenericGenerator(name = "UUID", type = org.hibernate.id.uuid.UuidGenerator.class)
    @JdbcTypeCode(SqlTypes.CHAR)
	@Column(name="id")
	private UUID id;
	@Column(name="company_name")
	private String companyName;
	@Column(name="company_address")
	private String companyAddress;
	@Column(name = "contact_number")
	private String contactNumber;
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "licence_key")
	private String licenceKey;
	@Enumerated (EnumType.STRING)
	@Column(name = "status")
	private Status status;
	@Enumerated(EnumType.STRING)
	@Column(name = "expiredStatus")
	private ExpiredStatus expiredStatus;
	
	

}
