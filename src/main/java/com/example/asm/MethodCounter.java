package com.example.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodCounter extends ClassVisitor {
    private int methodCount = 0;
    
    public MethodCounter() {
        super(Opcodes.ASM9);
    }
    
    @Override
    public MethodVisitor visitMethod(
        int access, String name, String descriptor,
        String signature, String[] exceptions) {
        methodCount++;
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
    
    public int getMethodCount() {
        return methodCount;
    }
}

