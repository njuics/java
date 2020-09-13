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

## 面向对象编程

---


# 编程范式 Programming Pradigms

- 命令式编程 Imperative programming
  - Focuses on describing <span style="color:red">**how**</span> a program operates.
- 声明式编程 Declarative programming
  - Focuses on <span style="color:red">**what**</span> the program should accomplish without specifying how the program should achieve the result.

---

# 命令式编程

In computer science, imperative programming is a programming paradigm that uses statements that change a program's state. In much the same way that the imperative mood in natural languages expresses commands, an **imperative program consists of commands for the computer to perform**. 

<br>
<div style="text-align: right;">- Wikipeida</div>


---

# 汇编语言

```asa
        extern printf           ; the C printf function, to be called
        section .data           ; Data section, initialized variables
msg:    db "Hello, world!", 0   ; C string terminates with 0
fmt:    db "%s", 10, 0          ; The printf format, "\n",'0'
        section .text           ; Begin code section
        global main             ; the standard gcc entry point
main:                           ; the program label for the entry point
        push    rbp             ; set up stack frame, must be aligned
        mov     rdi,fmt
        mov     rsi,msg
        mov     rax,0           ; can also be: xor rax,rax
        call    printf          ; Call C printf function
        pop rbp                 ; restore stack
        mov rax,0               ; normal, no error, return value
        ret                     ; return
```

---

# Go To Statement

