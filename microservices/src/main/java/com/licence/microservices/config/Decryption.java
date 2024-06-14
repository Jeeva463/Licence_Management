package com.licence.microservices.config;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Decryption {
	 public static String decrypt(String data, SecretKey secretKey) throws Exception {
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	        byte[] decodedBytes = Base64.getDecoder().decode(data.getBytes());
	        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
	        return new String(decryptedBytes);
	        
	 }
	

}
