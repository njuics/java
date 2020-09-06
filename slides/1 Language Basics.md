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

## 语言基础

---

# 标识符
- 程序中要用到许多名字，诸如类、对象、变量、方法等。标识符就是用来标识它们的唯一性和存在性的名字。
- 标识符的长度是任意的,标识符分为两类：
  - 保留字：是Java预定义的标识符，具有特定的含义，又称关键字。
  - 用户定义标识符：是程序设计者根据自己的需要为定义的类、对象、变量、方法等的命名。
- Java采用Unicode字符集，由16位构成，可以记录表达任何人类语言。

--- 

# 用户自定义标识符的定义规则

- 以字母、下划线或符开头的字母、下划线、数字、符的序列。
- 一些由开发环境自动生成的名称中会带有符或下划线，因此虽然规则允许，但是自定义的变量名称中应尽量避免使用符或下划线。
- 标识符区分大小写。
- 标识符不能与保留字同名。
- 标识符遵守先定义后使用的原则。
- 虽然`true`、`false`和`null`并不是关键字，但其代表的是值，也不可以用。

---

# 分隔符
- 规定任意两个相邻标识符、数、保留字或两个语句之间必须至少有一个分隔符，以便编译程序能识别：
  - 空白分隔符：空格、TAB、换行符与回车符
  - 普通分隔符:
    - `{ }` 用来定义复合语句、类体、方法体以及进行数组的初始化等
    - `;` 表示一条语句的结束。
    - `:` 有些地方会用到，你自会知道。

---

# 注释
注释用来对程序中的代码做出解释。注释部分对程序的执行不产生任何影响，可增加程序的可读性，有利于程序的修改、调试、交流。

 ```java               
// 这是一个单行注释             
/* 这是也是注释 *／
/*
   这是还是注释
   可以多行
 *／

/**
  * 这也是多行注释
  * 但称为说明注释，跟代码文档相关
  */
```
        

---

# 数据类型
- 基本数据类型
- 复合数据类型

---

# 基本数据类型

### 数值型

- 字节型：`byte`，短整型：`short`，整型：`int`，长整型：`long`
- 单精度：`float`，双精度：`double`
- 字符型：`char`
- 布尔型：`boolean`

---


###### 数据存储空间大小

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

---

# SizeOf
在C/C++中，经常需要用到`sizeof()`方法来获取数据项被分配的字节数，原因是为了移植性，不同数据在不同机器上可能有不同的大小。Java不需要`sizeof()`方法来满足这种需求，因为所有类型的大小在不同平台上是相同的。

```java
private static void sizeOf() {  
    System.out.println("Integer: " + Integer.SIZE/8);           // 4  
    System.out.println("Short: " + Short.SIZE/8);               // 2
    System.out.println("Long: " + Long.SIZE/8);                 // 8  
    System.out.println("Byte: " + Byte.SIZE/8);                 // 1  
    System.out.println("Character: " + Character.SIZE/8);       // 2  
    System.out.println("Float: " + Float.SIZE/8);               // 4  
    System.out.println("Double: " + Double.SIZE/8);             // 8  
}  
```

---

## 常量

- 常量是指在程序运行过程中其值不变的量
- 常量通过用关键字`final`来实现声明

```java
final int WIDTH=100;
```

此处`100`称为字面值(Literal)


---

# Underscore

In Java SE 7 and later, any number of underscore characters `_` can appear anywhere between digits in a numerical literal. This feature enables you, for example, to separate groups of digits in numeric literals, which can improve the readability of your code.

```java
long creditCardNumber = 1234_5678_9012_3456L;
```
https://docs.oracle.com/javase/7/docs/technotes/guides/language/underscores-literals.html

---

## 变量

- 变量用来存放指定类型的数据，其值在程序运行过程中是可变的。
- 使用一个变量之前必须先声明它。一方面给该变量分配内存空间，另一方面防止在以后使用此变量时因错误输入而对不存在的变量进行操作。

``` java
int a;
boolean found=false;
float b=0f,c,d=1.3f;
```

---

## 变量作用域

- 变量有一定的生存期和有效范围，变量的作用域指明可访问该变量的一段代码
  - 全局变量：可以在整个类中被访问；
  - 局部变量：在方法或方法的一个代码块中声明，它的作用域为它所在的代码块；
  - 类变量：在类中声明，而不是类的某个方法中声明，作用域为整个类；
  - 方法参数（变量）：作用域为传递给的那个方法；
  - 异常处理参数：传递给异常处理代码，作用域是异常处理部分。

---

## 运算符

