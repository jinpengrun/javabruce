package com.jd.www.google.guava;

import com.google.common.base.*;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.cache.*;
import com.google.common.collect.*;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhujinpeng on 16/1/20.
 */
public class GuavaTest {


    public static void main(String[]args)throws Exception{
        //stringTest();
        //objectOptions();
        //preconditions("",33,"dfd");
        //optional();
        //doIt();
        //immutable();
        //multiSetTest();
        //biMapTest();
        //listmultiMap();
        //updateIterator();
        //testCache();
        //cachu();
        //guavaInvoke();
        guavaDic();
    }


    private static void stringTest(){
        System.out.println(Strings.isNullOrEmpty("----"));
        String a = "com.jd.sdflasd";
        String b = "com.jd.adslkjfasd";
        System.out.println(Strings.commonPrefix(a,b));


        //buquan
        int minleng = 2;
        String padEndResult = Strings.padEnd("123",minleng,'2');
        System.out.println(padEndResult);

                                                                                //忽略字符串
        Iterable<String> splitResults = Splitter.on("[,，]{1,}").trimResults().omitEmptyStrings().split("hello,word，世界，水平");

        for(String item:splitResults){
            System.out.println("spliter:"+item);
        }


        //二次拆分
          String toSplitString = "a=b;c=d,e=f";
          Map<String,String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator('=').split(toSplitString);
         for (Map.Entry<String,String> entry : kvs.entrySet()) {
             System.out.println(String.format("%s=%s", entry.getKey(),entry.getValue()));
              }

        //合并
        String joinResult = Joiner.on("00-0").join(new String[]{"sdfasdf","asdfasdf"});
        System.out.println(joinResult);


        //合并map里
          Map<String,String> map = new HashMap<String,String>();
          map.put("a", "b");
         map.put("c","d");
         String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(kvs);
          System.out.println(mapJoinResult);


    }




    private static void objectOptions(){
        Object a = null;
        Object b = new Object();
        //先比较 是否 引用是否 相等 ， 再比较 equals 方法
        boolean aEqualsB = Objects.equal(a, b);

        Student student = new Student();
        student.setAge(12);
        student.setId(32);
        //student.setName("helloworld");
        System.out.println(student.toString());

    }

    static class Student{
        private int id;
        private String name;
        private int age;

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }

