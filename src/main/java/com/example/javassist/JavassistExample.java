package com.example.javassist;

import javassist.*;

public class JavassistExample {
    public static void main(String[] args) throws Exception {
        // 1. 获取类池
        ClassPool pool = ClassPool.getDefault();
        
        // 2. 创建一个新类（因为原示例中的 com.example.User 可能不存在）
        CtClass cc = pool.makeClass("com.example.User");
        
        // 3. 添加字段
        CtField nameField = new CtField(pool.get("java.lang.String"), "name", cc);
        nameField.setModifiers(Modifier.PRIVATE);
        cc.addField(nameField);
        
        // 4. 添加 getter 方法
        CtMethod getNameMethod = CtNewMethod.make(
            "public String getName() { return name; }",
            cc
        );
        cc.addMethod(getNameMethod);
        
        // 5. 添加新方法
        CtMethod newMethod = CtNewMethod.make(
            "public void sayHello() { " +
            "    System.out.println(\"Hello from Javassist!\"); " +
            "}",
            cc
        );
        cc.addMethod(newMethod);
        
        // 6. 修改现有方法（添加日志）
        CtMethod method = cc.getDeclaredMethod("getName");
        method.insertBefore("System.out.println(\"Getting name...\");");
        
        // 7. 生成修改后的类
        cc.writeFile("target/classes");
        
        // 8. 使用修改后的类
        Class<?> clazz = cc.toClass();
        Object user = clazz.getDeclaredConstructor().newInstance();
        clazz.getMethod("sayHello").invoke(user);
        clazz.getMethod("getName").invoke(user);
    }
}

