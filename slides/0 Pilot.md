---
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
# backgroundImage: url('https://marp.app/assets/hero-background.jpg')
marp: true
---

![bg right:40% 90%](https://upload.wikimedia.org/wikipedia/zh/8/88/Java_logo.png)
# Java高级程序设计


---

# 先谈三点

- 什么是Java
- 为什么学Java
- 怎么学Java

![bg right:50% fit](https://why-what-how.com/images/Why-What-How.gif)

---

# Java

![bg right:40%](https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/James_Gosling_2008.jpg/440px-James_Gosling_2008.jpg)

Java是一种广泛使用的计算机编程语言，拥有跨平台、面向对象、泛型编程的特性，广泛应用于企业级Web应用开发和移动应用开发。

一般公认为詹姆斯·高斯林（英语：James Gosling，1955年5月19日－，加拿大软件专家）为“Java之父”

-- https://zh.wikipedia.org/wiki/Java


---
# 历史



![bg 80% right:30%](https://upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Sun-Logo.svg/440px-Sun-Logo.svg.png)

  任职于Sun微系统的詹姆斯·高斯林等人于1990年代初开发新型语言Oak，目标设置在家用电器等小型系统的编程语言，应用在电视机、电话、闹钟、烤面包机等家用电器的控制和通信，后该项目被放弃了该项计划。
  
  随着1990年代互联网的发展，Sun公司看见Oak在互联网上应用的前景，于1995年5月以Java的名称正式发布。

---

# Java特性

Java编程语言是个简单、面向对象、分布式、解释性、健壮、安全与系统无关、可移植、高性能、多线程和动态的语言。

Java不同于一般的编译语言或解释型语言。它首先将源代码编译成字节码，再依赖各种不同平台上的虚拟机来解释执行字节码，具有“**一次编写，到处运行**”跨平台特性。

![bg right:35% fit](https://i2.wp.com/bhivetech.blog/wp-content/uploads/2020/06/JVM_independent.png)

---
# Java与Internet

  1995年发布HotSpot浏览器。
  
  支持运行Java Applet。
  
  同时得到Netscape浏览器支持。

![bg right:55% fit](https://upload.wikimedia.org/wikipedia/commons/4/46/MainPage-HotJava3-Optim.png)

---

# Applet

相当于现在的微信小程序

```java
import java.applet.Applet;
import java.awt.*;
public class HelloWorld extends Applet {
    g.drawString("Hello, world!", 20,10);
  }
}
```
```html
<HTML><BODY>
<P>My first Java applet says: 
<APPLET code="HelloWorld.class" WIDTH="200" HEIGHT="40">
</APPLET></P>
</BODY></HTML>
```

![bg right:40% fit](https://www3.ntu.edu.sg/home/ehchua/programming/java/images/Applet_HelloWorld.gif)

---

# 之后

在流行几年之后，Java在浏览器中的地位被逐步侵蚀。

但在万维网的服务器端和手持设备上，Java变得更加流行。

很多网站在后端使用JSP和其他的Java技术。


![bg right:50% fit](https://upload.wikimedia.org/wikipedia/commons/5/5a/Jsxp_arch.png)

---

# 为什么要学Java

找工作
缺学分
交朋友
...

---

# Java应用广泛

- Java可以写桌面、可以写Android、可以写服务端、可以写企业级业务、可以写互联网业务...
- Java是在开发效率、开发门槛、性能、跨平台这几方面平衡教（或最）好的语言
- Java是一个门槛低但上限也很高的语言

---


<iframe src="https://madnight.github.io/githut/#/pull_requests/2020/2" width=100% height="100%"></iframe>

---


# Java Editions
- Java Standard Edition (J2SE), to develop client-side standalone applications or applets
- Java Enterprise Edition (J2EE), to develop server-side applications such as Java servlets and Java ServerPages
- Java Micro Edition (J2ME), to develop applications for mobile devices such as cell phones



---

# 本课程去年考试情况

|&nbsp;&nbsp;&nbsp;&nbsp;分数段&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;人数&nbsp;&nbsp;|&nbsp;&nbsp;比例&nbsp;&nbsp;|
|:--:|--:|--:|
|100-90|11|18%|
|89-80|16|27%|
|79-70|14|23%|
|69-60|11|18%|
|59-0|8|14%|

[去年试卷](https://www.zhihu.com/question/392342294)

---


# 课程作业

日常作业：葫芦娃系列
大作业：葫芦娃大战妖精


![bg right:50% 150%](https://img.dujin.org/uploads/2019/05/huluxiongdi.jpg)


---

![bg fit](https://njuics.github.io/java-2019/content/images/1/1.png)


---

![bg fit](https://njuics.github.io/java-2019/content/images/1/tang.gif)

---

![bg fit](https://njuics.github.io/java-2019/content/images/1/lu.gif)


---




<style>
video {
  display: block;
  margin: 0 auto;
}
</style>

<video height="600px" controls>
  <source src="https://cdn.njuics.cn/java20/demo1.mp4" type="video/mp4">
  Your browser does not support the video tag.
</video>

---


<style>
video {
  display: block;
  margin: 0 auto;
}
</style>

<video height="600px" controls>
  <source src="https://cdn.njuics.cn/java20/demo2.mp4" type="video/mp4">
  Your browser does not support the video tag.
</video>

---


<style>
video {
  display: block;
  margin: 0 auto;
}
</style>

<video height="600px" controls>
  <source src="https://cdn.njuics.cn/java20/demo3.mp4" type="video/mp4">
  Your browser does not support the video tag.
</video>

---

# 交个朋友

- 交流
- 签到
- 答疑
- 民调
- 考试
- ...

![bg right:40%](https://cdn.njuics.cn/java20/qq.png)

---


# 前导课程要求

- 至少一门高级程序设计语言
- 如果C++学得很好，特别是面向对象编程思想领悟到位，建议不选
- **不接受免修不免考**
 
![bg 90% right:55%](https://st.depositphotos.com/1756445/4385/i/950/depositphotos_43853619-stock-photo-word-cloud-programming-languages-or.jpg)


---

# 后续课程

- 软件工程综合实验
- 大数据及大数据综合实验
- 软件体系结构
- 设计模式
- 等等


![bg right:50% 100%](https://previews.123rf.com/images/olechowski/olechowski1311/olechowski131100054/23796130-big-data-concept-in-word-tag-cloud-on-white-background.jpg)

---

# 课程网站

![bg right:50%](https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png)

https://njuics.github.io/java20

---

# 教材/参考书

《On Java 8》 by Bruce Eckel

https://github.com/LingCoder/OnJava8


> 讲课的目的是对书本内容进行提要，听课并不能个替代你自己读书的过程。

![bg right:50% 60%](https://cdn.njuics.cn/java20/onjava8.png)



---

# 大纲 -I



1. 语言基础 (基本数据类型, 运算符, 控制流）
2. 面向对象I （抽象、类、接口、封装、继承、多态等)
3. 面向对象II (初始化与清理、访问权限、内部类等)
4. 设计原则 (SOLID)


--- 

# 大纲 -II

5. 类加载和自省
6. 异常处理
7. 集合类型
8. 泛型
9. 标注和测试（Annotations/Testing）
10. 工程工具（Maven/Gradle/CI）

---

# 大纲 -III

11. 文件与输入输出（NIO）
12. 流式编程 (Stream)
13. 函数式编程
14. 并发编程
15. 图形化（JavaFX）
16. 设计模式（Design Pattern）


---

# 分工

:man:
- 1/2/3/5/8/9/10/13 

:woman:
- 4/6/7/11/12/14/15/16


---

# Hello World

Hello, World是指在电脑屏幕显示“Hello, World!”（你好，世界！）字符串的计算机程序。相关的程序通常都是每种电脑编程语言最基本、最简单的程序，也会用作示范一个编程语言如何运作[1]。同时它亦可以用来确认一个编程语言的编译器、程序开发环境及运行环境是否已经安装妥当。


![bg left](https://upload.wikimedia.org/wikipedia/commons/2/21/Hello_World_Brian_Kernighan_1978.jpg)


----

# Java SDK

- JDK 1.02 (1995)
- Java 2 SDK v 1.2 (a.k.a JDK 1.2, 1998)
- Java 2 SDK v 1.4 (a.k.a JDK 1.4, 2002)
- ...
- Java [8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html), 9, 10, 11, 12, [14](https://docs.oracle.com/en/java/javase/14/install/overview-jdk-installation.html)

![bg right:50%](https://s3.shunyafoundation.com/s3/1578452c3f66d8fd0d04d5d195328ae1359d8caa/jdk-jvm.png)


[Oracle JDK vs. OpenJDK](https://www.baeldung.com/oracle-jdk-vs-openjdk)

---

# VS Code + Java Extension Pack

<style>
img {
  display: block;
  margin: 0 auto;
}
</style>

![height:500px](https://raw.githubusercontent.com/redhat-developer/vscode-java/master/images/vscode-java.0.0.1.gif)


---

# Hello world

``` java
public class HelloWorld{
     public static void main(String []args){
        System.out.println("Hello World");
     }
}
```
```bash
$ javac HelloWorld.java
$ java -Xmx128M -Xms16M HelloWorld
Hello World
```
---

# Playground
https://www.tutorialspoint.com/compile_java8_online.php

<iframe src="https://www.tutorialspoint.com/compile_java8_online.php" width=100% height="70%"></iframe>

---

![bg 50%](https://www.desicomments.com/wp-content/uploads/2017/02/Thank-You-Image.png)