package com.example.javacv;

import org.bytedeco.javacv.*;

public class WebcamExample {
    public static void main(String[] args) throws FrameGrabber.Exception {
        // 1. 打开摄像头
        FrameGrabber grabber = FrameGrabber.createDefault(0);
        grabber.start();

        // 2. 创建窗口显示
        CanvasFrame canvas = new CanvasFrame("摄像头");
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        // 3. 循环读取帧
        while (canvas.isVisible()) {
            Frame frame = grabber.grab();
            if (frame != null) {
                canvas.showImage(frame);
            }
        }

        // 4. 释放资源
        grabber.stop();
        canvas.dispose();
    }
}
