package com.terreni.cctv.model.factory;

import java.util.UUID;

import com.terreni.cctv.model.Log;
import com.terreni.cctv.model.RecorderModel;
import com.terreni.cctv.model.RecorderUtils;
import com.terreni.cctv.model.User;



public final class ModelFactory {

	private ModelFactory(){}
	
	public static User user() {
		return new User(UUID.randomUUID().toString());
	}
	
	public static Log log(){
		return new Log(UUID.randomUUID().toString());
	}
	
	public static RecorderModel recorderModel(){
		return new RecorderModel(UUID.randomUUID().toString());
	}
	public static RecorderUtils recorderUtils(){
		return new RecorderUtils(UUID.randomUUID().toString());
	}
}