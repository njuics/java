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

## 面向对象编程

---

# 面向对象编程


面向对象编程首先认识待解决问题所涉及的基本对象和他们间的相互关系，然后将这些现实对象映射到计算机中，实现计算机对现实问题的模拟，将“问题空间”直接映射到“解空间”。

---

# Class

- 对象的“生成模板”
  + 属性（状态）
  + 方法（行为）

```java
class Human {
    int age;
    boolean gender;
    void walk() {};
}
```

---

# UML

![bg 50%](https://www.plantuml.com/plantuml/png/Iyv9B2vMy2ZDJSnJgEPI08BCl1A5nFHKQp0dAJy_9nKebPwQbv9Q114hoyzCKIXFpCdMqBJcgWK0)  

![bg 50%](https://s.plantuml.com/logoc.png) 

<br><br>
<br>
<br>
<br>

<div style="text-align: right;">[PlantUML](http://www.plantuml.com/)</div>

---

## 初始化（构造）

从”对象模版“生成“对象实例“

```java
Human you = new Human();
```

- 每个对象拥有一份属性拷贝
- 对象共享方法（代码）的定义
- 每个对象有一个标识（id，内存地址）
- 通过对象引用（Object Reference）可访问对象方法和属性


---

## 构造函数（Constructor）

```java
class Human {
    int age;
    boolean gender;
    Human(){ //default constructor
        age = 0;
        gender = false;
    }
    Human(int age){ //overloading
        gender = true;
        this.age = age;
    }
    Human(boolean gender){
        this(0) //calling constructors from constructors
        this.gender = gender;
    }
}

```

---

## 成员变量初始化 （P185）

按在类中定义顺序逐个初始化（或赋予默认值），然后执行构造函数。

---

## 静态变量


```java
class Human {
    static int total;

    int age;
    boolean gender;
    Human(){ //default constructor
        Human.total++;
        age = 0;
        gender = false;
    }
    ...
}

```

---

## Static Blocks
```java
class Human {
    static int total;
    static{
        total = 2; //Adam and Eve
    }
    int age;
    boolean gender;
    Human(){ //default constructor
        Human.total++;
        age = 0;
        gender = false;
    }
    ...
}

```

---

## Instance initialization

```java
class Man {
    Object girlfriend;
    {
        girlfriend = new Dog();
    }
    ...
}

```

---

## 数组初始化
PP. 193-204

---

## Enum 怎么理解？

```java
enum GENDER {
    MALE, FEMALE, NOTTELLING
}

public static void main(String[] args){
    GENDER gender = GENDER.MALE;
    System.out.println(gender.toString() + " " + gender.ordinal())
}
```

---

## 对象消亡

- 垃圾回收（Garbage Collection）
  + Stop-and-Copy
  + Mark-and-Sweep

vs. [Reference Counting](https://en.wikipedia.org/wiki/Reference_counting)

---

## Stop-and-Copy

![](https://www.memorymanagement.org/_images/two-space.svg)

---

## Mark-and-Sweep

![](https://3.bp.blogspot.com/-LNBSfvedEEM/VkxZomXBkvI/AAAAAAAAACI/EhsDaD0k-1g/s1600/output_oPN0rV.gif)

---

## package

```bash
Huam.java
```
```java
package cn.edu.nju.java;

class Human{

}
```
```bash
Society.java
```
```java
import cn.edu.nju.java.Human;

```

---

## CLASSPATH

```bash
CLASSPATH=.:CLASSPATH=/path/to/some/folder:/path/to/other.jar
```

```bash
/path/to/some/folder/cn/edu/nju/java/Human.class

java Human
```

<span style="color:red">Why?</span> <!-- .element: class="fragment" -->

The class loading problem.<!-- .element: class="fragment" -->

---

## package

<span style="color:red">Why?</span> <!-- .element: class="fragment" -->

组织管理、避免冲突、访问控制 （PP. 210-220) <!-- .element: class="fragment" -->


---

## 访问控制

- Modifier
  + public
  + protected
  + private
  + _default_

---

## public

```java

enum Appearance {
    BEAUTIFUL, UGLY
}
public class Human{
    public Appearance appearance;
}
```

Interface access

---

## protected

```java
public class Human{
    protected float money;
}
```
<small>注：此例并不恰当</small>

Inheritance access 

---

## private

```java
enum Mood {
    GOOD, BAD
}
public class Human{
    private Mood mood;
}
```

You can't touch that!

---

## default


```java

enum Performance {
    GOOD, BAD
}
public class Human{
    Performance performance;
}
```

Package friendly

---

## Modifiers

- class
- member
- method

<span style="color:red">Why?</span> <!-- .element: class="fragment" -->

封装  <!-- .element: class="fragment" -->

---

## Encapsulation


Encapsulation is to hide the implementation details from users

![](https://alssl.askleomedia.com/wp-content/uploads/2009/01/cpu-600x490.jpg) <!-- .element height="40%" width="40%" --> 


<span style="color:red">Why?</span> <!-- .element: class="fragment" -->

---

## Encapsulating for

![](https://qph.ec.quoracdn.net/main-qimg-00d9d179877f83e537c65a770cd052e1.webp)

Flexibility, Reusability, Maintainability.


---

## 代码重用（复用）

- Composition
- Inheritance
- Delegation


---

## Composition

```java
public class Heart {
    ...
}

public class Liver {
    ...
}

public class Human {
    private Heart battery;
    private Liver screen;
    ...
}

```

---

## Composition in UML
![](https://www.plantuml.com/plantuml/png/yoZDJSnJK39KKj3IrGNJ7gc9HILS7XZYFfbbgKK0)

“部分”的生命期不能比“整体”还要长

--- 

## 弱合成

- Aggregation
- Association


---

## Aggregation

![](https://www.plantuml.com/plantuml/png/SquiKb0oL5B8rzK5qwvvwPbvgLpEoC8cIWhX6QcfEG00)


“部分”可独立存在


---

## Association

![](https://www.plantuml.com/plantuml/png/2qZDByX9LNZKC4VhKSDS1LrT4DCGn0pFByhEp4iFo4bCJWK0)

对象可以向另一个对象通过某种方式发送消息

---

## Inheritance

![](https://www.plantuml.com/plantuml/png/SquiKh2fqTLLS4hDgm80)

Initialize the base first


--- 

## Delegating
```java
public class Car {
    public Window[] windows = new Window[4];
}

public class Jetta extends Car{

}

Car myCar = new Jetta();

//手摇式玻璃
myCar.windows[0].open();

```


--- 

## Delegating
```java
public class Car {
    public Window[] windows = new Window[4];
}

public class Tesla extends Car{
    public void openWindow(int i){
        this.windows[i].open(); //delegate
    }
}

Car hisCar = new Tesla();

//自动式玻璃
hisCar.openWindow(1);

```

--- 

## 代码复用？

![](https://developer.apple.com/library/content/documentation/General/Conceptual/CocoaEncyclopedia/Art/delegation1.jpg)

---

## A system class

```java

public class Window {
    private Button btnClose;

    private void btnCloseClicked(){
        ///???
    }
}

```

---

## Customizing it by extending it

```java

public class MyWindow extends Window {
    private Button btnClose;

    private void btnCloseClicked(){
        // bala bala
    }
}

```

---

## Or implementing a delegate

```java

public class Window {
    private Button btnClose;
    
    private WindowDelegate delegate;

    private void btnCloseClicked(){
        delegate.windowClosing();
    }
}

public class WindowDelegate {
    public windowClosing(){
        //bala bala
    }
}

```


<span style="color:red">Why?</span> <!-- .element: class="fragment" -->

---

## final 

pp262-271


---

## final的变量



常量声明(经常和static关键字一起使用)

```java
public static final int i = 0;

i=1; // compilation errorerror

```

---

## final方法

代表这个方法不可以被子类的方法重写。

```java
class Man extends Human{
    public final void hitChild(){
        //beat and KO!
    }
}
 
class Father extends Man{
    @Override
    public final void hitChild(){
        // beat harder ...
        return // compilation error: overridden method is final
    }
}
```

“父类中这件事已经做得够好了，子类无法超越”


---

## final类


```java
final class Father extends Man{ 

}

class Son extends Father{ //compilation error: cannot inherit from final class

}
```

“老子整个已经不可超越”


---


## Polymorphism


多态性是指对象能够有多种形态。

- 男人是人
- 男人是动物
- 男人是一种存在

---

## 多态是”继承“的产物

```java

class Man extends Human {
    ...
}

class Human extends Creature{
    ...
} 

class Creature extends Being{
    ...
}
```

---

## 多态意味着什么？

```java
class Creature extends Being{
    public void eat(){  System.out.println("eating"); }
}
class Human extends Creature{
    @Override
    public void eat{ System.out.println("cooking...eating") }
} 
class Woman extends Human {
    @Override
    public void eat{ System.out.println("cooking...photoing...eating"); }
}

Being you = new Woman(); 
you.eat(); //??
```

---

## 再举个例子

```java
class Shape{  
    void draw(){  System.out.println("Draw Shape"); }  
}  
class Circle extends Shape{  
    void draw(){ System.out.println("Draw Circle"); }  
}   
class Triangle extends Shape{  
    void draw(){ System.out.println("Draw Triangle"); }  
}  
class Square extends Shape{  
    void draw(){ System.out.println("Draw Square"); }  
}  
public class Test {  
     public static void main(String[] args) {  
         Shape[] shapes = {new Circle(), new Triangle(), new Square()};  
         for(Shape s : shapes){  
             s.draw();  
         }  
     }  
}  
```
执行结果？


---

## 再看一个例子
```java
class Shape{  
    private void draw(){ System.out.println("Draw Shape"); }  
      
    void show(){ draw(); }  
}  
  
class Circle extends Shape{  
    void draw(){ System.out.println("Draw Circle"); }  
}  
  
public class Test {
     public static void main(String[] args) {  
         Shape s = new Circle();  
         s.show();  
     }  
}  
```
执行结果？

---

## 多态形成的条件

- 继承
- 重写
- 父类引用指向子类对象

实际上是由Java中的”动态绑定“机制造成的。<!-- .element: class="fragment" -->

---

## 动态绑定

https://stackoverflow.com/questions/19017258/static-vs-dynamic-binding-in-java


http://www.jianshu.com/p/0677f366db08


---

## 再想想，Why？

## 抽象思维（编程）的支撑！<!-- .element: class="fragment" -->

---

## 抽象类

当一个类没有足够的信息来描述一个具体的对象，而需要其他具体的类来支撑它，那么这样的类我们称它为抽象类。


--- 

## 举个例子

```java
class Human {
    public void meetLouisVuitton(){
        //??
    }
}
```

---

## Abstract Class
```java
abstract class Human {
    public abstract void meetLouisVuitton();
}
class Man extends Human {
    @Override
    public void meetLouisVuitton(){
        pass();
    }
}
class Woman extends Human {
    @Override
    public void meetLouisVuitton(){
        enter();
    }
}

```

Why？应对不确定。<!-- .element: class="fragment" -->

---


## Interface

当所有行为都不确定时，来一份Interface。

接口比抽象类更抽象。

接口是用来建立类与类之间的协议（protocol）。


---

## 举个例子

```java
public interface Communicate{
    public String talkTo(String message);
}

public Man extends Human implements Communicate{
    ...
    public String talkTo(String message){
        return process(message);
    }
}
public Woman extends Human implements Communicate{
    ...
    public String talkTo(String message){
        return "我不听我不听我不听";
    }
}
```

---

## Why？

抽象的行为协议定义

接口与实现的分离


---

## 多继承

```java
interface A{
    public void a();
}
interface B{
    public void b();
}

class C implements A,B {
    public void a(){
        ...
    }
    public void b(){
        ...
    }
}
```
为什么Java不支持多继承多个父类但支持实现多个接口？<!-- .element: class="fragment" -->

---

## 接口中的成员变量

- 成员变量必须是`public static final`

为什么? 公有化，标准化、规范化。<!-- .element: class="fragment" -->

---


## Inner Classes

pp. 345-388

Why Inner Classes？<!-- .element: class="fragment" -->

下次再说<!-- .element: class="fragment" -->

---


## 再看葫芦娃


![](https://camo.githubusercontent.com/a5cc35d2179c5bf861f6a8eeca25fc5fa3e876a2/687474703a2f2f656e676c6973682e6372692e636e2f6d6d736f757263652f696d616765732f323030392f30362f32342f34363334636172746f6e312e6a7067)

---


# END