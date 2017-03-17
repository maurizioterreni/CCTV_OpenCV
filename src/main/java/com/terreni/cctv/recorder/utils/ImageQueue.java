package com.terreni.cctv.recorder.utils;

import java.util.LinkedList;

import org.opencv.core.Mat;


public class ImageQueue {

  private LinkedList<Mat> list = new LinkedList<Mat>();

  public void put(Mat image) {
    list.addLast(image);
  }

  public Mat get() {
    if (list.isEmpty()) {
      return null;
    }
    return list.removeFirst();
  }

  public Mat peek() {
    return list.getFirst();
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }

  public int size() {
    return list.size();
  }
}

