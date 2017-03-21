package com.terreni.cctv.servlet;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.terreni.cctv.dao.LogDao;
import com.terreni.cctv.model.RecorderUtils;
import com.terreni.cctv.recorder.Recorder;

public class StartUpServlet  implements ServletContextListener {
	
	@EJB
	LogDao logDao;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		RecorderUtils utils = new RecorderUtils("");
		utils.setPath("/Users/maurizio/Desktop/video/");
		utils.setFormat("mp4");
		utils.setFps(5);
		utils.setWidth(640);
		utils.setHeight(480);
		utils.setDoRecorder(true);
		
		Thread thread = new Thread(new  Recorder(utils , logDao)); 
		
		thread.start();
		System.out.println("hello from servlet");
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
