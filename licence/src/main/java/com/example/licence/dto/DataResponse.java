package com.example.licence.dto;

import lombok.Data;

@Data
public class DataResponse {
	    private String secretKey;
	    private String data ;

	    public DataResponse(String data, String secretKey) {	       
	        this.secretKey = secretKey;
	        this.data = data;
	    }
	    
	    
}
