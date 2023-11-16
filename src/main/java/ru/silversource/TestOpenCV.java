package ru.silversource;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TestOpenCV {
    public static Mat loadImage(String imageURL) {
        //Imgcodecs imgcodecs = new Imgcodecs();
        return Imgcodecs.imread(imageURL);
    }

    public static void showImage(String imageURL) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        JFrame window = new JFrame("WT");
        JLabel screen = new JLabel();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        //Mat img = loadImage(imageURL);
        Mat img = Imgcodecs.imread(imageURL);
        Mat imgEmpty = img.clone();

        Mat kernel = new Mat(20, 20, CvType.CV_8UC1, new Scalar(1.0));

        int i = in.nextInt();
        while (i != 10) {
            switch (i) {
                case 0:
                    // Расширяем светлые и сужаем тёмные области.
                    Imgproc.dilate(img, imgEmpty, kernel);
                    System.out.println(i);
                    break;
                case 1:
                    // Расширяем тёмные и сужаем светлые области.
                    Imgproc.erode(img, imgEmpty, kernel);
                    System.out.println(i);

                    break;
                case 2:
                    // Конвертируем изображение в другое цветовое пространство.
                    Imgproc.cvtColor(img, imgEmpty, Imgproc.COLOR_BGR2GRAY);
                    System.out.println(i);
                    break;
                case 3:
                    // Размываем изображение (параметры ядра должны быть нечетными).
                    Imgproc.GaussianBlur(img, imgEmpty, new Size(15, 15), 0);
                    System.out.println(i);
                    break;
                case 4:
                    // Выделяем границы изображения.
                    Imgproc.Canny(img, imgEmpty, 2, 2);
                    System.out.println(i);
                    break;
                case 5:
                    // Изменяем размер изображения.
                    Imgproc.resize(img, imgEmpty, new Size(200, 200));
                    System.out.println(i);
                    break;
                case 6:
                    System.out.println(i);
                    // Обрезаем изображение.
                    //Mat imgCrop = img.colRange(400, 600).rowRange(200, 400);
                    // Обрабатываем часть изображения.
                    //Imgproc.erode(img.colRange(400, 600).rowRange(200, 400), img.colRange(400, 600).rowRange(200, 400), kernel);
                    break;
            }

            MatOfByte buf = new MatOfByte();
            Imgcodecs.imencode(".jpg", imgEmpty, buf);

            ImageIcon ic = new ImageIcon(buf.toArray());

            screen.setIcon(ic);

            window.getContentPane().add(screen);
            window.pack();
            i = in.nextInt();
        }
    }
}
