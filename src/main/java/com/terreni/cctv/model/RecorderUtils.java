package com.terreni.cctv.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opencv.core.Point;
import org.opencv.core.Size;

@Entity
@Table(name="recorder_utils")
public class RecorderUtils extends BaseEntity{
	
	
	private String path;
	private String format;
	private Integer fps;
	private Integer width;
	private Integer height;
	
	
	RecorderUtils(){
		super();
	}
	
	public RecorderUtils(String uuid) {
		super(uuid);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getFps() {
		return fps;
	}

	public void setFps(Integer fps) {
		this.fps = fps;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public Size getSize(){
		return  new Size(width, height);
	}
	
	public Point getLabelPoint(){
		return new Point(width - 200,height - 20);
	}
	
	
}
