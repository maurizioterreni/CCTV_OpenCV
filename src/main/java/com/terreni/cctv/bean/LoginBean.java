package com.terreni.cctv.bean;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.terreni.cctv.dao.UserDao;
import com.terreni.cctv.model.User;



@Model
public class LoginBean {

//	@Inject 
//	private ExternalContext context;
	@Inject
	private UserDao dao;
	private User user;
	
	public boolean isLoggedIn() {
		return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null;
	}
	
	public User getUser() {
		if ( user == null ) {
			initUser();
		}
		return user;
	}

	public void login(String userid, String password) throws ServletException {
		HttpServletRequest request = getRequest();
				
		request.login(userid, password);
	}

	public String logout() throws ServletException {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		getRequest().logout();
		return "/home.jsf?faces-redirect=true";
	}
	
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	private void initUser() {
		if ( FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() == null ) return;
	
		user = dao.findByName( FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName() );
	}
	
}

