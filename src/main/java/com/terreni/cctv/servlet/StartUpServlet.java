package com.terreni.cctv.servlet;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.terreni.cctv.dao.LogDao;
import com.terreni.cctv.dao.RecorderModelDao;
import com.terreni.cctv.dao.UserDao;
import com.terreni.cctv.model.RecorderUtils;
import com.terreni.cctv.model.User;
import com.terreni.cctv.model.factory.ModelFactory;
import com.terreni.cctv.model.utils.UserPasswordTools;
import com.terreni.cctv.recorder.Recorder;

public class StartUpServlet  implements ServletContextListener {
	
	@EJB
	LogDao logDao;
	@EJB
	RecorderModelDao recorderModelDao;
	
	@EJB
	UserDao userDao;

	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		RecorderUtils utils = new RecorderUtils("");
		utils.setPath("/Users/maurizio/Desktop/video/");
		utils.setFormat("mp4");
		utils.setFps(5);
		utils.setWidth(640);
		utils.setHeight(480);
		utils.setCameraId(0);
		utils.setTimeRecording(new Long(30));
		utils.setDoRecorder(true);
		
		Thread thread = new Thread(new  Recorder(utils , logDao , recorderModelDao)); 
		
//		thread.start();
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
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
