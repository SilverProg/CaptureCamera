package ru.silversource;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import ru.silversource.camera.CameraCapture;
import ru.silversource.camera.CameraCaptureAWT;
import ru.silversource.camera.CameraScreen;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        CameraCapture capture = new CameraCapture(0);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CameraScreen screen = new CameraScreen();
        JLabel videoScreen = screen.getVideoScreen();

        //capture.
        videoScreen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println(e.getX());
                    videoScreen.setIcon(capture.getImage());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        videoScreen.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    System.out.println(e.getKeyCode());
                    videoScreen.setIcon(capture.getImage());
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        String urlIn = "../set1/dubel/dubel (2).jpg";
        String urlOut = "../test_outs/test2.jpg";

    }
}
