类加载器的前世今生
来源：类加载器是java语言的一个创新，也是java语言流行的重要原因，使得java类可以被动态加载到java虚拟机中并执行。当时是为了满足java applet 的需要开发
     java applet 需要从远程下载java类文件到浏览器并执行。
现在：类加载器在web容器和 osgi中得到了广泛使用。

一般：java应用开发人员不需要同类加载器进行交互，java虚拟机默认的行为就已经满足需求，如果遇到直接同类加载器进行交互，而对类加载器机制不了解，就得花大量
     时间调试classnotfoundexception 和 noclassdeffounderror 等异常。



java源文件--》java字节码 .class文件---》类加载负责读取java字节码 生成 Class 类的一个对象

ClassLoader   与加载类相关方法
getParent()	返回该类加载器的父类加载器。
loadClass(String name)	加载名称为 name的类，返回的结果是 java.lang.Class类的实例。
findClass(String name)	查找名称为 name的类，返回的结果是 java.lang.Class类的实例。
findLoadedClass(String name)	查找名称为 name的已经被加载过的类，返回的结果是 java.lang.Class类的实例。
defineClass(String name, byte[] b, int off, int len)	把字节数组 b中的内容转换成 Java 类，返回的结果是 java.lang.Class类的实例。这个方法被声明为 final的。
resolveClass(Class<?> c)	链接指定的 Java 类。

表示类名称的 name参数的值是类的二进制名称。需要注意的是内部类的表示，如 com.example.Sample$1和 com.example.Sample$Inner等表示方式。


类加载器的树状组织结构
类加载器两类 系统提供  和  用户编写
引导类加载器（bootstrap class loader）：它用来加载 Java 的核心库，是用原生代码来实现的，并不继承自 java.lang.ClassLoader。
扩展类加载器（extensions class loader）：它用来加载 Java 的扩展库。Java 虚拟机的实现会提供一个扩展库目录。
                                        该类加载器在此目录里面查找并加载 Java 类。
系统类加载器（system class loader）：它根据 Java 应用的类路径（CLASSPATH）来加载 Java 类。一般来说，Java 应用的类都是由它来完成加载的。
                                        可以通过 ClassLoader.getSystemClassLoader()来获取它。

除了系统提供的类加载器以外，开发人员可以通过继承 java.lang.ClassLoader类的方式实现自己的类加载器，以满足一些特殊的需求。


