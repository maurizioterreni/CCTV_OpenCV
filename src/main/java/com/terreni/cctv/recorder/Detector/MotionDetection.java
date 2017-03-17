package com.terreni.cctv.recorder.Detector;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class MotionDetection{

	private static int SENSITIVITY_VALUE = 40;
	private static int MAX_VALUE = 255;
	
	private Size size;
	private int cvType;

	public MotionDetection(Size size, int cvType){
		this.size = size;
		this.cvType = cvType;
	}

	public Mat detectMotion(Mat previusImage, Mat nextImage) {
		Mat grayImage1 = new Mat(size, cvType);
		Mat grayImage2 = new Mat(size, cvType);
		Imgproc.cvtColor(previusImage, grayImage1, Imgproc.COLOR_BGR2GRAY);
		Imgproc.cvtColor(nextImage, grayImage2, Imgproc.COLOR_BGR2GRAY);

		Mat differenceImage = new Mat(size, cvType);
		Core.absdiff(grayImage1, grayImage2, differenceImage);

		Mat thresholdImage = new Mat(size, cvType);
		Imgproc.threshold(differenceImage, thresholdImage, SENSITIVITY_VALUE, MAX_VALUE, Imgproc.THRESH_BINARY);

		Mat detectionImage = nextImage.clone();

		Rect rect = detectionContours(thresholdImage);
		if(rect != null){
			Imgproc.rectangle(detectionImage, rect.br(),rect.tl(),
					new Scalar(0, 255, 0), 1);
			
		}

		return detectionImage;

	}


	private Rect detectionContours(Mat outmat) {
		Mat v = new Mat();
		Mat vv = outmat.clone();
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Imgproc.findContours(vv, contours, v, Imgproc.RETR_LIST,
				Imgproc.CHAIN_APPROX_SIMPLE);

		double maxArea = 100;
		int maxAreaIdx = -1;
		Rect r = null;
		int minX = Integer.MAX_VALUE;
		int maxX = -1;
		int minY =  Integer.MAX_VALUE;
		int maxY = -1;
		if(contours.size() > 0){
			for (int idx = 0; idx < contours.size(); idx++) { 
				Mat contour = contours.get(idx); 
				double contourarea = Imgproc.contourArea(contour); 
				if (contourarea > maxArea) {
					maxAreaIdx = idx;
					r = Imgproc.boundingRect(contours.get(maxAreaIdx));
					if(r.tl().x < minX){
						minX = r.x;
					}
					if(r.tl().y < minY){
						minY = r.y;
					}
					if(r.br().x > maxX){
						maxX = r.x + r.width;
					}
					if(r.br().y > maxY){
						maxY = r.y + r.height;
					}
				}

			}
			v.release();
			return new Rect(minX, minY, Math.abs(maxX - minX),Math.abs( maxY - minY ));
		}
		v.release();
		return null;

	}
	
//	
//	private Rect createAreaRect(int minX,int maxX, int minY,int maxY){
//		
//		int width = maxX - minX;
//		int 
//		if()
//		
//		return new Rect(minX, minY, maxX - minX, maxY - minY);
//	}



}
