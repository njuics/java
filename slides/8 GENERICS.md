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

## 泛型

> 这可能是这门课最难理解的一章。


---

## 泛型

泛型程序设计（generic programming）是程序设计语言的一种风格或范式。泛型允许程序员在强类型程序设计语言中编写代码时使用一些以后才指定的类型，在实例化时作为参数指明这些类型。... Ada、Delphi、Eiffel、Java、C#、F#、Swift 和 Visual Basic .NET 称之为泛型（generics）；ML、Scala 和 Haskell 称之为参数多态（parametric polymorphism）；C++ 和 D称之为模板。《Design Patterns》一书称之为参数化类型（parameterized type）。

<small>https://zh.wikipedia.org/wiki/%E6%B3%9B%E5%9E%8B</small>

---

## Java Generics

- A way to control a class type definitions
- A way of improving the clarity of code
- A way of avoiding (**casts**) in code, turning run-time errors (typically **ClassCastException**) into compile-time errors.

---

## Without Generics

```java
class Stack{
  void push(Object o){...}
  Object pop(){...}
}
String s = "Hello";
Stack st = new Stack();
...
st.push(s);
...
s = (String)st.pop();
```

---

## With Generics

```java
class Stack<A>{ // 类参数
  void push(A a){...}
  A pop(){...}
}
String s = "Hello";
Stack<String> st = new Stack<String>();
st.push(s);
...
s = st.pop();

```

---

## 葫芦娃中的例子

``` java
public class Position {
    private Object holder;
    public Position(Object holder) { 
        this.a = a; 
    }
    Object get() { 
        return a; 
    }
}
```

<div style="text-align:center; ">Postion里放任意东西</div>

---

## 改进

``` java
public class Position {
    private Creature holder;
    public Position(Creature holder) { 
        this.a = a; 
    }
    Creature get() { 
        return a; 
    }
}
```
<br>
<div style="text-align:center; ">通过构造方法和get方法我们能看出Position里放的是Creature</div>

---

## 但如果是这样呢？
``` java
public class Position {
    private Creature holder;
}
```
<br>
<div style="text-align:center; ">从外部观察，何以了解Position是放Creature的？</div>

---

## 显式说明一下

``` java
public class Position<T>{
    private T holder;
}
```
<br>
<div style="text-align:center; ">显式表达“某种不确定”: Position里是要放东西的，但不确定是什么东西</div>

---

## 再明白一点

``` java
public class Position<T extends Creature>{
    private T holder;
}
```

<div style="text-align:center; ">显式表达Position是跟某种Creature有关系的</div>


---

## 再定义一个Computer 

``` java
public class Computer{
    private HDD mHarddisk;   // 机械硬盘
    Computer(HDD harddisk){
        mHarddisk = harddisk;
    }
    public Data readDisk(){
        return mHarddisk.read();
    }
    public void writeDisk(Data data){
        mHarddisk.write(data);
    }
}
```

<div style="text-align:center; ">如果安装的是SSD怎么办？</div>

---

## 定义一个SSDComputer ？

``` java
public class SSDComputer{
    private SSD mHarddisk;   // SSD硬盘
    Computer(SSD harddisk){
        mHarddisk = harddisk;
    }
    public Data readDisk(){
        return mHarddisk.read();
    }
    public void writeDisk(Data data){
        mHarddisk.write(data);
    }
}
```

<div style="text-align:center; ">这当然不好！ </div>

---

## 抽象一下
``` java
public abstract class Disk{};
public class SSD extends Disk{};
public class HHD extends Disk{};
public class Computer{
    private Disk disk;   // 抽象的硬盘
    Computer(Disk disk){
        this.disk = disk;
    }
    public Data readDisk(){ return disk.read(); }
    public void writeDisk(Data data){ disk.write(data); }
    public Disk getDisk(){ return disk; }
}
```

<div style="text-align:center; ">基于多态实现设计抽象（解耦）</div>
<div style="text-align:center; ">但你还要cast</div>

---

## 用泛型来做

