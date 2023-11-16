package ru.silversource;

import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculationObjectsTest {

    @Before
    public void setLibrary() {
        OpenCV.loadShared();
    }
    @Test
    public void getObjectsCount() {
        int count  = CalculationObjects.getObjectsCount("../set1/dubels/dubel(1).jpg");
        assertEquals(count,1);
    }
    @Test
    public void testImageViewer() throws InterruptedException {
        ImageViewer.showImage("../set1/dubels/dubel (1).jpg");
        System.out.println("OK");
        assertEquals(1,1);
    }
}