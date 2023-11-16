package ru.silversource;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.ORB;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.utils.Converters;

import java.util.ArrayList;
import java.util.List;

public class ObjectsDetector {
    public static void detectObject(String imgUrl, String sceneUrl) {
        Mat scene = Imgcodecs.imread(sceneUrl);
        Mat object = Imgcodecs.imread(imgUrl);
        //FastFeatureDetector detector = FastFeatureDetector.create(Fea);

        //Feature2D detector = SURF.create

        MatOfKeyPoint keyPointsObject = new MatOfKeyPoint();
        MatOfPoint2f keyPointsScene = new MatOfPoint2f();

        Mat descriptorsObject = new Mat();
        Mat descriptorsScene = new Mat();

        ORB orb = ORB.create();

        orb.detectAndCompute(object,new Mat(),keyPointsObject,descriptorsObject);
        //orb.detectAndCompute(scene,new Mat(),keyPointsScene,descriptorsScene);

        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

        MatOfDMatch matches = new MatOfDMatch();

        matcher.match(descriptorsObject,descriptorsScene,matches);

        DMatch[] matchesArray = matches.toArray();

        double maxDist = 0;
        double minDist = 100;

        for(DMatch match : matchesArray) {
            double dist = match.distance;
            if(dist < minDist) {
                minDist = dist;
            }
            if(dist > maxDist) {
                maxDist = dist;
            }
        }
        double threshold = 1.2 * minDist;
        List<DMatch> goodMatchesList = new ArrayList<>();
        for(DMatch match : matchesArray) {
            if(match.distance < threshold) {
                goodMatchesList.add(match);
            }
        }
        MatOfDMatch goodMatches = new MatOfDMatch();
        goodMatches.fromList(goodMatchesList);

        //List<KeyPoint> keyPoints = keyPointsScene.toList();

/*        Mat homography = Calib3d.findHomography(
                Converters.vector_DMatch_to_Mat(goodMatchesList),
                Converters.vector_Point2f_to_Mat(keyPointsScene.toArray()),
                Calib3d.RANSAC,5);*/

        Mat outImage = new Mat();
       // Features2d.drawMatches(object,keyPointsObject,scene,keyPointsScene,goodMatches,outImage);
    }
}