``` java
public class Computer<T extends Disk>{
    private T disk;   // 参数类
    Computer(T disk){ this.disk = disk; }
    public Data readDisk(){ return disk.read(); }
    public void writeDisk(Data data){ disk.write(data); }
    public T getDisk(){ return disk; }
    public void setDisk(T disk){ return this.disk = disk; }

    public static void main(String[] args) {
        Computer<SSD> computer = new Computer<SSD>(new SSD());
        SSD disk = computer.getDisk(); // No cast needed
        //computer.setDisk(new HHD()); // error!
    }
}
```
<div style="text-align:center; ">有了类型约束</div>

---

## 泛型方法

``` java
public class GenericMethods {
    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }
    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f("");
        gm.f(1); //autoboxing
        gm.f(1.0);
        gm.f(1.0F);
        gm.f(‘c’);
        gm.f(gm);
    }
}
```

---

## 再看容器

``` java
public class Holder<T> {
    private T obj;
    public void set(T obj){ this.obj = obj; }
    public T get(){ return obj; }
    public static void main(String[] args){
        Holder<Integer> holder = new Holder<>();
        holder.set(1);
        //holder.set("Abc"); // error
        Integer obj = holder.get(); //无须cast
    }       
}
```
<div style="text-align:center; ">多完美！可惜这只是编译时刻... 因为运行时的类型信息被擦掉了<div>

```
javap -v -p -s -sysinfo -constants Holder.class
```
---

## 擦除

``` java
public class Computer<T extends Disk>{
    private T disk;   // 运行时disk是Disk类型
    Computer(T disk){
        disk = disk;
    }
}
```

Java泛型的实现方式就是将类型参数用边界类型替换，在上面的例子中就是把T用Disk替换。这种实现方式看上去就像是把具体的类型（某种硬盘，机械的或者是固态的），擦除到了边界类型（它们的父类Disk)。

---


## 这两个是不同类型么？

```java
ArrayList<Integer> intList = new ArrayList<>();
ArrayList rawList = new ArrayList();

System.out.println(intList.getClass().getSimpleName());
System.out.println(rawList.getClass().getSimpleName());
```


---

## 擦除的后果

``` java
ppublic class Holder<T> {
    private T obj;
    public void set(T obj){ this.obj = obj; }
    public T get(){ return obj; }
    public void testT(Object arg){
        if (arg instanceof T){ ... } //编译错误
        T var = new T(); //编译错误
        T[] array = new T[100]; //编译错误
        }
    }
}
```

<div style="text-align:center; ">这劳什子有何用？！</div>

---

## T存在的意义
```java
public class Holder<T> {
    private T obj; //在编译时，该类中的所有的T都会被替换为边界类型Object。
    public void set(T obj){ this.obj = obj; }
    public T get(){ return obj; }
    public static void main(String[] args){
        Holder<Integer> holder = new Holder<>();
        //编译器会检查实参是不是一个Integer，
        //虽然这里的1是int类型，但是因为自动包装机制的存在，
        //他会被转化为一个Integer，因此能够通过类型检查。
        holder.set(1); 
        //编译器也会进行类型检查，
        //并且自动插入一个Object类型到Integer类型的转型操作。
        Integer obj = holder.get();
    }       
}
```

---

## 泛型的实际实现

- 对泛型的处理全部集中在编译期，在编译时，编译器会执行如下操作。
  - 会将泛型类的类型参数都用边界类型替换。
  - 对于传入对象给方法形参的指令，编译器会执行一个类型检查，看传入的对象是不是类型参数所指定的类型。
  - 对于返回类型参数表示对象的指令，也会执行一个类型检查，还会插入一个自动的向下转型，将对象从边界类型向下转型到类型参数所表示的类型。

---

## 所以这些都是错的 

``` java
ppublic class Holder<T> {
    private T obj;
    public void set(T obj){ this.obj = obj; }
    public T get(){ return obj; }
    public void testT(Object arg){
        if (arg instanceof T){ ... } //编译错误
        T var = new T(); //编译错误
        T[] array = new T[100]; //编译错误
        }
    }
}
```

