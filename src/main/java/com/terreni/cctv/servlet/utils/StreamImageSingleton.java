package com.terreni.cctv.servlet.utils;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class StreamImageSingleton {
	
	private static StreamImageSingleton singleton;
	private Mat frame;
	private Size size;
	
	private StreamImageSingleton(){
		size = new Size(200.0, 200.0);
		frame = new Mat();
	}
	
	public static StreamImageSingleton getInstace(){
		if(singleton == null){
			singleton = new StreamImageSingleton();
		}
		return singleton;
	}

	public static StreamImageSingleton getSingleton() {
		return singleton;
	}

	public static void setSingleton(StreamImageSingleton singleton) {
		StreamImageSingleton.singleton = singleton;
	}

	public Mat getFrame() {
		return frame;
	}

	public void setFrame(Mat frame) {
		Imgproc.resize(frame, this.frame, size);
	}
	
}
	
