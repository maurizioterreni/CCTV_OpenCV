package com.terreni.cctv.model.factory;

import java.util.UUID;

import com.terreni.cctv.model.User;



public final class ModelFactory {

	private ModelFactory(){}
	
	public static User user() {
		return new User(UUID.randomUUID().toString());
	}
}