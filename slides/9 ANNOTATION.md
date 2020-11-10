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

## 注解

---


## 注解 

- 从JavaSE 1.5开始，Java增加了对元数据的支持，也就是Annotation
  - 元数据是关于数据的数据。在编程语言上下文中，元数据是添加到程序元素如方法、字段、类和包上的额外信息，对数据进行说明描述的数据
- Annotation其实就是对代码的一种特殊标记，这些标记可以在编译，类加载和运行时被读取，并执行相应的处理


---


## 基本的Annotation


- `@Override` — 限定重写父类方法
- `@Deprecated` — 标示已过时
- `@SuppressWarning` — 抑制编译器警告


---

## @Override
- 用于告知编译器，需要覆写父类的当前方法
  - 如果某个方法带有该注解但并没有覆写父类相应的方法，则编译器会生成一条错误信息
  - `@Override`可适用元素为方法

试一下 <!-- .element: class="fragment" -->


---

## @Deprecated 

- 使用这个注解，用于告知编译器，某一程序元素(比如方法，成员变量)不建议使用了（即过时了）
- 调用具有该注解的方法时编译器会出现警告，告知该方法已过时。
- `@Deprecated`可适合用于除注解类型声明之外的所有元素

---

## @SuppressWarnings
- 用于告知编译器忽略特定的警告信息
  - 例在泛型中使用原生数据类型，编译器会发出警告，当使用该注解后，则不会发出警告
