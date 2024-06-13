package com.licence.microservices.config;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;



@Configuration
public class TemConfig {
	@Bean
	public RestTemplate getTemplate() {
		
		RestTemplate templete = new RestTemplate();//indha pridefine RestTemplate class use panna class create panni adhula obj create panni autowired pannugirom
		
		return templete;
		
		
	}
}
