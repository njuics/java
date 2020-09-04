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

缺学分
找工作
交朋友
...


---

# Java应用广泛

- Java可以写桌面、可以写Android、可以写服务端、可以写企业级业务、可以写互联网业务...
- Java是在开发效率、开发门槛、性能、跨平台这几方面平衡教（或最）好的语言
- Java是一个门槛低但上限也很高的语言

---

# 怎么学Java

---

# 前导课程要求

- 至少一门高级程序设计语言
- 如果C++学得很好，特别是面向对象编程思想领悟到位，建议不选
- 不接受免修不免考
 
![bg 90% right:55%](https://st.depositphotos.com/1756445/4385/i/950/depositphotos_43853619-stock-photo-word-cloud-programming-languages-or.jpg)

---

# 教材/参考书

《On Java 8》 by Bruce Eckel

https://github.com/LingCoder/OnJava8

![bg right:50% 60%](images/onjava8.png)


---

# Installation

https://docs.oracle.com/en/java/javase/14/install/overview-jdk-installation.html

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
$javac HelloWorld.java
$java -Xmx128M -Xms16M HelloWorld
Hello World
```

https://www.tutorialspoint.com/compile_java8_online.php


---

# 交个朋友

![bg 30%](images/qq.png)
