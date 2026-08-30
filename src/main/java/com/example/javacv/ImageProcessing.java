package com.example.javacv;

import org.bytedeco.opencv.opencv_core.Mat;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class ImageProcessing {
    public static void main(String[] args) {
        // 1. 读取图像
        Mat image = imread("input.jpg");

        // 2. 转换为灰度图
        Mat gray = new Mat();
        cvtColor(image, gray, COLOR_BGR2GRAY);

        // 3. 高斯模糊
        Mat blurred = new Mat();
        GaussianBlur(gray, blurred, new org.bytedeco.opencv.opencv_core.Size(15, 15), 0);

        // 4. 保存结果
        imwrite("output.jpg", blurred);
    }
}
