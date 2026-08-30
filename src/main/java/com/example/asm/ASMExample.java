package com.example.asm;

import org.objectweb.asm.ClassReader;

import java.io.IOException;

public class ASMExample {
    public static void main(String[] args) throws IOException {
        // 示例：统计 String 类的方法数量
        ClassReader cr = new ClassReader("java.lang.String");
        MethodCounter counter = new MethodCounter();
        cr.accept(counter, 0);
        System.out.println("String 类的方法数量: " + counter.getMethodCount());
        
        // 统计自定义类的方法数量
        try {
            ClassReader cr2 = new ClassReader("com.example.h2.H2Example");
            MethodCounter counter2 = new MethodCounter();
            cr2.accept(counter2, 0);
            System.out.println("H2Example 类的方法数量: " + counter2.getMethodCount());
        } catch (IOException e) {
            System.out.println("无法读取类: " + e.getMessage());
        }
    }
}

