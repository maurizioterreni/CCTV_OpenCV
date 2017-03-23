package com.terreni.cctv.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.terreni.cctv.dao.LogDao;
import com.terreni.cctv.model.Log;
import com.terreni.cctv.param.HttpParam;


@Model
public class LogBean {

	@Inject @HttpParam("l")
	private String logId;
	
	@EJB
	private LogDao logDao;
	
	public void saveLog(Log log){
		logDao.save(log);
	}
	
	public List<Log> getAllLog(){
		return logDao.getAllLog();
	}
	
}
