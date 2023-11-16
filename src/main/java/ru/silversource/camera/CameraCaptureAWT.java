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

public class CameraCaptureAWT extends JFrame {

    private JLabel videoScreen;
    private int cameraIndex;

    public void setCameraIndex(int cameraIndex) {
        this.cameraIndex = cameraIndex;
    }

    public CameraCaptureAWT(int cameraIndex) {

        super("Camera Capture");
        setLayout(new FlowLayout());
        videoScreen = new JLabel();
        add(videoScreen);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080,720);
        setLocationRelativeTo(null);
        setVisible(true);
        this.cameraIndex = cameraIndex;
        new Thread(this::startCamera).start();
    }

    private void startCamera() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        VideoCapture capture = new VideoCapture(cameraIndex);

        capture.set(Videoio.CAP_PROP_FRAME_WIDTH, 1080);
        capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 720);

        Mat frame = new Mat();
        MatOfRect face = new MatOfRect();

        while (true) {
            capture.read(frame);

            BufferedImage image = matToBufferedImage(frame);

            videoScreen.setIcon(new ImageIcon(image));

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private BufferedImage matToBufferedImage(Mat frame) {
        //System.out.println(frame.size());
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_3BYTE_BGR);

        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        frame.get(0,0,data);

        return image;
    }
}
