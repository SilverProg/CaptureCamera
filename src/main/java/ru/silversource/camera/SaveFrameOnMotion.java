package ru.silversource.camera;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.BackgroundSubtractorMOG2;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.List;

public class SaveFrameOnMotion extends JFrame {

    private VideoCapture capture;
    private Mat currentFrame;
    private Mat previousFrame;
    private BackgroundSubtractorMOG2 backgroundSubtractor;
    private JLabel videoScreen;

    public SaveFrameOnMotion() {
        setTitle("Save Frame On Motion");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        videoScreen = new JLabel();
        panel.add(videoScreen);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setVisible(true);

        // Инициализация библиотеки OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Открываем веб-камеру
        capture = new VideoCapture(0);
        capture.set(Videoio.CAP_PROP_FRAME_WIDTH, 640);
        capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 480);

        // Инициализируем фоновый вычитатель (BackgroundSubtractor)
        backgroundSubtractor = Video.createBackgroundSubtractorMOG2();

        // Запускаем поток для получения и отображения видеопотока
        new Thread(this::startCamera).start();
    }

    private void startCamera() {
        currentFrame = new Mat();
        previousFrame = new Mat();
        int i = 0;
        while (true) {
            // Захватываем кадр
            capture.read(currentFrame);

            // Преобразуем кадр в оттенки серого
            Imgproc.cvtColor(currentFrame, currentFrame, Imgproc.COLOR_BGR2GRAY);

            // Если это первый кадр, сохраняем его
            if (previousFrame.empty()) {
                previousFrame.release();
                previousFrame = currentFrame.clone();
                continue;
            }

            // Вычисляем разницу между текущим и предыдущим кадром
            Mat diff = new Mat();
            Core.absdiff(previousFrame, currentFrame, diff);

            // Применяем фоновый вычитатель для дополнительной фильтрации
            Mat fgMask = new Mat();
            backgroundSubtractor.apply(diff, fgMask);

            // Ищем контуры движущихся объектов
            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            Imgproc.findContours(fgMask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

            // Если есть контуры (движение обнаружено), сохраняем кадр
            if (!contours.isEmpty()) {
                //
                System.out.println(i++);
                viewCurrentFrame();
            }

            // Освобождаем ресурсы
            fgMask.release();
            diff.release();
            hierarchy.release();

            // Обновляем предыдущий кадр
            previousFrame.release();
            previousFrame = currentFrame.clone();

            // Преобразуем Mat в BufferedImage для отображения
            BufferedImage image = matToBufferedImage(currentFrame);

            // Обновляем изображение в JLabel
            videoScreen.setIcon(new ImageIcon(image));

            // Задержка для стабильного отображения
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void viewCurrentFrame() {
        videoScreen.setIcon(new ImageIcon(matToBufferedImage(currentFrame)));
    }

    private void saveCurrentFrame() {
        // Генерируем уникальное имя файла (можешь использовать свою логику)
        String filename = "../opencv_frames/motion_frame_" + System.currentTimeMillis() + ".png";

        // Сохраняем текущий кадр в файл
        Imgcodecs.imwrite(filename, currentFrame);

        System.out.println("Кадр сохранен при обнаружении движения: " + filename);
    }

    private BufferedImage matToBufferedImage(Mat frame) {
        // Создаем пустое изображение
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_3BYTE_BGR);

        // Получаем массив байт из Mat
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        frame.get(0, 0, data);

        return image;
    }


}