- 该注解有方法value(）属性,可支持多个字符串参数，用户指定忽略哪种警告
  - `@SupressWarning(value={"uncheck","deprecation"})`


---

## Values

| 参数        | 含义                        |
| :---------- | :-------------------------- |
| deprecation | 使用了过时的类或方法        |
| unchecked   | 执行了未检查的转换          |
| fallthrough | switch块缺少了break         |
| fianlly     | 任意finally子句不能正常完成 |
| ...         | ...                         |
| all         | 以上所有情况                |

[`javac -X`](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javac.html) 

---

## 元Annotation

- 元Annotation就是修饰其他Annotation的Annotation
- Java除了在`java.lang`提供了上述内建注解外，还在`java.lang.annotation`包下提供了6个Meta Annotation(元Annotataion)
  - 其中有5个元Annotation都用于修饰其他的Annotation定义，`@Repeatable`专门用户定义Java 8新增的可重复注解

---

## Annotation Type Override 

``` java
@Target(value=METHOD)
@Retention(value=SOURCE)
public @interface Override {

}
```

<small>https://docs.oracle.com/javase/8/docs/api/java/lang/Override.html</small>

其中`@interface`定义了`Override`是一个Annotation类型，或者叫元数据(meta-data)。 `@Target`和`@Retetion`是对`@Override`的注解，称之为元注解(注解的注解)。


---

## @Target的定义

``` java
package java.lang.annotation;
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Target {
    /**
     * Returns an array of the kinds of elements an annotation type can be applied to.
     * @return an array of the kinds of elements an annotation type can be applied to
     */
    ElementType[] value();
}
```
<small>`Target`也是一个注解类型，在其内部定义了方法`ElementType[] value();`返回值就是`@Target(ElementType.METHOD)`中的`ElementType.METHOD`，也就是注解的属性，是一个`ElementType`枚举。</small>

---

## ElementType
<small>这个枚举其实就是定义了注解的适用范围，在`Override`注解中，`@Target`的属性是`ElementType.METHOD`,所以`Override`这个注解只能用于注解方法。</small>

```java
package java.lang.annotation;
public enum ElementType {
    /** Class, interface (including annotation type), or enum declaration */
    TYPE,
    /** Field declaration (includes enum constants) */
    FIELD,
    /** Method declaration */
    METHOD,
    /** Formal parameter declaration */
    PARAMETER,
    /** Constructor declaration */
    CONSTRUCTOR,
    /** Local variable declaration */
    LOCAL_VARIABLE,
    /** Annotation type declaration */
    ANNOTATION_TYPE,
    /** Package declaration */
    PACKAGE,
    /**
     * Type parameter declaration
     *
     * @since 1.8
     */
    TYPE_PARAMETER,
    /**
     * Use of a type
     *
     * @since 1.8
     */
    TYPE_USE
}
```

---

## 再看@Target

```java
package java.lang.annotation;
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Target {
    /**
     * Returns an array of the kinds of elements an annotation type can be applied to.
     * @return an array of the kinds of elements an annotation type can be applied to
     */
    ElementType[] value();
}
```
<small>而`Target`注解本身也有一个`@Target`元注解，这个`@Target`元注解属性是`ElementType.ANNOTATION_TYPE`，也就是说`Target`注解只能用作元数据(注解)的注解，所以叫它元注解。</small>

<small>`@Retention(RetentionPolicy.RUNTIME)`? `@Documented`?</small>

---
## @Retention
``` java
package java.lang.annotation;
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Retention {
    /**
     * Returns the retention policy.
     * @return the retention policy
     */
    RetentionPolicy value();
}
```
---
## RetentionPolicy


``` java
public enum RetentionPolicy {
    /**
     * Annotations are to be discarded by the compiler.
     */
    SOURCE,
    /**
     * Annotations are to be recorded in the class file by the compiler but need not be retained by the VM at run time.  This is the default behavior.
     */
    CLASS,
    /**
     * Annotations are to be recorded in the class file by the compiler and retained by the VM at run time, so they may be read  reflectively.
     */
    RUNTIME
}
```
<small>`@Retention`注解则定义了注解的保留范围，如:在源代码、CLASS文件或运行时保留。超出`@Retention`定义的属性，注解将被丢弃。</small>

---

## @Documented


``` java
package java.lang.annotation;
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Documented {
}

```
<small>如果一个注解定义了`@Ducumented`，在[javadoc](http://www.oracle.com/technetwork/articles/java/index-137868.html)生成API文档时，被这个注解标记的元素在文档上也会出现该注解。</small>

---

## 例

``` java
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface InWork {
    String value();
}

/**
 * Annotated class.
 */
@InWork(value = "")
public class MainApp {...}
```


![bg right:40% 80%](https://i.stack.imgur.com/QLm1F.png)

---

## @Inherited

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Inherited {
}

```
<small>只对`@Target`为`ElementType.TYPE`(类、接口、枚举)有效，并且只支持类元素。使用了`@Inherited`的注解修饰在一个class上，可以保证继承它的子类也拥有同样的注解。</small>

---

## 自定义注解

使用`@interface`可以定义一个注解

``` java
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorAnno{
    //以下无参方法实际上定义的是注解的属性
    //其返回值可以是所有基本类型、String、Class、enum、Annotation或以上类型的数组
    String name();
    //default关键字可以定义该属性的默认值，如果没有指定默认值在使用注解时必须显示指定属性值
    String website() default "hello";
    int revision() default 1;
}

```

---

## 使用自定义注解

``` java
public class AnnotationDemo {
    @AuthorAnno(name="lvr", website="hello", revision=1)
    public static void main(String[] args) {
        System.out.println("I am main method");
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    @AuthorAnno(name="lvr", website="hello", revision=2)
    public void demo(){
        System.out.println("I am demo method");
    }
}
```

---

## 注解解析

```java
public class AnnotationParser {
    public static void main(String[] args) throws SecurityException, ClassNotFoundException {
        Method[]  demoMethod = AnnotationDemo.class.getMethods();
        
        for (Method method : demoMethod) {

            if (method.isAnnotationPresent(AuthorAnno.class)) {
                 AuthorAnno annotationInfo = method.getAnnotation(AuthorAnno.class);
                 System.out.println("method: "+ method);
                 System.out.println("name= "+ annotationInfo.name() +
                         " , website= "+ annotationInfo.website()
                        + " , revision= "+annotationInfo.revision());
            }
        }
    }
}
```

---

![bg 50%](images/happy.png)
