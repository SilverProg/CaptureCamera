package ru.silversource;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;

public class ImageViewer {

    public static void showImage(String imageURL) throws InterruptedException {
        JFrame window = new JFrame("WT");
        JLabel screen = new JLabel();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        Mat img = Imgcodecs.imread(imageURL);

        MatOfByte buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", img, buf);

        ImageIcon ic = new ImageIcon(buf.toArray());

        screen.setIcon(ic);

        window.getContentPane().add(screen);
        window.pack();
    }

    public static void showImage(Mat img) throws InterruptedException {
        JFrame window = new JFrame("WT");
        JLabel screen = new JLabel();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        MatOfByte buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", img, buf);

        ImageIcon ic = new ImageIcon(buf.toArray());

        screen.setIcon(ic);

        window.getContentPane().add(screen);
        window.pack();
    }
}
