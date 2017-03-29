package com.terreni.cctv.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.terreni.cctv.dao.RecorderUtilsDao;
import com.terreni.cctv.model.RecorderUtils;

@Model
public class RecorderUtilsBean {
	
	@Inject
	private RecorderUtilsDao recorderUtilsDao;
	
	
	private RecorderUtils recorderUtils;
	
	public RecorderUtils getRecorderUtils() {
		if(recorderUtils == null){
			recorderUtils = recorderUtilsDao.getSetting();
		}
		return recorderUtils;
	}
	
	
	public void saveSetting(){
		System.out.println("saved");
	}
	
	public List<String> getFormat(){
		List<String> formats = new ArrayList<>();
		
		formats.add("mp4");
		formats.add("avi");
		
		return formats;
	}
}
