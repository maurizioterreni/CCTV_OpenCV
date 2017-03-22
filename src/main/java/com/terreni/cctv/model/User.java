package com.terreni.cctv.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.terreni.cctv.model.utils.UserPasswordTools;

@Entity
@Table(name="user")
public class User extends BaseEntity{
	private String username;
	private String password;
	private String role;
	
	User(){
		super();
	}
	
	public User(String uuid){
		super(uuid);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		
		this.password = UserPasswordTools.encrypt(password);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