---

## 如果真想生成泛型对象？

```java
class Holder<T>{
    private T t;
    public void init(IFactory<T> factory){
        this.t = factory.create();  // 此处即为new T()的工厂方法的实现
    }
}
interface IFactory<T>{  //接口也可以参数化
    T create();
}
class IntegerFactory implements IFactory<Integer>{
    public Integer create(){
        return new Integer(10);
    }
}
public class newTwithFactory{
    public static void main(String[] args){
        Holder<Integer> holder = new Holder<>();
        holder.init(new IntegerFactory());
    }
}
```

---

## 或者可以使用RTTI

``` java
class Holder<T>{
    private T t;
    private Class<T> kind;
    public Holder(Class<T> kind){ this.kind = kind; }
    public void init(){
        try{
            this.t = kind.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Holder<Integer> holder = new Holder<>(Integer.class);
        holder.init();
    }
}
```


---

## 边界 Bounds

用`extends`申明对参数类型的限制条件

```java
interface HasColor{ java.awt.Color getColor(); }

class Colored <T extends HasColor>{...}

class Dimension { public int x,y,z; }

class ColoredDimension <T extends HasColor & Dimension>{...} //错误！
class ColoredDimension <T extends Dimension & HasColor>{
    
}
```


---

## 看看这个例子

``` java

class Fruit{}
class Apple extends Fruit{}

public class NonConvariantGeneric {
    List<Fruit> flist = new ArrayList<Apple>(); //编译错误
}
```

---

## 再看看这个例子



```java
class Fruit {}
class Apple extends Fruit {}

class Plate<T>{
    private T item;
    public Plate(T t){item=t;}
    public void set(T t){item=t;}
    public T get(){return item;}
}

//现在我定义一个“水果盘子”，逻辑上水果盘子当然可以装苹果。
//但实际上Java编译器不允许这个操作。会报错，“装苹果的盘子”无法转换成“装水果的盘子”。
Plate<Fruit> p=new Plate<Apple>(new Apple()); //编译错误！
```

<div style="text-align:center; color:red;">“苹果” IS-A “水果”， BUT “装苹果的盘子” NOT-IS-A “装水果的盘子”！</div>


---

## 协变与逆变

协变与逆变(Covariance and contravariance )是在计算机科学中，描述具有父/子型别关系的多个型别通过型别构造器、构造出的多个复杂型别之间是否有父/子型别关系的用语。


<small>https://zh.wikipedia.org/wiki/%E5%8D%8F%E5%8F%98%E4%B8%8E%E9%80%86%E5%8F%98</small>


---

## 通配符

```java
class Fruit{}
class Apple extends Fruit{}
public class GenericsAndCovariance {
    public static void main(String[] args){
        //一个能放水果以及一切是水果派生类的盘子,啥水果都能放的盘子
        //Plate<？ extends Fruit>和Plate<Apple>最大的区别就是：
        //Plate<？ extends Fruit>是Plate<Fruit>以及Plate<Apple>的基类。
        Plate<? extends Fruit> p=new Plate<Apple>(new Apple());
        // a list of any type that's inherited from Fruit
        List<? extends Fruit> flist = new ArrayList<Apple>();
    }
}
```

---

## 扩展一下

```java
class Food{}
//Lev 2
class Fruit extends Food{}
class Meat extends Food{}
//Lev 3
class Apple extends Fruit{}
class Banana extends Fruit{}
class Pork extends Meat{}
class Beef extends Meat{}
//Lev 4
class RedApple extends Apple{}
class GreenApple extends Apple{}
```

---

## 通配