- 程序中用来处理数据、表示数据运算、赋值和比较的符号称为运算符，参与运算的数据称为操作数。
  - 算术运算符：`+`,`-`,`*`,`/`,`%`,`++`,`--`...
  - 比较运算符: `>`,`<`,`==`,`!=`,`>=`...
  - 逻辑运算符：`!`,`&`,`|`,`^`,`&&`,`||`...
  - 位运算符: `~`,`&`,`|`,`^`,`<<`,`>>`,`>>>`
  - 条件运算符：`?:`
  - 赋值运算符：`=`,`+=`,`-=`,`/=`...

---

## 表达式（Expression）

- 表达式是由操作数和运算符按照一定的语法形式组成的符号序列，计算出单一值。

``` java
a=0
```

``` java
value1==value2
```

``` java
1 * 2 * 3
```

---

## 语句（Statement）

- 表达式加上“`;`”构成语句（表达式语句）
```java
a=0;
```
- 还有另外几种语句：声明语句、控制流语句、方法调用语句等

```java
int cadence = 0;
anArray[0] = 100;
System.out.println("Element 1 at index 0: " + anArray[0]);

int result = 1 + 2; // result is now 3
if (value1 == value2)
    System.out.println("value1 == value2");
```

---

## 块（Block）

- 块是位于成对大括号之间的零个或多个语句的语句组，可以在允许使用单一语句的任何位置使用块。

``` java
class BlockDemo {
     public static void main(String[] args) {
          boolean condition = true;
          if (condition) { // begin block 1
               System.out.println("Condition is true.");
          } // end block one
          else { // begin block 2
               System.out.println("Condition is false.");
          } // end block 2
     }
}
```

---

# 编码规范

- 规范原则
  - 尽量使用完整的英文描述符；
  - 采用大小写混合使名字可读，采用适用于相关领域的术语；
  - 尽量少用缩写，若已使用尽量明智，且在整个文件或工程中通用；
  - 避免使用长的和类似的名字，或仅仅是大小写不同的名字；
  - 除静态常量外，尽量少用下划线。

---

# 约定细则 - I
- 源文件命名规则
  - 源程序中包含有公共类的定义，源文件名必须与该公共类的名字一致。在一个源程序中至多只能有一个公共类的定义；
  - 源程序中不包含公共类，则该文件名只要和某个类名字相同即可；
  - 源程序中有多个类的定义，编译时将会为每个类生成一个class文件

---

# 约定细则 - II

- 包：包名是全小写的名词，中间可以由点分隔开，如`java.awt.event`
- 类／接口：类／接口名首字母大写，若由多个单词合成一个类名，要求每个单词的字母也要大写，如`MyFirstJava`
- 方法：由多个单词组成的方法名首字母小写，中间的每个单词首字母大写，如`isButtonPressed`

---
# 约定细则 - III

- 变量： 一般全小写，如`length`
- 常量：一般全大写，如果由多个单词组成则中间用下划线相连。如果是对象类型的常量，则是大小写混合，由大写字母把单词隔开，如`STR_LENGTH`
- 组件：使用完整的英文描述来说明组件的用途，尾部应该加上组件类型，如`okButton`

---

### 示例

``` java
/*
Find Largest and Smallest Number in an Array Example
*/
public class FindLargestSmallestNumber {
        public static void main(String[] args) {
                //array of 10 numbers
                int numbers[] = new int[]{32,43,53,54,32,65,63,98,43,23};
                //assign first element of an array to largest and smallest
                int smallest = numbers[0], largetst = numbers[0];
                for(int i=1; i< numbers.length; i++){
                        if(numbers[i] > largetst)
                                largetst = numbers[i];
                        else if (numbers[i] < smallest)
                                smallest = numbers[i];
                }
                System.out.println("Largest Number is : " + largetst);
                System.out.println("Smallest Number is : " + smallest);
        }
}
```
---

# 控制流


- Java 使用了 C 的所有执行控制语句，因此对于熟悉 C/C++ 编程的人来说，这部分内容轻车熟路。大多数面向过程编程语言都有共通的某种控制语句。在 Java 中，涉及的关键字包括 `if-else`，`while`，`do-while`，`for`，`return`，`break` 和`switch`。 
- Java 并不支持的`goto`

---

# `true`和`false`

所有的条件语句都利用条件表达式的“真”或“假”来决定执行路径。举例：
`a == b`。它利用了条件表达式 `==` 来比较 `a` 与 `b` 的值是否相等。 该表达式返回 `true` 或 `false`。代码示例：

```java
// control/TrueFalse.java
public class TrueFalse {
	public static void main(String[] args) {
		System.out.println(1 == 1);
		System.out.println(1 == 2);
	}
}
```
---

# `if-else`

