重复注解
 在同一个地方不能多次使用同一个注解。java 8 打破了这个限制，引入了重复注解的概念，允许在同一个地方多次使用同一个注解。
 使用@Repeatable 注解定义重复注解
比如
@Schedule(dayOfMonth="last")
@Schedule(dayOfWeek="Fri", hour="23")
public void doPeriodicCleanup() { ... }

例如
@Alert(role="Manager")
@Alert(role="Administrator")
public class UnauthorizedAccessException extends SecurityException { ... }


第一步 声明一个 可重复的


反射api 中有几种方法用于检索注释，
1 AnnotatedElement.getAnnotationByType(Class<T>)
2 AnnotatedElement.getAnnotations(Class<T>)

设计考虑