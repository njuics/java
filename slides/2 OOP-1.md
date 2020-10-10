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

![bg width:80%](images/goto.png)
<br><br><br><br><br><br>
 _Edsger Dijkstra, "Go To Statement Considered Harmful",  1968 Communications of the ACM (CACM),_


---

# 结构化程序设计

结构化编程在1960年代开始发展，Giuseppe Jacopini发表论文说明任何一个有goto指令的程序，可以改为完全不使用goto指令的程序


_Corrado Bohm, Giuseppe Jacopini, "Flow diagrams, turing machines and languages with only two formation rules" Comm. ACM, 9(5):366-371, May 1966._


采用子程序、块结构、for循环以及while循环等结构，来取代传统的goto。希望借此来改善计算机程序的明晰性、质量以及开发时间，并且避免写出[面条式代码](https://zh.wikipedia.org/zh-hans/%E9%9D%A2%E6%9D%A1%E5%BC%8F%E4%BB%A3%E7%A0%81)。


<div style="text-align: center; color:red;">有序控制，降低复杂度。</div>



 ---

#  过程式程序设计

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


---


# 过程式编程


过程式 (Procedural programming)

- 派生自结构化编程，主要采取程序调用或函数调用的方式来进行流程控制
- 流程则由包涵一系列运算步骤的过程，例程，子程序, 方法，或函数来控制。

<br>

<div style="text-align: center; color:red;">自顶向下，逐步求精。</div>

---

# 过程式编程

主要问题

- 它是面向“解空间”的。
- 即针对现实中“做什么”的问题直接给出在机器中“怎么做”的解决方案。

<br>

<div style="text-align: center; color:green; ">这种编程语言迫使我们以机器视角进行编程 </div>

---


## 如何把大象装进冰箱？

![bg right:40% width:100%](https://pic.17qq.com/uploads/iiodhphogjz.jpeg)

为了把大象装进冰箱，需要3个过程。

1. 把冰箱门打开
2. 把大象装进去
3. 把冰箱门关上

每个过程有阶段性的目标，依次完成这些过程，就能把大象装进冰箱。

<br>

<div style="text-align: center; color:red; ">这是面向过程</div>

---

# 如何把大象装进冰箱(OO)？

做三个动作（行为），每个动作有一个执行者，它就是对象(Object)。

1. 冰箱，你给我把门打开
2. 大象，你给我钻到冰箱里去
3. 冰箱，你给我把门关上

每个执行者依次动作，大象进了冰箱。<span style="color:red; ">考虑“用什么做”虑, 这是面向对象</span>


![width:80%](https://s3.narvii.com/image/w6w4xj3ybg7wv5zwv5rt2iz2ohcenklx_hq.jpg)

---

# 面向过程 vs. 
# 面向对象

“面向过程”是做一件事，是对机器行为的说明；

“面向对象”是造一堆东西，是对现实中对象的刻画。

![bg right:50% fit](https://5b0988e595225.cdn.sohucs.com/images/20190709/fdc77e7daaa4469d8d549f1c2327bdbd.jpeg)

---
# 面向对象编程


_“面向对象编程的首要工作就是认识待解决问题所涉及的基本对象和他们间的相互关系”_
<div align="right"><small>- 徐家福，《对象式程序设计语言》</small></div>

然后通过将这些对象映射到计算机中，实现计算机对现实问题的<span style="color:red">模拟</span>，得到与应用问题结构对应（一致）的程序系统结构。 <!-- .element: class="fragment" -->

<br>
<div style="text-align:center; color:red"><large>这样使我们能从问题的角度进行编程</large></div> <!-- .element: class="fragment" -->


---

# Simula

![bg right:30% 90%](images/Simula.png)

- 于1960年代在奥斯陆的挪威电子计算中心（Norwegian Computing Center）开始被发展出来
- 主要的设计者是Ole-Johan Dahl等，开发出了Simula I与Simula 67两代
- 它被认为是第一个面向对象编程的编程语言。


---

# Smalltalk
<small>1970s, Alan Kay</small>

第一个成功的面向对象程序设计语言

![ bg right:60% height:400px ](https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Alan_Kay_%283097597186%29.jpg/1920px-Alan_Kay_%283097597186%29.jpg)  <!-- .element height="40%" width="40%" --> 

---

## 面向对象（Object Oriented）设计思想

- 万物皆对象。你可以将对象想象成一种特殊的变量。它存储数据，但可以在你对其“发出请求”时执行本身的操作。
- 程序是一组对象，通过消息传递来告知彼此该做什么。要请求调用一个对象的方法，你需要向该对象发送消息。
- 每个对象都有自己的存储空间，可容纳其他对象。
- 每个对象都有一种类型，每个对象都是某个“类”的一个“实例”。
- 同一类所有对象都能接收相同的消息。一个类最重要的特征就是“能将什么消息发给它？”。

<div style="text-align:center; color:red">写程序就是写对象，实现对现实世界的“模拟” </div> 

---

# 对象：现实世界中事物的抽象

![height:450px](https://pic2.zhimg.com/50/v2-0c6c35cf72ed9b761dd27f57b2bf3671_hd.jpg)

<div style="text-align: right;"><small>https://zhuanlan.zhihu.com/p/38891517</small></div>


---

# Simula中经典问题:“银行出纳员”


- 出纳员、客户、帐号、交易和货币单位等许多"对象”
- 每类成员（元素）都具有一些通用的特征：每个帐号都有一定的余额；每名出纳都能接收客户的存款；等等。
- 每个成员都有自己的状态；每个帐号都有不同的余额；每名出纳都有一个名字。
- 能用独一无二的实体分别表示出纳员、客户、帐号以及交易。这个实体便是“对象”，而且每个对象都隶属一个特定的“类”，那个类具有自己的通用特征与行为。


https://lingcoder.github.io/OnJava8/#/book/01-What-is-an-Object

---

## 对象与对象类型

![bg right:50% 80%](https://lingcoder.github.io/OnJava8/images/reader.png)
- Each object has its own memory, and is made up of other objects. 
- An object has an interface, determined by its class.
- Every object has a type (class).

<span style="color:red"><small>接口即与对外界交互的接口，代表对象的行为。同类型对象行为一致</small></span> 

---

## 现实中的例子

![bg right:50% 80%](https://i0.wp.com/www.annainchina.com/wp-content/uploads/2017/09/%E4%BD%A0%E6%88%91%E4%BB%962.jpg?fit=960%2C720&ssl=1)

- “你”、“我”都是Object（对象）
- “你”、“我”的行为方法都一样，所以我们都是"人"这个Class(类型)。
- If “你”、“我”的行为方法不一样...

---

# Then

![bg height:500px](https://www.shuoshuokong.org/uploads/allimg/160919/2-160919104204.jpg)
<br><br>
<br>
<br>
<br>
<div style="text-align:center; color:red"><large>“你（对象）不是人（类型）！”</div>


---

## 面向对象编程

Object Oriented Programming in Java

```java
class Human {

}
Human you;
Human me;
Human him, her;
```

定义类型，声明对象。


<span style="color:red">但还没有人造出来。</span>

---

# 女娲造人

![bg height:400px](https://njuics.github.io/java-2019/content/images/3/nvwa.jpg)  

---

# 在代码中造人

``` java
class Human {

}

...
Human you = new Human();
Human me = new Human();
...

```

<span style="color:red">定义了类型，声明且创造了对象</span>


![bg right:50% height:400px](https://njuics.github.io/java-2019/content/images/3/nvwa.jpg)  


---

## 稍微详细点


造的是个什么样的人？


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

# 你还缺个女娲或者叫“上帝之手”

换句话说，这两行`new`从在哪里执行呢？

``` java
class Human {

}

...
Human you = new Human();
Human me = new Human();
...

```


---

# run起来



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

<div style="text-align:center; color:green"><large>C'est la vie</large></div>


---
# 入口
`main()`放哪里？

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

# 关系：Composition


One class has another as a part.

例如：引擎是汽车的一部分。

https://lingcoder.github.io/OnJava8/#/book/01-What-is-an-Object?id=%e5%a4%8d%e7%94%a8

![bg right:50% fit](https://lingcoder.github.io/OnJava8/images/1545758268350.png)

---

# 再来点更抽象的

```java
class Society {
  Human[] members; //A society's subparts
  void initialize(){
    Human you = new Human(); Human me = new Human();
    members = new Human[2];
    members[0] = you; members[1] = me;
  }
  void functioning(){
    for (int i=0; i< memebers.length; i++){
      if (!member[i].isDead()){
        member[i].talk(); member[i].eat();
      }
    }
  }
  public static void main(String[] args){
    Society society = new Society();
    society.initialize(); society.functioning();
  }
}
```

---

# 关系：Inheritance

One class is a specialized version of another.

https://lingcoder.github.io/OnJava8/#/book/01-What-is-an-Object?id=%e7%bb%a7%e6%89%bf

![bg right:50% 50%](https://lingcoder.github.io/OnJava8/images/1545763399825.png)

---

# 男人和女人

```java
class Man extends Human {
  void chiji(){}
}

class Woman extends Human {
  void selfie(){}
}

Man me = new Man();
me.chiji();
me.talk();

Woman her = new Woman();
her().selfie();
her.eat();
```

---

# 最抽象的那个对象

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

``` java
class Human {

}
```
等同于

```java
class Human extends Object {
  ...
}
```

<div style="text-align:center; color:red"><large>Java中定义的每个类型默认都继承自Object类型。</large></div>

---

## Override

```java
class Man extends Human {
  void chiji(){}
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
<div style="text-align:center; color:red"><large>同样都是人，但男女还是很不一样的。</large></div>

---

# 父类与子类

```java
Man me = new Man();
Woman you = new Woman();
Human her = new Human();
Human he = new Man();
Man he = new Human(); // wrong
```

---

# 这样会是什么结果？

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

https://lingcoder.github.io/OnJava8/#/book/01-What-is-an-Object?id=%e5%a4%9a%e6%80%81

![bg right:60% fit](https://lingcoder.github.io/OnJava8/images/1545839316314.png)

---

# 这样会是什么结果？

```java
class Society{
  Human[] members;
  void initialize(){
    Man you = new Man(); Woman me = new Woman();
    members = new Human[2];
    members[0] = you; members[1] = me;
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
    society.initialize(); society.functioning();
  }
}
```


---

# Initialization

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
    this.age = age; //this -> this object
  }
  ...
}
```

---

## Destroying Objects

If an object goes “out of scope,” it can no longer be used (its name is no longer known).
In C++, we might need to write an explicit function to free memory allocated to an object.
Java uses references and “garbage collection”.

![bg right:40% 80%](images/GC.png)

---

# Garbage collection

![height:400px](https://static001.infoq.cn/resource/image/75/a4/754f1ab05e6527107cfd8578d98a80a4.png)

---

## What Happens?

```java
class Woman {
  void giveBirth() {
      Human baby;
      baby = new Human();
      return;
  }
  ...
}
```
<small>The `Human` object still exists, but the reference `baby` disappears (it’s out of scope after return). Eventually, the garbage collector removes the actual `Human` object, i.e., the `baby`.</small>

![bg right:30% 80%](images/cry.jpg)

---

## Inside

![height:400px](https://www.programcreek.com/wp-content/uploads/2013/09/string-pass-by-reference--650x247.jpeg)


<div style="text-align: right;"><small>https://www.baeldung.com/jvm-garbage-collectors
</small></div>

---

## Primitives ?

```java
int i; double d; ...
```
<small>

| 类型      | 内存字节数 |                    数值范围 |
| --------- | :--------: | --------------------------: |
| `byte`    |     1      |               `-128`～`127` |
| `short`   |     2      |           `-32768`～`32767` |
| `int`     |     4      | `-2147483648`～`2147483647` |
| `long`    |     8      |                    whatever |
| `float`   |     4      |                   who cares |
| `double`  |     8      |                   holy crap |
| `char`    |     2      |           `\u0000`~`\uFFFF` |
| `boolean` |     1      |           `true` or `false` |

</small>


---

## Heap and Stack

![height:400px](https://i.stack.imgur.com/KdBPf.png)

<div style="text-align: right;"><small>https://www.baeldung.com/java-stack-heap
</small></div>


---

# Always remember 

![height:500px](images/showcode.jpg) 

---

# Let's DO the Java Programming!


![height:500px](https://raw.githubusercontent.com/redhat-developer/vscode-java/master/images/vscode-java.0.0.1.gif)

---

![bg 50%](images/happy.png)