package ru.silversource;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import ru.silversource.camera.CameraCapture;
import ru.silversource.camera.CameraCaptureAWT;
import ru.silversource.camera.CameraScreen;
import ru.silversource.camera.SaveFrameOnMotion;

import javax.swing.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("START PROG");

        OpenCV.loadShared();
        /*try {
            ImageViewer.showImage(Imgcodecs.imread("../set1/dubels/dubel (1).jpg"));
            //ImageViewer.showImage("../set1/dubels/dubel (1).jpg");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //System.out.println(CalculationObjects.getObjectsCount("../set1/dubel/dubel111.jpg"));

        /*CameraCaptureAWT camera1 = new CameraCaptureAWT(0);
        System.out.println(camera1.getWidth());*/
        //SwingUtilities.invokeLater(CameraCaptureAWT::new);
        //CameraCaptureAWT.setCameraIndex(1);
        //SwingUtilities.invokeLater(CameraCaptureAWT::new);
        /*CameraCapture capture = new CameraCapture(0);
        CameraScreen screen = new CameraScreen();
        screen.setCapture(capture);*/

        SwingUtilities.invokeLater(SaveFrameOnMotion::new);
        String urlIn = "../set1/dubel/dubel (2).jpg";
        String urlOut = "../test_outs/test2.jpg";

    }

}
