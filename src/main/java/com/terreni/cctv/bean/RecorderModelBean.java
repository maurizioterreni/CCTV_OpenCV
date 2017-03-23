package com.terreni.cctv.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.terreni.cctv.dao.RecorderModelDao;
import com.terreni.cctv.model.RecorderModel;

@Model
public class RecorderModelBean {
	@EJB
	private RecorderModelDao recorderModelDao;
	
	public List<RecorderModel> getAllRecord(){
		return recorderModelDao.getAllRecorder();
	}
}