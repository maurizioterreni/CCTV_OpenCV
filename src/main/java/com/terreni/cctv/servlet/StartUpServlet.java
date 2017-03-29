package com.terreni.cctv.servlet;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.terreni.cctv.dao.LogDao;
import com.terreni.cctv.dao.RecorderModelDao;
import com.terreni.cctv.dao.RecorderUtilsDao;
import com.terreni.cctv.model.RecorderUtils;
import com.terreni.cctv.recorder.Recorder;

public class StartUpServlet  implements ServletContextListener {
	
	@EJB
	LogDao logDao;
	@EJB
	RecorderModelDao recorderModelDao;
	@EJB
	RecorderUtilsDao recorderUtilsDao;
	
//	@EJB
//	UserDao userDao;
	
	private Thread threadRegistration;

	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		RecorderUtils utils =  recorderUtilsDao.getSetting();
		threadRegistration = new Thread(new  Recorder( utils, logDao , recorderModelDao)); 
		
		if(utils.getDoRecorder()){
			runRegistration();
		}

	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
	public void runRegistration(){
		threadRegistration.start();
	}
	
//	public void stopRegistration(){
//		threadRegistration.
//	}
	
//	private void createUser(){
//		User user = ModelFactory.user();
//		
//		user.setUsername("name");
//		user.setPassword(UserPasswordTools.encrypt("password"));
//		
//		userDao.save(user);
//		
//	}
}
