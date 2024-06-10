package com.example.licence.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.licence.entity.Licence;

@Repository
public interface RepositoryLicence extends JpaRepository<Licence, UUID> {



	Optional<Licence> findBylicenceKey(String licenceKey);


	

}
