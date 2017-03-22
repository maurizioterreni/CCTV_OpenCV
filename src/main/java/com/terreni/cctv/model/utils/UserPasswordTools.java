package com.terreni.cctv.model.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class UserPasswordTools {

	private static final String ALGORITHM = "SHA-256";
	
	public UserPasswordTools() {}
	
	public static String encrypt(String password) {
		return createPasswordKey( password );
	}

	private static String createPasswordKey( String password ) {
		if ( password == null ) {
			throw new IllegalArgumentException( "password cannot be null" );
		}
		
		try {
			MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			md.update( password.getBytes() );
			
			return Base64.getEncoder().encodeToString(md.digest());
			
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException( "algorithm not found", e );
		}
	}

	public String generateEncryptedPassword(String currentPassword) {
		if ( currentPassword == null ) {
			throw new IllegalArgumentException( "password cannot be null" );
		}
		
		try {
			MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			md.update( currentPassword.getBytes() );
			
			return Base64.getEncoder().encodeToString(md.digest());
			
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException( "algorithm not found", e );
		}
	}
	
	
	
}
