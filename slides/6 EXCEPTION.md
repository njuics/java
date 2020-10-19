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

![bg right:35% 90%](https://upload.wikimedia.org/wikipedia/zh/8/88/Java_logo.png)

## Error Handling with Exceptions

<br>

> Badly formed code will not be run.


---

## Outline

- Concepts
- Java Exception Mechanism
- Design by Contract vs. Exceptions 

---

## Software Reliability

- Correctness 正确性
  + 依据规约，完成任务
- Robustness 鲁棒性
  + 异常情况，合理反应
- Integrity  完整性
  + 非法访问或修改，合理反应

---

## Design by Contract


- 方法学层面的思想：以尽可能小的代价开发出可靠性出众的软件

- 可以贯穿于软件创建的全过程，从分析到设计，从文档到调试，甚至可以渗透到项目管理中

<br>

> Bertrand Meyer：<font color=red>DbC</font>是构建面向对象软件系统方法的核心。

> James McKim：只要你会写程序，你就会写<font color=red>契约</font>。

---

## Contract

- Every software element is intended to satisfy a certain <font color=red>goal</font>, for the benefit of other software elements (and ultimately of human users). 
- This goal is the element’s <font color=red>contract</font>.  
  + The contract of any software element should be
<font color=red>Explicit</font>. 
  + Part of the software element itself.

---

## A view of software construction

- Constructing systems as structured collections of cooperating software elements — <font color=red>suppliers</font> and <font color=red>clients</font> — cooperating on the basis of clear definitions of <font color=red>obligations</font> and <font color=red>benefits</font>.		
- These definitions are the contracts.

---

## 葫芦娃的契约

<br>

|   | 义务| 权益|
| - | - | - |
| Client  | 不能混进蛇精蝎子精  | 由小到大给葫芦娃们排序 |
| Supplier| 帮葫芦娃们由小到大排序| 只排葫芦娃 |


---

## Properties of Contracts

- Binds two parties (or more): supplier, client.
- Is explicit (written).
- Specifies mutual obligations and benefits. 
- Usually maps obligation for one of the parties into benefit for the other, and conversely. 

---

## Contracts

- 契约就是<font color=red>“规范和检查”</font>！
  + Precondition：针对method，它规定了在调用该方法之前必须为真的条件
  + Postcondition：针对method，它规定了方法顺利执行完毕之后必须为真的条件
  + Invariant：针对整个类，它规定了该类任何实例调用任何方法都必须为真的条件

---

## 再看葫芦娃的契约


|   | 义务| 权益 |
| - |-| -|
| Client   | "Satisfy Preconditon": 只排葫芦娃   | "From Postcondition": 葫芦娃们由小到大给排好序 |
| Supplier | "Satisfy Postconditon": 葫芦娃们由小到大排好序| "From Precondition":只排葫芦娃          |


---

## Exception Handling and DbC

- Exceptions are about dealing with things going wrong at <font color=red>runtime</font>.

- DbC is about <font color=red>statically</font> defining the conditions under which code is supposed to operate. 

---

## What are Exceptions?

- Many “exceptional” things can happen during the running of a program, e.g.:
  + <font color=green>User mis-types input</font>
  + <font color=green>Web page not available</font>
  + <font color=green>File not found</font>
  + <font color=#0099ff>Array index out of bounds</font>
  + <font color=#0099ff>Method called on a null object</font>
  + <font color=red>Out of memory</font>
  + <font color=red>Bug in the actual language implementation</font>

---

<!-- _class: lead -->
## Exceptions are unexpected conditions in programs.

---

## What do we want of exceptions?

- Ideally, a language (and its implementation) should:

  +  <font size=6><font color=red>Restrict</font> the set of possible exceptions to “reasonable” ones.</font>
  + <font size=6>Indicate <font color=red>where</font> they happened, and <font color=red>distinguish</font> between them.</font>
  + <font size=6>Allow exceptions to be dealt with in a <font color=red>different</font> place in the code from where they occur.</font>
  + <font size=6>so we <font color=#0099ff>throw</font> exceptions where they <font color=red>occur</font>, and <font color=#0099ff>catch</font> them where we want to <font color=red>deal with</font> them.</font>


---

## What do we want of exceptions?

- Ideally, we don't want non-fatal exceptions to be thrown too far — this breaks up the modularity of the program and makes it hard to reason about.

---

## Exceptions in Java

- If a thrown exception is <font color=red>not</font> caught, it <font color=red>propagates out</font> to the caller and so on until <font color=#0099ff>**main**</font>. 

- If it is <font color=red>never</font> caught, it <font color=red>terminates</font> the program.

- If a method can generate (checked) exceptions but does <font color=red>not</font> handle them, it has to explicitly <font color=red>declare</font> that it throws them so that clients know what to expect.

---

## Exceptions in Java

- In Java, the basic exception handling construct is to:
  + <font color=red>try</font>: a block of code which normally executes ok
  + <font color=red>catch</font>: any exceptions that it generates, and
  + <font color=red>finally</font>: do anything we want to do irrespective of what happened before. 


---

## Catch Exceptions

```java
try{
  //Code that might generate exceptions
}
catch (ExceptionType1 e1){
  //Handle exceptions of ExceptionType1
}
catch (ExceptionType2 e2){
  //Handle exceptions of ExceptionType2
}
catch (ExceptionType3 e3){
  //Handle exceptions of ExceptionType3
}
finally{
  //Activities that happen every time
}
```

---

## Multi-Catch

- With Java 7 multi-catch handlers, you can "OR" together different types of exceptions in a single ***catch***.

```java
public class MultiCatch{
  void x() throws Except1, Except2, Except3, Except4{}
  void process(){}
  void f(){
    try{
      x();
    }catch(Except1 | Except2 | Except3 | Except4 e){
      process();
    }
  }
}
```

---

## Catch Ordering

- All ***catch*** clauses are checked in order. An error occurs if the super-type is arranged before the sub-type.

```java
class SuperException extends Exception { }
class SubException extends SuperException { }
class BadCatch {
  public void goodTry() {
    try { 
      throw new SubException();
    } catch (SuperException superRef) { ...
    } catch (SubException subRef) {
      ...// never be reached
    } // an INVALID catch ordering
  }
}
```

---

## Catch Ordering


- If exceptions are thrown in current "catch" and "finally", the "catch" clauses will not be rechecked. They are passed to the outside.

---

## Report Exceptions


- Declare and Throw

```java
public boolean method(int x) throws MyException{
    //some code
    throw new MyException(...);
}
```

<span style="color:#0099ff">Exception Propagation</span>

---

## Create Exceptions

```java
class SimpleException extends Exception{}
public class InheritingExceptions {
  public void f() throws SimpleException {
    System.out.println("Throw SimpleException from f()");
    throw new SimpleException();
  }
  public static void main(String[] args){
    InheritingExceptions sed = new InheritingExceptions();
    try{
      sed.f();
    } catch(SimpleException e){
      System.out.println("Caught it!");
    }
  }
}
```

---

## Getting information from exceptions

<code>java.lang.Throwable</code>

```java
public Throwable();
public Throwable(String message);
public Throwable(String message, Throwable cause);
public Throwable(Throwable cause);
```


```java
public String getMessage();
public String getLocalizedMessage();
public String toString();
public StackTraceElement[] getStackTrace();
public void setStackTrace(StackTraceElement[] stackTrace);
public void printStackTrace();
```

---

## The Stack Trace

<code>e.printStackTrace()</code>

- This method returns an array of stack trace elements, each representing one stack frame.
- Element zero is the top of the stack, and is the last method invocation in the sequence (the point this <code>Throwable</code> was created and thrown).
- The last element of the array and the bottom of the stack is the first method invocation in the sequence.

---

## Example

```java
public class WhoCalled {
    static void f() {
      // Generate an exception to fill in the stack trace
      try {
            throw new Exception();
      } catch (Exception e) {
            for(StackTraceElement ste : e.getStackTrace())
            System.out.println(ste.getMethodName());
      }
    }
    static void g() { f(); }
    static void h() { g(); }
    public static void main(String[] args) {
        f(); g(); h();
    }
}
```

---

## Rethrowing exceptions

```java
try {
  // some statements;
}
catch(TheException ex) {
  //perform operations before exits;
  throw ex;
}
```
- <code>printStackTrace()</code>：显示的是原来异常抛出点的调用栈信息，而非重新抛出点的信息
- <code>fillInStackTrace()</code>：返回一个<code>Throwable</code>对象，它是通过把当前调用栈信息填入原来那个异常对象而建立的。

---

```java
public class Rethrowing {
    public static void f() throws Exception {
        System.out.println(
                "originating the exception in f()");
        throw new Exception("thrown from f()");
    }
    public static void h() throws Exception {
        try {
            f();
        } catch(Exception e) {
            System.out.println(
                    "Inside h(), e.printStackTrace()");
            e.printStackTrace(System.out);
            throw (Exception)e.fillInStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            h();
        } catch(Exception e) {
            System.out.println("main: printStackTrace()");
            e.printStackTrace(System.out);
        }
    }
}
```

---

## Chained Exception

```java
public class ChainedExceptionDemo {
  public static void main(String[] args) {
    try {  method1();}
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  public static void method1() throws Exception {
    try {  method2();}
    catch (Exception ex) {
      throw new Exception("New info from method1", ex);
    }
  }
  public static void method2() throws Exception {
      throw new Exception("New info from method2");
  }
}

```

---

## Chained Exception

![bg 50% fit](images/exceptions.jpg)

---

## Exception matching

- When an exception is thrown, the exception-handling system looks through the <font color=red>"nearest"</font> handlers in the order they are written. When it finds a match, the exception is considered handled, and no further searching occurs.

- A derived-class object will match a handler for the base class.

---

## Example

```java
class Annoyance extends Exception{}
class Sneeze extends Annoyance{}
public class Human{
  public static void main(String[] args){
    try{
      throw new Sneeze();
    } catch(Sneeze s){
      System.out.println("Caught Sneeze");
    } catch(Annoyance a){
      System.out.println("Caught Annoyance");
    }
    try{
      throw new Sneeze();
    } catch(Annoyance a){
      System.out.println("Caught Annoyance");
    }
  }
}

```

---

## Performing Cleanup with ***finally***

- ***finally*** clause is executed whether or not an exception is thrown.

```java
class ThreeException extends Exception {}
public class FinallyWorks {
    static int count = 0;
    public static void main(String[] args) {
        while(true) {
            try {
                if(count++ == 0)  throw new ThreeException();
                System.out.println("No exception");
            } catch(ThreeException e) { System.out.println("ThreeException");
            } finally { System.out.println("In finally clause");
                if(count == 2) break; // out of "while"
            }
        }
    }
}
```

---

## Try-With-Resources

```java
import java.io.*;
public class MessyExceptions {
    public static void main(String[] args) {
        InputStream in = null;
        try{ in = new FileInputStream(new File("MessyExceptions.java"));
             int contents = in.read();
             // Process contents
        } catch(IOException e) { // Handle the error
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch(IOException e) { // Handle the close() error
                }
            }
        }
    }
}
```
---

## Try-With-Resources

```java
import java.io.*;
public class TryWithResources {
    public static void main(String[] args) {
        try(
                InputStream in = new FileInputStream(
                        new File("TryWithResources.java"))
        ) {
            int contents = in.read();
            // Process contents
        } catch(IOException e) {
            // Handle the error
        }
    }
}
```
---

## Try-With-Resources

- The objects created in the **try-with-resources** definition clause (within the parenthese) must implement the <code>java.lang.AutoCloseable</code> interface, which has a single method, <code>close()</code>.

```java
class Reporter implements AutoCloseable {
    String name = getClass().getSimpleName();
    Reporter() {
        System.out.println("Creating " + name);
    }
    public void close() {
        System.out.println("Closing " + name);
    }
}
```
---

## Try-With-Resources

```java
class First extends Reporter {}
class Second extends Reporter {}
public class AutoCloseableDetails {
    public static void main(String[] args) {
        try(
                First f = new First();
                Second s = new Second()
        ) {
        }
    }
}
```

---

## Exceptions and Logging

- 1: Send error output to the *standard error* stream by writing to <code>System.err</code>.

```java
class MyException extends Exception{
  public MyException(){}
  public MyException(String msg){super(msg);}
}
public class FullConstructors {
  public static void f() throws MyException{
    System.out.println("Throwing MyException from f()");
    throw new MyException();
  }
  public static void g() throws MyException{
     System.out.println("Throwing MyException from g()");
    throw new MyException("Originated in g()");
  }
}
```
---

## Exceptions and Logging

- <code>printStackTrace()</code>
```java
  public static void main(String[] args){
    try{  f(); } 
    catch(MyException e){ 
      e.printStackTrace(System.out); 
    }
    try{  g(); } 
    catch(MyException e){
      e.printStackTrace(System.out);
    }
  }
}
```

---

## Exception and Logging

- 2: Log the output using the <code>java.util.logging</code> facility.

```java
import java.util.logging.*;
import java.io.*;

class LoggingException extends Exception{
  private static Logger logger = Logger.getLogger("LoggingException");
  public LoggingException(){
    StringWriter trace = new StringWriter();
    printStackTrace(new PrintWriter(trace));
    logger.severe(trace.toString());
  }
}

```

---

## Exception and Logging

```java
public class LoggingExceptions{
  public static void main(String[] args){
    try{
      throw new LoggingException();
    } catch(LoggingException e){
      System.err.println("Caught "+e);
    }
  }
}

```

---

## Classification of Errors

- <font color=red>*Syntax errors*</font> arise because the rules of the language have not been followed. They are detected by the compiler.

- <font color=red>*Runtime errors*</font> occur while the program is running if the environment detects an operation that is impossible to carry out.

- <font color=red>*Logic errors*</font> occur when a program doesn't perform the way it was intended to.

---

## Java Exceptions

![bg 90% fit](images/exp-tree.png)

---

## System Errors

- <font color=red>System errors</font> are thrown by JVM and represented in the <code>Error</code> class. The <code>Error</code> class describes internal system errors. Such errors rarely occur.
- If one does, there is little you can do beyond notifying the user and trying to terminate the program gracefully.

---

## Exceptions

- <font color=red>Exceptions</font> are represented in the <code>Exception</code> class that describes errors caused by your program and external circumstances. These errors can be caught and handled by your program.

---

## Runtime Exceptions

- <font color=red>Runtime exceptions</font> are represented in the <code>RuntimeException</code> class that describes programming errors, such as bad casting, accessing an out-of-bounds array, and numeric errors.

---

## Checked Exceptions vs. Unchecked Exceptions

- <code>RuntimeException</code>, <code>Error</code> and their subclasses are known as <font color=red>unchecked exceptions</font>.
   - In most cases, unchecked exceptions reflect programming logic errors that are not recoverable.
     - For example: <code>NullPointerException</code>, <code>IndexOutOfBoundsException</code>


---

## Checked Exceptions vs. Unchecked Exceptions

- These are the logic errors that should be corrected in the program. Unchecked exceptions can occur anywhere in the program. To avoid cumbersome overuse of try-catch blocks, Java does not mandate you to write code to catch unchecked exceptions.

- All other exceptions are known as <font color=red>checked exceptions</font>, meaning that the compiler forces the programmer to check and deal with the exceptions.


---

## Tips

- 异常处理不能代替简单的测试。

case a:
```java
If (!s.empty()) s.pop();
```
case b:
```java
try { //10 times longer than (a)
   s.pop();
}
catch (EmptyStackException e){}
```

---

## Tips

- 不要过分地细化异常。

case a:

```java
for (i=0 ; i<100; i++) {
  try { n=s.pop(); }
  catch (EmptyStackException s) {...}
  try { out.writeInt(n); }
  catch (IOException e) {...}
}

```

---

## Tips

case b:
```java
try { 
  for (i=0 ; i<100; i++) { n=s.pop(); out.writeInt(n);}
}
catch (IOException e) {...}
catch (EmptyStackException s) {...}
```
<span style="color:red">Which one is better?</span>

---

## Tips

- 充分利用异常层次结构。
  + 不要只抛出<code>RuntimeException</code>异常。应该寻找一个适合的子类或创建自己的异常类。
  + 不要只捕获<code>Throwable</code>异常。
- 不要压制异常。
- 在检测错误时，“苛刻”要比放任更好。
- 不要羞于传递异常。

---

## DbC vs. Exception

- Exceptions are about dealing with things going wrong at runtime.
- DbC is about statically defining the conditions under which code is supposed to operate.
- The two are nicely complementary.
- <font color=red>Unchecked exceptions</font> are “what happens when the contract is broken”.
- <font color=red>Checked exceptions</font> are expected to happen from time to time, so are not contract violations. 

---
<!-- _class: lead -->
## How about assertion?

**To be continued**

---

![bg 50%](images/happy.png)