`if-else` 语句是控制程序执行流程最基本的形式。 其中 `else` 是可选的，因此可以有两种形式的 `if`。代码示例：

```java
if(Boolean-expression) 
	“statement” 
```

或

```java
if(Boolean-expression) 
	“statement”
else
  “statement”
```


---

# 例
```java
public class IfElse {
  static int result = 0;
  static void test(int testval, int target) {
    if(testval > target)
      result = +1;
    else if(testval < target) 
      result = -1;
    else
      result = 0;
  }
  public static void main(String[] args) {
    test(10, 5); System.out.println(result);
    test(5, 10); System.out.println(result);
    test(5, 5); System.out.println(result);
  }
}
```

---


# 控制语句（迭代语句）

`while`，`do-while` 和 `for` 用来控制循环语句（有时也称迭代语句）。

只有控制循环的布尔表达式计算结果为 `false`，循环语句才会停止。 

---

# `while`


```java
while(Boolean-expression) 
  statement
```

执行语句会在每一次循环前，判断布尔表达式返回值是否为 `true`。

---

# `do-while`

```java
do 
	statement
while(Boolean-expression);
```

`while` 和 `do-while` 之间的唯一区别?

---

# `for`

`for`循环可能是最常用的迭代形式。 该循环在第一次迭代之前执行初始化。随后，它会执行布尔表达式，并在每次迭代结束时，进行某种形式的步进。

```java
for(initialization; Boolean-expression; step)
  statement
```

初始化表达式、布尔表达式，或者步进运算都可以为空。每次迭代之前都会判断布尔表达式的结果是否成立，一旦为 `false`，则跳出 `for` 循环体并继续执行后面代码。 每次循环结束时，都会执行一次步进。

---

# 例

```java
public class ListCharacters {
  public static void main(String[] args) {
    for(char c = 0; c < 128; c++)
      if(Character.isLowerCase(c))
        System.out.println("value: " + (int)c + " character: " + c);
  }
}
```

---



## 逗号操作符

在 Java 中逗号运算符（这里并非指我们平常用于分隔定义和方法参数的逗号分隔符）仅有一种用法：在 `for` 循环的初始化和步进控制中定义多个变量。我们可以使用逗号分隔多个语句，并按顺序计算这些语句。**注意**：要求定义的变量类型相同。

```java
public class CommaOperator {
  public static void main(String[] args) {
    for(int i = 1, j = i + 10; i < 5; i++, j = i * 2) {
      System.out.println("i = " + i + " j = " + j);
    }
  }
}
```

---

# `for-in` 语法 

Java 5 引入了更为简洁的“增强版`for`循环”语法来操纵数组和集合，`for-in`无需你去创建`int`** `变量和步进来控制循环计数。

```java
import java.util.*;

public class ForInFloat {
  public static void main(String[] args) {
    Random rand = new Random(47);
    float[] f = new float[10];
    for(int i = 0; i < 10; i++)
      f[i] = rand.nextFloat();
    for(float x : f)
      System.out.println(x);
  }
}
```

---

# `return`

在 Java 中有几个关键字代表无条件分支，这意味无需任何测试即可发生。这些关键字包括 `return`，`break`，`continue` 和跳转到带标签语句的方法。


```java
public class TestWithReturn {
  static int test(int testval, int target) {
    if(testval > target)
      return +1;
    if(testval < target)
      return -1;
    return 0; // Match
  }
}
```

---

# `break` 和 `continue`

在任何迭代语句的主体内，都可以使用 `break` 和 `continue` 来控制循环的流程。 其中，`break` 表示跳出当前循环体。而 `continue` 表示停止本次循环，开始下一次循环。

---



## switch

`switch` 有时也被划归为一种选择语句。根据整数表达式的值，`switch` 语句可以从一系列代码中选出一段去执行。它的格式如下：

```java
switch(integral-selector) {
	case integral-value1 : statement; break;
	case integral-value2 : statement; break;
	case integral-value3 : statement; break;
	case integral-value4 : statement; break;
	case integral-value5 : statement; break;
	// ...
	default: statement;
}
```


---


# `switch` 字符串

Java 7 增加了在字符串上 `switch` 的用法。

```java
public class StringSwitch {
  public static void main(String[] args) {
    String color = "red";
    switch(color) {
      case "red":
        System.out.println("RED");
        break;
      default:
        System.out.println("Unknown");
        break;
    }
  }
}
```

---

## 参考

Language Basics

<small> https://docs.oracle.com/javase/tutorial/java/nutsandbolts/index.html</small>


---

![bg 50%](https://www.desicomments.com/wp-content/uploads/2017/02/Thank-You-Image.png)