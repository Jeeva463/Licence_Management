package com.example.licence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LicenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenceApplication.class, args);
	}

}
//data objectla irukkum secretkey encrypted panna secretkey stringla irukkum
//data encrypt datala irukkum decrypt panna secretkey Stringla irundhu secretkey 
//change panni decrypt pannanum