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

## 类型和自省

---

## SOLID


- 高内聚: S 单一职责原则，I 接口隔离原则 

- 低耦合: O 开闭原则，D 依赖倒置原则，L 里氏替换原则

<div style="text-align:center; size:24pt"> 抽象！</div>

---

## 回顾一下抽象


```java
class Shape{  
    void draw(){ System.out.println("Draw Shape"); }  
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
             s.draw();  // without knowing the concrete type of "s"
         }  
     }  
} 
```


---

## What if?

```java
abstract class Shape{  
    void draw(){
        System.out.println("Draw Shape");
    }  
    abstract void rotate();
}  
public class Test {  
     public static void main(String[] args) {  
         Shape[] shapes = {new Circle(), new Triangle(), new Square()};  
         for(Shape s : shapes){  
             // if s is not a circle 
             s.rotate();  // circle.rotate() means nothing!
         }  
     }  
} 
```

---

## RTTI
 

如何在不破坏抽象的设计原则下，获得对象的实际类型？

Run-time type identification



---

## The `Class` Object


每一个类都持有其对应的`Class`类的对象的引用，其中包含着与类相关的信息

<small>https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html</small>

`Object`类中的`getClass()`能让我们获取到这个对象

<small>https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#getClass</small>

---

## 试一下


---

## 思考一下

`Class`对象内的信息来自哪里？

针对每一个类，编译Java文件会生成一个二进制.class文件，这其中就保存着该类对应的Class对象的信息。

Java虚拟机运行你的代码的第一步，就是先加载你的.class文件


---

## 三步曲

1. 加载(Loading)。由类加载器执行，查找字节码，创建一个Class对象。
2. 链接(Linking)。验证字节码，为静态域分配存储空间，如果必需的话，会解析这个类创建的对其他类的所有引用（比如说该类持有static域）。
3. 初始化(Initializing)。如果该类有超类，则对其初始化，执行静态初始化器和静态初始化块。

---

## 换而言之


- 所有的类都是在对其第一次使用时，动态加载到JVM中去的
- 当程序创建第一个对类的静态成员的引用时，JVM会使用类加载器来根据类名查找.class文件（实际上很复杂，后面专门讲类加载机制）
- 一旦某个类的`Class`对象被载入内存，它就被用来创建这个类的所有对象
- 构造器也是类的静态方法，使用`new`操作符创建新对象会被当作对类的静态成员的引用


---

## Inside JVM

![](https://www.artima.com/insidejvm/ed2/images/fig5-7.gif)

---

`Class.forName(String str)`

`Class`类的静态方法`forName(String str)`，可以让我们对于某个类不进行创建就得到它的`Class`对象的引用

```java
try {
     Class circleClass = Class.forName("Circle");
} catch (ClassNotFoundException e) {

}
```

缺点是什么？

可能有异常，且会触发类的static子句（静态初始化块）

---

## 类字面常量 Class literals 

Class literals also provide a reference to the Class object

```java
Class c = Circle.class;
```


支持编译时检查，所以不会抛出异常;不会触发类的static子句（静态初始化块）

更简单更安全更高效

---

## 基本类型的Class对象

Each object of a primitive wrapper class has a standard field called TYPE that also provides a reference to the Class object

<small>https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#TYPE</small>

---

## 类型检查

`public boolean isInstance​(Object obj)`

> Determines if the specified Object is assignment-compatible with the object represented by this Class.

```java
if ( Shape.class.isInstance(x) ) {
     Shape s = (Shape)x;
}
```


---

## 或者

也可以使用`instanceof`关键字进行类型检查

```java
if ( x instanceof Shape ) {
     Shape s = (Shape)x;
}
```

---

## 从Class对象创建对象

```java
try {
    Class<?>  circleClass = Class.forName("Circle"); 
    Object obj = circleClass.newInstance();
} catch (ClassNotFoundException e) {

}

//or 
Class<?> circleClass = Circle.class;
Object obj = circleClass.newInstance();

```

只能得到Object类型... &lt;?&gt;又是什么鬼？

---

## 创建出实际类型的对象

```java
Class<Circle> circleClass = Circle.class;
Circle obj = circleClass.newInstance();
```

Class&lt;Circle&gt; &rarr; 范型（generic type）下次再说

---

## 问题

以上使用的RTTI都具有一个共同的限制：在编译时，编译器必须知道所有要通过RTTI来处理的类。

但有的时候，你获取了一个对象引用，然而其对应的类并不在你的程序空间中，怎么办？比如说你从磁盘文件或者网络中获取了一串字串，并且被告知这一串字串代表了一个类，这个类在编译器为你的程序生成代码之后才会出现。


---

## 子曰


> 见贤思齐焉，见不贤而内自省也


---

## 曾子曰

> 吾日三省吾身，为人谋而不忠乎？与朋友交而不信乎？传不习乎？


---

## 自省 

自省 Introspection： 自我评价、自我反省、自我批评、自我调控和自我教育


---

## 计算机技术中的introspection


是程序能在运行时检查对象的类型和属性的能力


In computing, type introspection is the ability of a program to examine the type or properties of an object at runtime.

-- Wikipedia

---

## Reflection

In computer science, reflection is the ability of a computer program to examine, introspect, and modify its own structure and behavior at runtime.

<div style="text-align:right">-- Wikipedia</div>

Introspection是Reflection的子集。反射除了检查，还可以控制改变。

---

## Java Reflection

Java supports introspection through its reflection library


---

## 有啥用？ 

- Rapid Application Development (RAD)
- Visual approach to GUI development
- Requires information about component at run-time
- Remote Method Invocation (RMI)
- Distributed objects



---

## Introspection in Python

The Inspect module provides introspections mechanism

https://docs.python.org/3/library/inspect.html


---


![bg 50%](https://www.desicomments.com/wp-content/uploads/2017/02/Thank-You-Image.png)