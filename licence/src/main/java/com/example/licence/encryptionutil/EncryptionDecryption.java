package com.example.licence.encryptionutil;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncryptionDecryption {
	

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
	
	public static SecretKey  secretkeyGen() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");//getInstance("AES") edhu key generate pann AES helpa iruku 
		keyGen.init(256);//init--keysize sollalam ஜாவாவில் 256 பிட்கள் அளவு கொண்ட AES secretkey உருவாக்க KeyGenerator ஆப்ஜெக்டை துவக்குகிறது.
		return keyGen.generateKey();
	}
	 public static String encrypt(String data, SecretKey secretKey) throws Exception {
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	        //String commit = licenseKey+":"+emailId;//licenseKey,emailId--doFinal();il kodakka mudiyadhanaal this line
	        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
	        return Base64.getEncoder().encodeToString(encryptedBytes);
	    }
	 public static String decrypt(String encryptedLicenseKey, String encryptedemailId, SecretKey secretKey) throws Exception {
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	        String commit = encryptedLicenseKey+encryptedemailId;
	        byte[] decodedBytes = Base64.getDecoder().decode(commit);
	        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
	        return new String(decryptedBytes);
//		 
//	        Cipher cipher = Cipher.getInstance("AES");
//	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//
//	        // Decode and decrypt the encrypted license key
//	        byte[] decodedLicenseBytes = Base64.getDecoder().decode(encryptedLicenseKey);
//	        byte[] decryptedLicenseBytes = cipher.doFinal(decodedLicenseBytes);
//	        String decryptedLicenseKey = new String(decryptedLicenseBytes);
//
//	        // Decode and decrypt the encrypted email ID
////	        byte[] decodedEmailBytes = Base64.getDecoder().decode(encryptedemailId);
////	        byte[] decryptedEmailBytes = cipher.doFinal(decodedEmailBytes);
////	        String decryptedEmailId = new String(decryptedEmailBytes);
//
//	        // Return the decrypted license key and email ID
//	        return decryptedLicenseKey /*+ ":" + decryptedEmailI*/;
	    }

}
