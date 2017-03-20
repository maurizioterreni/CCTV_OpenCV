package com.terreni.cctv.recorder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import com.terreni.cctv.model.RecorderUtils;
import com.terreni.cctv.recorder.Detector.MotionDetection;


public class Recorder implements Runnable{
	static{
		nu.pattern.OpenCV.loadLocally();
	}
	private RecorderUtils utils;

	public Recorder(RecorderUtils utils) {
		this.utils = utils;
	}

	public void run() {
		while(true){
			Long timeRecording = System.currentTimeMillis() + (1000 * 30);
			
			System.out.println((timeRecording - System.currentTimeMillis()));
			
			try {
				doVideo(timeRecording, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")));
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			}
		}
	}


	public void doVideo(Long timeRecording , String name) throws InterruptedException{
//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		VideoCapture camera = new VideoCapture(0);
		
		VideoWriter writer = new VideoWriter(utils.getPath() + "/" + name + "." + utils.getFormat(), VideoWriter.fourcc('D', 'I', 'V', 'X'), utils.getFps(), utils.getSize(), true);
			
		Long timeLaps = (Long) (1000 / utils.getFps().longValue());
		
		
		camera.set(Videoio.CV_CAP_PROP_FRAME_WIDTH, utils.getWidth());
		camera.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT, utils.getHeight());
		Mat frame = new Mat();
		Mat prevFrame = new Mat();
		MotionDetection motionDetection = new MotionDetection(new Size(new Double(utils.getWidth()), new Double(utils.getHeight())) , 16);
		while(System.currentTimeMillis() < timeRecording){

			if (camera.read(frame)){
				Thread.sleep(timeLaps);
				Imgproc.resize(frame, frame, utils.getSize());
				Imgproc.putText(frame,LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")), utils.getLabelPoint(),Core.FONT_HERSHEY_PLAIN, 1.0 ,new  Scalar(0,255,255));
				if(!prevFrame.empty()){
					writer.write(motionDetection.detectMotion(prevFrame, frame.clone()));
					prevFrame = frame.clone();
				}else{
					prevFrame = frame.clone();
				}
				
				frame.release();
			}
				
		}	
		writer.release();
	}

}