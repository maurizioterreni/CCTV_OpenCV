package com.terreni.cctv.bean;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.terreni.cctv.dao.UserDao;
import com.terreni.cctv.model.User;
import com.terreni.cctv.model.utils.UserPasswordTools;

@Model
public class ChangePasswordBean {

	public static final int MIN_LENGHT = 6;

	@EJB
	private UserDao userDao;
	
	@Inject
	private LoginBean login;
	
	private String currentPassword;
	private String newPassword;
	private String newPasswordAgain;
	
	public boolean isValid() {
		return getCurrentPassword() != null && !getCurrentPassword().isEmpty() &&
				getNewPassword() != null && !getNewPassword().isEmpty() &&
				getNewPasswordAgain() != null && !getNewPasswordAgain().isEmpty();
	}
	
	public String change() {
		User user = login.getUser();
		String baseUrl = "/pages/user/change-password?faces-redirect=true&uz=" + user.getId();

		UserPasswordTools tools = new UserPasswordTools();
		
		// empty values
		if(!isValid()) {
			return baseUrl+"&error=true&cause=nullvalues";
		}
		
		// current password is incorrect
		if(!user.getPassword().equals( 
				tools.generateEncryptedPassword(currentPassword) )) {
			return baseUrl+"&error=true&cause=current";
		}
		
		// new password is too short
		if(newPassword.length() < MIN_LENGHT) {
			return baseUrl+"&error=true&cause=short";
		}
		
		// new password are not equals
		if(!newPassword.equals(newPasswordAgain)) {
			return baseUrl+"&error=true&cause=different";
		}
		
		userDao.updatePassword(user.getId(),
				tools.generateEncryptedPassword(newPassword));
		
		return baseUrl+"&success=true";
	}
	
	//
	// ACCESS METHODS
	//
	
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getNewPasswordAgain() {
		return newPasswordAgain;
	}
	public void setNewPasswordAgain(String newPasswordAgain) {
		this.newPasswordAgain = newPasswordAgain;
	}
	
}

