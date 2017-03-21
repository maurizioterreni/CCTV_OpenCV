package com.terreni.cctv.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="recorder")
public class RecorderModel extends BaseEntity{

	private Long time;
	private String path;


	RecorderModel(){
		super();
	}
	public RecorderModel(String uuid){
		super(uuid);
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
	public String getStrTime(){
		Date date = new Date(time);
		return date.toString();
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public void newRecorder(String path){
		this.path = path;
		this.time = System.currentTimeMillis();
	}
}
