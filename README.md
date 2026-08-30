# Java Examples

这个模块包含 Java 基础库、工具库以及 DESMO-J 离散事件模拟示例。它可以独立于课程幻灯片仓库编译和运行。

## 项目结构

```
src/
└── main/
    └── java/
        └── com/
            └── example/
                ├── h2/              # H2 内存数据库示例
                │   └── H2Example.java
                ├── asm/              # ASM 字节码操作示例
                │   ├── ASMExample.java
                │   └── MethodCounter.java
                └── javassist/        # Javassist 字节码操作示例
                    └── JavassistExample.java
```

## 运行示例

### 1. 编译项目

```bash
mvn compile
```

### 2. 运行 H2 示例

```bash
mvn exec:java -Dexec.mainClass="com.example.h2.H2Example"
```

### 3. 运行 ASM 示例

```bash
mvn exec:java -Dexec.mainClass="com.example.asm.ASMExample"
```

### 4. 运行 Javassist 示例

```bash
mvn exec:java -Dexec.mainClass="com.example.javassist.JavassistExample"
```

## 依赖说明

所有依赖都在 `pom.xml` 中定义：

- **H2**: 内存数据库
- **Javassist**: 字节码操作库
- **ASM**: 底层字节码操作库

## 注意事项

- 确保已安装 Maven 3.6+
- Java 版本要求：Java 17+
- Javassist 示例会动态创建类，运行后会在 `target/classes` 目录生成类文件

## DESMO-J 银行排队模拟

`com.example.desmoj.BankQueueSimulation` 是 `src/NS1.SIM` 的 Java 重写版本。它使用 DESMO-J 的过程模型和二维动画扩展，模拟顾客依次经过出纳员队列和收银员队列。

```bash
mvn compile
JAVA_HOME=$(/usr/libexec/java_home -v 17) mvn exec:java -Dexec.mainClass=com.example.desmoj.BankQueueSimulation
```

程序会在 `target/desmoj-bank-queue/` 生成报告和 `bank-queue.cmds` 动画命令文件，并打开 DESMO-J 二维查看器。无图形界面的环境可以使用：

```bash
JAVA_HOME=$(/usr/libexec/java_home -v 17) mvn -Djava.awt.headless=true exec:java -Dexec.mainClass=com.example.desmoj.BankQueueSimulation
```

二维动画中会显示顾客在两个队列、两个服务台和出入口之间的移动。DESMO-J 2.5.1e 官方二进制包未发布到 Maven Central，项目通过 `lib/desmoj-2.5.1e.jar` 固定依赖；该框架采用 Apache License 2.0，来源和文档见 [DESMO-J 官网](https://desmoj.sourceforge.net/)。二维查看器依赖 `java.applet`，在 Java 26 中不可用，因此必须使用 Java 17 运行查看器。
