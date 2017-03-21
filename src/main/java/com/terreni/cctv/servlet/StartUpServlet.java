package com.terreni.cctv.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartUpServlet  implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		System.out.println("hello from servlet");
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
