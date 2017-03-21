package com.terreni.cctv.recorder;

import com.terreni.cctv.model.RecorderUtils;

public class RecorderSetup implements Runnable{

	private RecorderUtils recorderUtils;
	public RecorderSetup(RecorderUtils recorderUtils){
		this.recorderUtils = recorderUtils;
	}
	
	@Override
	public void run() {
		//Thread thread = new Thread(new  Recorder(recorderUtils));
		//thread.start();
		
		//TODO gestire crash di registrazione
	}
//	public static void main(String[] args) {
//		RecorderUtils utils = new RecorderUtils("");
//		utils.setPath("/Users/maurizio/Desktop/video/");
//		utils.setFormat("mp4");
//		utils.setFps(5);
//		utils.setWidth(640);
//		utils.setHeight(480);
//		
//		Thread thread = new Thread(new  Recorder(utils));
//		thread.start();
//	}
	
}