        public String toString() {

           return MoreObjects.toStringHelper(Student.class).add("id",id)
                    .add("name",name)
                    .add("age",age).toString();
        }
    }


    private static void preconditions(String name,int age,String desc){
        Preconditions.checkNotNull(name, "name may not be null");
        Preconditions.checkArgument(age >= 18 && age < 99, "age must in range (18,99)");
        Preconditions.checkArgument(desc !=null && desc.length() < 10, "desc too long, max length is ", 10);

        System.out.println("33333333333");
    }



    private static void optional(){
        Optional<Student> possibleNull = Optional.of(null);
        possibleNull.get();
    }


    public static void doSometing() throws Throwable{
        throw new NullPointerException();
    }

    public static void doSomethingElse() throws Exception{
        System.out.println("dfasf");
    }



    public static void doIt()  throws SQLException{
        try {
            doSometing();
        } catch (Throwable throwable) {
            //如果是 sql 异常抛出  如果是 sql 异常 自己抛出 否则
            Throwables.propagateIfInstanceOf(throwable,SQLException.class);
            //如果是Error异常抛出
            //Throwables.propagateIfPossible(throwable);
        }
    }


    private static void immutable(){
        Set<String> immutableNamedColors = ImmutableSet.<String>builder()
                .add("red", "green","black","white","grey")
                .build();
        //immutableNamedColors.add("abc");
        for (String color : immutableNamedColors) {
            System.out.println(color);
        }
        //分配不可变
        Set<String> ss = ImmutableSet.of("1","2","3");

        for(String color : ss){
            System.out.println(color);
        }

        List<String> s1 = ImmutableSet.copyOf(new String[]{"11","22","33","44","44"}).asList();

        for(String color : s1){
            System.out.println(color);
        }
    }




    private static void multiSetTest(){

        //collection  +  count 功能
        Multiset multiset = HashMultiset.create();
        String sentences = "this is a story, there is a good girl in the story.";
        Iterable<String> words = Splitter.onPattern("[^a-z]{1,}").omitEmptyStrings().trimResults().split(sentences);
        for (String word : words) {
            multiset.add(word);
        }

        for (Object element : multiset.elementSet()) {
            System.out.println((String)element + ":" + multiset.count(element));
        }
    }





    /**双向map**/
    private static void biMapTest(){
        BiMap<String,String> weekNameMap = HashBiMap.create();
        weekNameMap.put("星期一","Monday");
        weekNameMap.put("星期二","Tuesday");
        weekNameMap.put("星期三","Wednesday");
        weekNameMap.put("星期四","Thursday");
        weekNameMap.put("星期五","Friday");
        weekNameMap.put("星期六","Saturday");
        weekNameMap.put("星期日","Sunday");

        System.out.println("星期日的英文名是" + weekNameMap.get("星期日"));
        System.out.println("Sunday的中文是" + weekNameMap.inverse().get("Sunday"));
    }



    /**Map<String,Collection<String>>，guava中的Multimap就是为了解决这类问题的。**/

    private static void listmultiMap(){
         Multimap<String,String> multimap = ArrayListMultimap.create();
        multimap.put("1","wodeshijie");
        multimap.put("1","wangbadan");
        multimap.put("1","gundan");
        multimap.put("2","wanglinying");
        multimap.put("3","zhujinpeng");
        System.out.println(multimap.size());

        System.out.println(multimap.get("1"));

        //打印所有
        for(String ss : multimap.values()){
            System.out.println(ss);
        }

        multimap.removeAll("3");
        System.out.println("-------------------------------");

        //打印所有
        for(String ss : multimap.values()){
            System.out.println(ss);
        }



    }



    /**更新迭代器**/

    private static void updateIterator(){
        List<String> list = Lists.newArrayList();
        list.add("nihao");
        list.add("wohao");
        list.add("tahao");
        Predicate<String> condition = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
             return   input.startsWith("n");
            }
        };

        boolean all =  Iterators.all(list.iterator(),condition);
       // System.out.println(all);



        //查找里面 包含 的
        Iterator<String> it = Iterators.filter(list.iterator(), new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.startsWith("dd");
            }
        });

        while(it.hasNext()){
            System.out.println(it.next());
        }

        //find 符合的第一个元素
        String length5Element = Iterators.find(list.iterator(), new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.length() == 5;
            }
        });

        System.out.println(length5Element);

        //transform方法 对迭代器做转换
        Iterator<Integer> countIterator = Iterators.transform(list.iterator(), new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return input.length();
            }
        });

         while(countIterator.hasNext()){
            System.out.println(countIterator.next());
         }
    }



    /**guava的cache**/
    private static void testCache()throws Exception{
        //缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
        LoadingCache<Integer,Student> studentCache
                //CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
                = CacheBuilder.newBuilder()
                //设置并发级别为8，并发级别是指可以同时写缓存的线程数 同时写缓存的 线程数
                .concurrencyLevel(8)
                        //设置写缓存后8秒钟过期
                .expireAfterWrite(8, TimeUnit.SECONDS)
                        //设置缓存容器的初始容量为10
                .initialCapacity(10)
                        //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(100)
                        //设置要统计缓存的命中率
                .recordStats()
                        //设置缓存的移除通知
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                })
                        //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build(
                        new CacheLoader<Integer, Student>() {
                            @Override
                            public Student load(Integer key) throws Exception {
                                System.out.println("load student " + key);
                                Student student = new Student();
                                student.setId(key);
                                student.setName("name " + key);
                                return student;
                            }
                        }
                );

        for (int i=0;i<20;i++) {
            //从缓存中得到数据，由于我们没有设置过缓存，所以需要通过CacheLoader加载缓存数据
            Student student = studentCache.get(1);
            System.out.println(student);
            //休眠1秒
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("cache stats:");
        //最后打印缓存的命中率等 情况
        System.out.println(studentCache.stats().toString());
    }



    //泛型运行时擦除
    public static void cachu(){
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println("intList type is " + intList.getClass());
        System.out.println("stringList type is " + stringList.getClass());
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass())); //true

        resolveCachu();
    }

    private static void resolveCachu(){
        TypeToken<ArrayList<String>> typeToken = new TypeToken<ArrayList<String>>() {};
        TypeToken<?> genericTypeToken = typeToken.resolveType(ArrayList.class.getTypeParameters()[0]);
        System.out.println(genericTypeToken.getType());
    }




    //guava 反射之invokable使用
    private static void guavaInvoke()throws Exception{
        Method method = GuavaTest.class.getMethod("cachu");
        //返回本类 声明的所有 方法
        Method[] methods = GuavaTest.class.getDeclaredMethods();

        for(Method method1:methods){
            System.out.println(method1);
        }
        System.out.println(Modifier.isPublic(method.getModifiers()));

        //guava
        System.out.println("guava:" + Invokable.from(method).isPublic());

        System.out.println("guava:"+Invokable.from(method).isPackagePrivate());
        //是否被子类 重写
        System.out.println("guava:"+Invokable.from(method).isOverridable());

        //查看注解 jdk 查看第一个参数是否被定义了@Nullable
        //method.getParameterAnnotations() 获取参数的annotations
        //获取第一个参数 看看 有没有 这个注解
        //System.out.println(Invokable.from(method).getParameters().get(0).isAnnotationPresent(Nullable.class));



        //invokable.isPublic();
        //invokable.getParameters();
        //invokable.invoke(object, args);


    }


    /**invoke 的 动态代理**/

    private static void guavaDic(){

        //原生  基于接口
        IFoo foo = new IFooImple();

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(foo);


        IFoo io = (IFoo)java.lang.reflect.Proxy.newProxyInstance(GuavaTest.class.getClassLoader()
                , new Class[]{IFoo.class}, myInvocationHandler);

        io.get("sldkjflkasdjflkasdf");


        // guava的生成策略
       IFoo ifo =  Reflection.newProxy(IFoo.class,myInvocationHandler);

        ifo.get("base on guava");


    }



    public static class MyInvocationHandler<T> implements  InvocationHandler{

        private T t;

        public MyInvocationHandler(T t){
            this.t = t;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method!=null&&t!=null){
               return method.invoke(t,args) ;
            }
            return null;
        }
    }




}
