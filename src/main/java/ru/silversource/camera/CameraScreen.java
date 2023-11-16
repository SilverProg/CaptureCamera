package ru.silversource.camera;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class CameraScreen extends JFrame {

    private JLabel videoScreen;


    public CameraScreen() {

        super("Camera Capture");
        setLayout(new FlowLayout());
        videoScreen = new JLabel();
        add(videoScreen);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080,720);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JLabel getVideoScreen() {
        return videoScreen;
    }
}
