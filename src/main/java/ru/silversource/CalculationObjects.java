package ru.silversource;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class CalculationObjects {
    public static Integer getObjectsCount(String url) {

        Mat image = Imgcodecs.imread(url);
        if (image.empty()) {
            System.out.println("No image for download");
        }

        Mat grayImg = new Mat();
        Imgproc.cvtColor(image, grayImg, Imgproc.COLOR_BGR2GRAY);

        Mat binaryImg = new Mat();
        Imgproc.threshold(grayImg, binaryImg, 120, 230, Imgproc.THRESH_BINARY);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(binaryImg, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        int objectsCount = contours.size();

        Imgproc.drawContours(image, contours, -1, new Scalar(0, 255, 0), 2);

        Imgcodecs.imwrite("../test_outs/test2.jpg", image);

        Mat imgNew = Imgcodecs.imread("../test_outs/test2.jpg");

        try {
            ImageViewer.showImage(imgNew);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return objectsCount;
    }

}
