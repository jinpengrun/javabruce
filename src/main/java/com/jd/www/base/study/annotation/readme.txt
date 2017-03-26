java注解的学习
元注解的作用就是负责注解其他注解。
Java5.0定义了4个标准的meta-annotation类型，
它们被用来提供对其它 annotation类型作说明。
Java5.0定义的元注解：
　　　　1.@Target, 说明修饰范围
　　　　2.@Retention, 说明注解保存时间长短， 源文件，class文件， 运行时
　　　　3.@Documented,@Documented用于描述其它类型的annotation应该被作为被标注的程序成员的公共API
　　　　4.@Inherited 标记注解 是被继承的 ， 如果使用inherited 的 注解类型被用于一个class，则该annotation将被用于该class 的子类



注解 需要注解处理器
