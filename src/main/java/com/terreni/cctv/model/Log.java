package com.terreni.cctv.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="log")
public class Log extends BaseEntity{
	private String mex;
	private Long time;
	
	Log(){
		super();
	}
	
	public Log(String uuid){
		super(uuid);
	}

	public String getMex() {
		return mex;
	}

	public void setMex(String mex) {
		this.mex = mex;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}
	
	public void create(String msg){
		this.mex = msg;
		this.time = System.currentTimeMillis();
	}
	
	public String timeToStr(){
		Date date = new Date(time);
		return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
	}
	
}
