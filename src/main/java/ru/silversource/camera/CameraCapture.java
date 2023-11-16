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

public class CameraCapture extends JFrame {

    private int cameraIndex;
    private Mat frame;
    private VideoCapture capture;

    public CameraCapture(int cameraIndex) {
        this.cameraIndex = cameraIndex;
        new Thread(this::startCamera).start();
    }

    public void setCameraIndex(int cameraIndex) {
        this.cameraIndex = cameraIndex;
    }

    private void startCamera() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        capture = new VideoCapture(cameraIndex);

        capture.set(Videoio.CAP_PROP_FRAME_WIDTH, 1080);
        capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 720);


    }

    public ImageIcon getImage() {
        frame = new Mat();
           // MatOfRect face = new MatOfRect();
        capture.read(frame);
        BufferedImage image = matToBufferedImage(frame);

        return new ImageIcon(image);
    }

    private BufferedImage matToBufferedImage(Mat frame) {
        //System.out.println(frame.size());
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_3BYTE_BGR);

        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        frame.get(0,0,data);

        return image;
    }
}
