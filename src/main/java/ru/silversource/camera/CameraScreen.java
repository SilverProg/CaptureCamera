package ru.silversource.camera;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class CameraScreen extends JFrame {

    private JLabel videoScreen;
    private JButton button;
    private CameraCapture capture;


    public CameraScreen() {

        super("Camera Capture");
        setLayout(new FlowLayout());
        videoScreen = new JLabel();
        add(videoScreen);

        button = new JButton("НАЖМИ");
        /*button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    //System.out.println(e.getButton());
                    videoScreen.setIcon(capture.getImage());
                }
            }
        });*/

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                videoScreen.setIcon(capture.getImage());
            }
        });

        this.add(button);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void setCapture(CameraCapture capture) {
        this.capture = capture;
    }

    public JLabel getVideoScreen() {

        return videoScreen;
    }


}