![bg width:80%](https://i.stack.imgur.com/6C1F5.png)
<br><br><br><br><br><br>
 _Edsger Dijkstra, "Go To Statement Considered Harmful",  1968 Communications of the ACM (CACM),_


---

# 结构化程序设计

结构化程序设计 (Structured programming): 采用子程序、块结构、for循环以及while循环等结构，来取代传统的goto。希望借此来改善计算机程序的明晰性、质量以及开发时间，并且避免写出面条式代码。

结构化编程在1960年代开始发展，Giuseppe Jacopini发表论文说明任何一个有goto指令的程序，可以改为完全不使用goto指令的程序


_Corrado Bohm, Giuseppe Jacopini, "Flow diagrams, turing machines and languages with only two formation rules" Comm. ACM, 9(5):366-371, May 1966._

<div style="text-align: center; color:red;">有序控制，降低复杂度。</div>



 ---

#  过程式程序设计

过程式 (Procedural programming): 派生自结构化编程，主要采取程序调用或函数调用的方式来进行流程控制。流程则由包涵一系列运算步骤的过程，例程，子程序, 方法，或函数来控制，例如Fortran, ALGOL, COBOL and BASIC

---


## 过程式编程

```c
#include <iostream> 
int sum(int num1, int num2); 
int main () { 
   int a = 10, b = 20, res; 
   res = sum(a, b); 
   std::cout << a << "+" << b << "=" << res << std::endl; 
   return 0; 
}
int sum(int num1, int num2) { 
   int result; 
   result = num1 + num2; 
   return result; 
} 
```
<div style="text-align: center; color:red;">自顶向下，逐步求精。</div>

---


# 过程式编程的问题

它是面向“解空间”的。

即针对现实中“做什么”的问题直接给出在机器中“怎么做”的解决方案。

<br>

<div style="text-align: center; color:green; ">这种编程语言迫使我们以机器视角进行编程 </div>

---

# 如何把大象装进冰箱？

为了把大象装进冰箱，需要3个过程。

1. 把冰箱门打开
2. 把大象装进去
3. 把冰箱门关上

每个过程有一个阶段性的目标，依次完成这些过程，就能把大象装进冰箱。


<div style="text-align: center; color:red; ">这是面向过程</div>。

---

# 如何把大象装进冰箱？

为了把大象装进冰箱，需要做三个动作（或者叫行为）。
每个动作有一个执行者，它就是对象。

1. 冰箱，你给我把门打开
2. 大象，你给我钻到冰箱里去
3. 冰箱，你给我把门关上

每个执行者依次做这些动作，大象就装进了冰箱。

<div style="text-align: center; color:red; ">从“用什么做”开始考虑, 这是面向对象</div>

---

“面向过程”是做一件事，是对机器行为的说明；

“面向对象”是造一堆东西，是对现实中对象的刻画。

---

_“面向对象编程的首要工作就是认识待解决问题所涉及的基本对象和他们间的相互关系”_
<div align="right"><small>- 徐家福，《对象式程序设计语言》</small></div>

然后通过将这些对象映射到计算机中，实现计算机对现实问题的模拟，得到与应用问题结构对应（一致）的程序系统结构。 <!-- .element: class="fragment" -->

<span style="color:gold">**这样使我们能从问题的角度进行编程**</span> <!-- .element: class="fragment" -->


---

Simula, 1967, Ole-Johan Dahl and Kristen Nygaard

Smalltalk, 1970s, Alan Kay

![AlanKay](https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Alan_Kay_%283097597186%29.jpg/1920px-Alan_Kay_%283097597186%29.jpg)  <!-- .element height="40%" width="40%" --> 

---

## 面向对象（Object Oriented）

- Everything is an object.
- A program is a bunch of objects telling each other what to do, by sending messages.

<span style="color:gold">写程序就是写对象，实现对现实世界的模拟 </span> <!-- .element: class="fragment" -->

---

## 对象：现实世界中事物的抽象

“你”、“我”、“他／她／它”

---

## 对象 Object

Each object has its own memory, and is made up of other objects.

<span style="color:gold">对象属性</span> <!-- .element: class="fragment" -->

---

## 对象类型 Class

- An object has an interface, determined by its class.
- Every object has a type (class).

<span style="color:gold">接口即与对外界交互的接口，代表对象的行为。同类型对象行为一致</span> <!-- .element: class="fragment" -->

“人”<!-- .element: class="fragment" -->

---

![](http://www.shuoshuokong.org/uploads/allimg/160919/2-160919104204.jpg)

“你（对象）不是人（类型）！”

---

## 面向对象编程

```java
class Human {

}
Human you;
Human me;
Human him, her;
```

定义类型，声明对象。

Object Oriented Programming in Java

---

女娲造人

![](http://imgs15.iaweg.com/pic/HTTP2ltZy56Y29vbC5jbi9jb21tdW5pdHkvMDFjNmQ0NTU0NzUyMTIwMDAwMDAyYjAxNzNjZWUxLmpwZwloglog.jpg)  <!-- .element: width="60%" -->

---

## 在代码中造人

``` java
class Human {

}

...
Human you = new Human();
Human me = new Human();
...

```

---

## 稍微详细点

```java
class Human {
  int age; // 属性
  boolean gender; // 属性

  void talk(){} // 接口/行为
  void eat(){  // 快乐的行为
    ...
  }
  boolean isDead(){} //悲哀的行为
}
```

---

## 你还缺个女娲

## 或者叫“上帝之手”

---

## run起来

```java
void main() {
  Human you = new Human();
  Human me = new Human();

  while(!me.isDead() && !you.isDead()){
    me.talk();
    me.eat();
    you.talk();
    you.eat();
  }
}
```

---

`main()`放哪里

```java
class God {
  public static void main(String[] args){
    Human you = new Human();
    Human me = new Human();
    while(!me.isDead() && !you.isDead()){
      me.talk();
      me.eat();
      you.talk();
      you.eat();
    }
  }
}

```

---

## Composition

One class has another as a part.

---

## 再抽象一次

```java
class Society {

  Human[] members; //A society's subparts

  void initialize(){
    Human you = new Human();
    Human me = new Human();
    members = new Human[2];
    members[0] = you;
    members[1] = me;
  }

  void functioning(){
    for (int i=0; i< memebers.length; i++){
      if (!member[i].isDead()){
        member[i].talk();
        member[i].eat();
      }
    }
  }

  public static void main(String[] args){
    Society society = new Society();
    society.initialize();
    society.functioning();
  }
}
```

---

## Inheritance

One class is a specialized version of another.

---

男人和女人

```java
class Man extends Human {
  void playDota(){}
}

class Woman extends Human {
  void selfie(){}
}

Man me = new Man();
me.playDota();
me.talk();

Woman her = new Woman();
her().selfie();
her.eat();
```

---

## 最抽象的那个对象

`Object`

对一切事物的泛指。

```java
class Object {
  ...
}
```

<small>https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html</small>


---

## Actually

```java
class Human extends Object {
  ...
}
```

---

## Override

```java
class Man extends Human {
  void playDota(){}
  @Override
  void talk(){
    System.out.println("bala bala");
  }
}

class Woman extends Human {
  void selfie(){}
  @Override
  void talk(){
    while (true) System.out.println("bala bala");
  }
}
```

---

## 父类与子类

```java
Man me = new Man();
Woman you = new Woman();
Human her = new Human();
Human he = new Man();
Man he = new Human(); // wrong
```

---

## 会是什么结果？

```java
Man me = new Man(); me.talk();
Woman you = new Woman(); you.talk();
Human her = new Human(); her.talk();
Human he = new Man(); he.talk();
Man he = new Human(); // wrong
```

---

## Polymorphism 多态

Different subclasses respond to the same message, possibly with different actions.

---

## 会是什么结果？

```java
class Society{
  Human[] members;
  void initialize(){
    Man you = new Man();
    Woman me = new Woman();
    members = new Human[2];
    members[0] = you;
    members[1] = me;
  }
  
  void functioning(){
    for (int i=0; i< memebers.length; i++){
      if (!member[i].isDead()){
        member[i].talk();
      }
    }
  }

  public static void main(String[] args){
    Society society = new Society();
    society.initialize();
    society.functioning();
  }
}

```


---

## Initialization

```java

class Human{
  int age;
  boolean gender;
  ....
}

Human you = new Human(); // age=0; gender=false;
```

---

## Customized Initialization


```java
class Human {
  int age;
  boolean gender;
  Human() {
    age = 0;
    gender = true;
  }

  Human(int age) {
    self.age = age; //self -> this object
  }
  ...
}
```

---

## Destroying Objects

If an object goes “out of scope,” it can no longer be used (its name is no longer known).
In C++, we might need to write an explicit function to free memory allocated to an object.
Java uses references and “garbage collection”.

---

## What Happens?

```java
class Woman {
  ...
  void giveBirth() {
      Human baby;
      baby = new Human();
      return;
  }
  ...
}
```

The `Human` object still exists, but the reference `baby` disappears (it’s out of scope after return). Eventually, the garbage collector removes the actual `Human` object, i.e., the `baby`.

---

## Inside

![](http://www.programcreek.com/wp-content/uploads/2013/09/string-pass-by-reference--650x247.jpeg)


---

## Primitives ?

```java
int i;
double d;
...
```

---

## Heap and Stack

![](https://i.stack.imgur.com/KdBPf.png)

---

![](http://poster.keepcalmandposters.com/9314179.jpg) <!-- .element: width="50%" -->

---

Let's DO the Java Programming!

![](https://www.jetbrains.com/idea/img/screenshots/idea_overview_5_1@2x.png) <!-- .element: width="60%" -->

---

# END