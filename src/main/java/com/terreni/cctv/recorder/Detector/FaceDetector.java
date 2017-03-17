package com.terreni.cctv.recorder.Detector;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector implements Runnable {//TODO creare un pool di immagini da analizzare non fare cosi!

	private String fileOutputPath;
	private Mat imageInput;

	public FaceDetector(String fileOutputPath, Mat imageInput){
		this.fileOutputPath = fileOutputPath;
		this.imageInput = imageInput;
	}

	public void run() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		CascadeClassifier faceDetector = new CascadeClassifier(FaceDetector.class.getResource("haarcascade_frontalface_alt.xml").getPath());
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(imageInput, faceDetections);
		if(faceDetections.toArray().length > 0){
			for (Rect rect : faceDetections.toArray()) {//TODO non creare bordo ma ritagliare immagine
				Imgproc.rectangle(imageInput, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
			}
			//TODO change name output
			String filename = "ouput05.jpg";
			Imgcodecs.imwrite(fileOutputPath + filename, imageInput);
		}
	}
}
