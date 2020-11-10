---
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
# backgroundImage: url('https://marp.app/assets/hero-background.jpg')
marp: true

---

<style>
img {
  display: block;
  margin: 0 auto;
}
</style>

![bg right:40% 90%](https://upload.wikimedia.org/wikipedia/zh/8/88/Java_logo.png)

# Java高级程序设计

## 自动构建

> 工欲善其事，必先利其器


---

## 自动构建 Build automation

Build automation is the process of automating the creation of a software build and the associated processes including: compiling computer source code into binary code, packaging binary code, and running automated tests.

-- Wikipedia<!-- .element align="right" -->

---


# 自动构建工具

- Make
- Rake
- Cake
- MS build
- Ant
- Gradle

---

# [Make](http://www.ruanyifeng.com/blog/2015/02/make.html)
假设a.txt来源于合并b.txt 和c.txt
``` bash
$ cat b.txt c.txt > a.txt
```
可以定义一个Makefile

``` makefile
a.txt: b.txt c.txt
    cat b.txt c.txt > a.txt
```
``` bash
$ make a.txt
```

---

# Maven


```
src
    |----main
        |----java
            |----package
    |----test
        |----java
            |----package
    |----resources
pom.xml
```

---

## 还是那个Math

```java
package edu.nju.ics;

public class Math {
    /**
     * 阶乘
     *
     * @param n
     * @return
     */
    public int factorial(int n) throws Exception {
        if (n < 0) {
            throw new Exception("负数没有阶乘");
        } else if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    /**
     * 斐波那契数列
     *
     * @param n
     * @return
     */
    public int fibonacci(int n) throws Exception {
        if (n <= 0) {
            throw new Exception("斐波那契数列从第1位开始");
        } else if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

}

``` 

---

## 还是那个MathTest
```java
package edu.nju.ics;

import org.junit.Test;
import static org.junit.Assert.*;

public class MathTest {
    @Test
    public void fibonacci() throws Exception {
        assertEquals(21, new Math().fibonacci(9));
    }

    @Test
    public void testFactorial() throws Exception {
        assertEquals(120, new Math().factorial(5));
    }
}
```

---

## 需要JUnit.jar怎么办？

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>edu.nju.ics</groupId>
    <artifactId>math</artifactId>
    <version>1.0-SNAPSHOT</version>


    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
    </dependencies>
</project>
```

pom.xml

---

## 自动化

```sh
$ mvn clean
$ mvn test
$ mvn package
```

Perfect! 

---

What‘s more?

---


自动构建你的葫芦娃


---

# END