![bg right:80% fit](https://itimetraveler.github.io/gallery/java-genericity/lowerBounds.png)

---

## but

```java
class Fruit{}
class Apple extends Fruit{}

       Plate<? extends Fruit> p=new Plate<Apple>(new Apple());
        //不能存入任何元素
        p.set(new Fruit());    //Error
        p.set(new Apple());    //Error
        //读取出来的东西只能存放在Fruit或它的基类里。
        Fruit newFruit1=p.get();
        Object newFruit2=p.get();
        Apple newFruit3=p.get();    //Error
```
<div style="text-align:center; font-size:20pt; color:red ">"A Plate of any type that's inherited from Fruit" 的意思不是"A Plate will hold any type of Fruit" but means "some specific type which is not specify" </div>

---

## super

表达的就是相反的概念：一个能放水果以及一切是水果基类的盘子。

![bg right:60% fit](https://itimetraveler.github.io/gallery/java-genericity/upperBounds.png)

`Plate<？super Fruit>`覆盖图中红色的区域。

---

## but

```java
class Fruit{}
class Apple extends Fruit{}
public class GenericsAndCovariance {
    public static void main(String[] args){
       Plate<? super Fruit> p=new Plate<Fruit>(new Fruit());
        //存入元素正常
        p.set(new Fruit());
        p.set(new Apple());
        //读取出来的东西只能存放在Object类里。
        Apple newFruit3=p.get();    //Error
        Fruit newFruit1=p.get();    //Error
        Object newFruit2=p.get();
    }
}
```

---
# 区别？

```
///无界通配符 Unbounded Wildcards
List<?>
```
```                            
List
```
```                            
List<Object>
```

---

## Overloading

```java
public class UseList<W,T>{
    void f(List<T> v){}
    void f(List<W> v){}
}
```

<div style="text-align:center; font-size:30pt; color:red ">NO！</div>

---

## Self-bounded types

```java
class SelfBounded<T extends SelfBounded<T>>{
    ...
}
```

😢😢😢😢😢😢😢😢😢😢😢😢😢😢😢😢😢😢😢😢😢😢


---

## 从简单的开始

```java
public class BasicHolder<T> {
    T element;
    void set(T arg){this.element = arg;}
    T get(){return this.element;}
    void print(){
        System.out.println(element.getClass().getSimpleName());
    }
}
public class SubType extends BasicHolder<SubType> {
    public static void main(String[] args){
        SubType s1 = new SubType();
        SubType s3 = s1.get();
        s1.print();
        s3.print();
    }
}

```

---

## 得到什么？

``` java
public class SubType extends BasicHolder<SubType> {}
public class Plate extends BasicHolder<Plate>{}
...
```

... the generic base class becomes a kind of template for comon functionality for all its derived class

<br>
<div style="text-align:center; font-size:30pt; color:red ">跟实现一个父类有什么区别？</div>

---

## 跟普通继承关系的区别

... but this functionality will use the derived type for all of its arguments and return values ...

<br>
<div style="text-align:center; font-size:30pt; color:red ">漂亮！</div>

---

## 还有一点小问题

``` java
class Other{}
class BasicOther extends BasicHolder<Other>{}

public static void main(String[] args){
    BasicOther b = new BasicOther(), b2 = new BasicOther();
    b.set(new Other());
    Other other = b.get();
    b.print();// Other
}
```

<br>
<div style="text-align:center; font-size:30pt; color:red ">没完全限制</div>

---

## Self-bounded

```java
//forcing the generic to be used as its own bound argument
class SelfBounded<T extends SelfBounded<T>> {
    T element;
    SelfBounded<T> set(T arg){
        element = arg;
        return this;
    }
    T get(){return element;}
}

class A extends SelfBounded<A> {}
class B extends SelfBounded<A> {} //ok 
class D;
class E extends SelfBounded<D>{} //error

public static void main(String[] args){
        A a = new A();
        a.set(new A());
        a.print();
        B b = new B(), a2 = new B();
        //b.set(b2); //Error
        //b.print();
    }
```
---

## 高大上一点的说法
<br>
<br>
<br>
<br>

<div style="text-align:center; font-size:30pt; color:red ">Argument covariance</div>

<div style="text-align:center; font-size:30pt; color:red ">&</div>

<div style="text-align:center; font-size:30pt; color:red ">Covariant return types</div>

---

# 作业

Rewrite 葫芦娃 with Generics

---



![bg 50%](images/happy.png)
