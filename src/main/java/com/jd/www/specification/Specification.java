package com.jd.www.specification;

import com.google.common.collect.Lists;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhujinpeng on 2017/4/28.
 */
public class Specification {

    //命名规范 禁用拼音 与  英文 混合方式，更不允许直接中文 方式  下例：不能使用用拼音， 不能使用中文
    private void getPingFenByName() {
        //这个名字是错误的
        int 某变了 = 2;
    }

    //类名中使用UpperCamelCase风格，必须遵从驼峰模式 例外情况 BO TO DTO VO 等情况
    static class UserDO {
        //这是个正确的示范
    }

    //方法名，参数名，成员变量，局部变量 都🙆使用lowerCameCase风格，必须遵从驼峰模式
    int localValue; //这是个正确的示范

    //常量名字全部用大写表示单词间用_隔开，尽量保持语义完整
    static final int MAX_STOCK_COUNT = 3;//正确例子


    //抽象类名使用 Abstract或 Base 开头，异常类命名使用Exception 结尾； 测试类 命名以要测试的类名开始 以test结尾
    //例如  定义一个抽象类
    abstract class AbstractMyTest {

    }

    abstract class BaseMyTest {

    }

    //关于数组定义 定义到中间 例如
    String[] args; //正确
    String args2[];  //; 不建议

    //pojo类中布尔类型 不要加is 比如 isSuccess 这样序列化框架会序列化错误

    boolean ifSuccess;//可以这样定义 不能用 is

    //杜绝完全不规范缩写 例如 AbstractClass 命名成 AbsClass  condition缩写成condi 此类随意严重降低了代码的可阅读性

    //如果使用了设计模式，建议在类名中体现出具体模式 例如
    public class OrderFactory {
        //工厂模式
    }

    public class LoginProxy {
        //代理模式  突出 模式类型 这样 易于阅读 和 体现功能
    }

    public class ResourceObserver {

    }

    //接口类方法和 属性都不要 任何修饰符号（public 不要加）保持代码简洁性，并加上有效的javadoc注释。尽量不要再接口里定义变量，如果一定要定义变量，肯定是与接口方法相关
    //并且是和整个应用的基础常量

    public interface MyInterface {
        String COMPANY = "meituan";

        void f(); //正例

        public abstract void f1();//反例

    }

    //接口和实现类的命名有两套规则
    //强制 对于service 和 DAO类，基于SOA理念暴露出来的一定是接口，内部的实现类用Impl的后缀与接口区别
    public interface CacheService {
        void f();
    }


    public class CacheServiceImpl implements CacheService {
        @Override
        public void f() {
            System.out.println("hello");
        }
    }


    //推荐  形容能力的接口名称，取对应的形容词做接口名 -able 形式
    public interface Translatable {
        int f();
    }

    public abstract class AbstractTranslator implements Translatable {

    }

    public class Translator extends AbstractTranslator {

        @Override
        public int f() {
            return 0;
        }
    }

    //枚举类型建议带上Enum后缀

    public enum PayTypeEnum {
        WEIXIN,
        ZHIFUBAO;
    }

    //各层命名
    // Service/DAO 层方法命名规约
    //获取单个对象方法用get前缀
    <T> T getObject() {
        return null;
    }

    //获取多个对象方法用list 做前缀
    <T> List<T> listObject() {
        return null;
    }

    //统计值方法用count
    <T> T countSomething() {
        return null;
    }

    //插入的方法用save(推荐) 或 insert做前缀
    boolean saveSomething() {
        return Boolean.FALSE;
    }

    //删除方法用remove（推荐）或者delete前缀
    boolean removeSomething() {
        return Boolean.FALSE;
    }

    boolean updateSomething() {
        return false;
    }

    //领域模型 命名规约
    //数据对象 xxxDO  XXX为 数据表名
    public class StudentDO {
    }

    //数据传输对象 xxxDTO ,XXX为业务领域相关的名称
    public class StudentDTO {
    }

    //展示对象：xxxVO ,XXX 一般为网页名称
    public class StudentVO {
    }

    //POJO是DO/DTO/BO/VO的统称，禁止命名成XXXPOJO


    //常量定义
    //强制 不允许出现任何魔法值（ji未经定义的常量）直接出现在代码中
    //反例
    int tradeID = 1;
    String key = "Id#bruce_" + tradeID;

    //强制 long或者 Long 初始赋值时候 必须使用大写的L 不允许使用 小写l 容易和 1混淆
    //反例
    long l = 1l;
    //正例
    long c = 1L;

    //推荐  不要使用一个常量类维护所有常量，应该按常量功能进行分类，分开维护
    //例如  常量归类
    public class ConfigConsts {
    }

    public class CacheConsts {
    }

    //常量复用层次有五层：跨应用共享常量，应用内共享常量，子工程内共享常量，包内共享常量，类内共享常量
    //1 跨应用共享常量：放置二方库，通常是client.jar的constants目录下
    //2 应用内共享常量：放置在一方库的modules的constants
    //3 子工程内部共享常量：ji在当前子工程的constant目录下
    //4 包内共享常量：ji在当前包下单独的constant目录下
    //5 类内共享常量：直接在类内部 private static final 定义


    //推荐 如果变量值仅在一个范围内变化用Enum类，如果还有名称之外的属性，使用Enum类

    enum Day {
        MONDAY(1),
        TUESDAY(2);
        int i;

        Day(int i) {
            this.i = i;
        }
    }

    //格式规约
    //强制 左括号和后一个字符之间不出现空格
    //强制if/for/while/swith/do保留字与左右括号之间都必须加空格
    //任何运算符左右必须加一个空格

    public static void testHello() {
        //缩进4个空格
        String say = "hello";
        //运算符左右必须有一个空格
        int flag = 0;
        //关键词if与括号之间必须有个空格，括号内的f与左括号，0与右括号不需要空格
        if (flag == 0) {
            System.out.println(say);
        }

        //左大括号前加空格且不换行；左大括号后换行
        if (flag == 1) {
            System.out.println("sdfsd");
        } else {
            System.out.println("ok");
        }

    }


    //强制 单行字符限制不超过120个，超出需要换行 ，换行遵循如下规则
    //1 第二行相对第一行缩进4个空格，从第三行开始，不再继续缩进
    //2 运算符与下文一起换行
    //3 方法调用的点符号与下文一起换行
    //4 在多个参数超长，逗号后进行换行
    //5 在括号前不要换行
    //正例
    public void testSb() {
        StringBuffer sb = new StringBuffer();
        //超过120个字符的情况下，换行缩进4个空格，并且方法前的点符号一起换行 正例子
        sb.append("zi").append("xin").append("---")
                .append("huang")
                .append("huang")
                .append("huang");

    }

    //强制 方法参数定义和传入时，多个参数逗号后边biubiu加空格 正解
    public static void testHelo(int i, int c){
        System.out.println(i + "--" + c);
    }



    //强制 ide text file encoding 设置为 utf-8 ；ide中文键的换行符使用unix 不要使用windows 格式



    //oop 规约
    //避免通过类的对象来访问此类的静态变量或静态方法，无谓增加编译器解析成本，直接用类名访问

    // 强制 所有覆盖方法必须加Override
    //相同参数类型，相同业务含义，才可以使用java可变参数，避免使用Object
    //可变参数必须放置在参数列表最后，（提倡尽量不用可变参数）
    public Object listUsers(String type, Integer... ids){return null;}

    //对外暴露的接口签名，原则上不允许修改方法签名，避免对接口调用产生影响。接口过时时候必须加上Deprecated注解，并清晰说明采用的新接口或者新服务是什么
    @Deprecated
    public void testDeprecated() {
        System.out.println("wo过期了");
    }

    //object的equals方法容易抛空指针异常，应使用常量或确定值来调用equals
    public void testEquals(){
        "test".equals(new String());
        //推荐使用java.util.Objects#equals java7引入
        Objects.equals("a","b");
    }

    //所有的相同类型的包装类对象之间值的比较，全部使用equals方法比较
    //对于Integer var = ？在-128 和 127之间赋值，Integer对象是在IntegerCache.cache产生，会复用已有对象，这个区间内的Integer值可以直接使用==进行判断
    //但是这个区间外的数据都会在堆上产生，并不会复用已有对象，推荐使用equals方法进行判断





    public static void testInteger(){
       Integer i = 123;
       Integer c = 123;
        System.out.println("i==c:"+(i==c));
        Integer a = 17788;
        Integer b = 17788;
        System.out.println("a==b:"+(a==b));

        System.out.println(Objects.equals(a,b));
        System.out.println(Objects.equals(i,c));

    }


    //强制 关于基本数据类型与包装数据类型的使用标准如下
    // 1 所有pojo类属性必须使用包装数据类型
    // 2 rpc方法返回值和参数必须使用包装数据类型
    // 3 所有局部变量推荐使用基本数据类型
    // pojo类属性没有初始值是提醒使用者在使用时候必须显示进行赋值，任何npe问题nullpointexception 问题或者入库检查都由使用者来保证

    //定义do/dto/vo时不要设定任何属性默认值
    //强制  序列化新增属性时，不要修改 serialVersionUID字段，避免序列化失败，如果完全不兼容升级，避免反序列化混乱，那么请修改serialVersionUID
    //serialVersionUID不一致会抛出序列化运行时异常

    //强制构造方法里面禁止加入任何业务逻辑，如果有初始化逻辑，请放在init方法中

    public class TestConstract{
        public TestConstract(){
            init();
        }

        void init(){
            System.out.println("test void ");
        }
    }


    //pojo类必须写toString方法，如果继承了另一个POJO类，注意前面加一下super.toString方法

    //使用split方法得到数组时候，需要做最后一个分隔符后有无内容的检查 否则有抛IndexOutOfBoundsException的风险

    public static void testSplit(){
        String str = "a,b,c,,";
        StringTokenizer ary = new StringTokenizer(str,",");

        while(ary.hasMoreTokens()){
            System.out.println(ary.nextToken());
        }

        //如果使用slit不能自认为是4 其实是3 长度

    }

    //如果一个类有多个构造方法，或者同名方法时，应该按顺序放置在一起 便于阅读

    //类内方法顺序依次是 共有方法或保护方法 》私有方法》getter/setter方法

    //setter方法中在getter 和 setter方法中尽量不要增加业务逻辑，增加排查问题难度

    // final 可以提供程序响应效率，声明成final的情况：
    // 不需要重新编译的变量，包括类属性，局部变量
    // 对象参数前加final 表示不允许修改引用的指向。
    // 类方法确定不允许重写

    //慎用object的clone方法来拷贝对象
    // 对象的clone方法默认是浅拷贝，若想实现深拷贝需要重写clone方法来实现属性对象的拷贝



    //类成员和方法访问控制从严
    //1 如果不允许外置直接通过new来创建对象，那么构造方法必须是private
    //2 工具类不允许有public货default 构造方法
    //3 类非static成员变量并且与子类共享，必须是protected
    //4 类非static成员变量并且仅在本类中使用必须是private
    //5 类static成员变量如果仅仅在本类使用必须是private
    //6 类成员方法只供本类内部调用必须是private
    //7 类成员方法只对继承类公开，那么限制为protected




    //集合处理
    //强制 关于hashcode 和 equals处理，遵循如下规则
    //1 只要重写equals 就必须重写hashCode
    //2 因为set存储的是不重复的对象，依据hashCode和equals进行判断，所以set存储的对象必须重写这两个方法
    //3 如果自定义对象作为 map的键，那么必须重写hashCode 和 equals
    // 说明  strng 重写了hashCode和equals方法，所以我们可以非常愉快地使用String对象作为key 来 使用


    //使用集合转数组的方法，必须使用集合的toArray(T[] array)传入的是类型完全一样的数组，大小就是list.size
    //正例反例
    public static void testArray(){
        List<String> strings = Lists.newArrayList("1","2","3","4");
        String[] args = strings.toArray(new String[strings.size()]);

        //传入无参的toArray
        Object[] objects = strings.toArray();
        for(Object object : objects){
            String s = (String)object;
            System.out.println(object);
        }

    }


    //使用工具类Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的add/remove/clear抛出异常，返回list时Arrays的内部类并没有实现集合修改的方法

    //强制 泛型通配符<? extends T> 接收返回的数据，此写法的泛型集合不能使用add方法。 TODO

    //不要再foreach 循环里进行元素的 remove/add操作。remove元素请使用iterator方式，如果并发，需要对iterator加锁

    //反例
    public static void testForeach(){
        List<String> a = Lists.newArrayList("1","3");
        for(String temp : a){

            //FIXME TODO
            if("1".equals(temp)){
                a.remove(temp);//反例
            }
        }
    }

    //强制 jdk7 版本 comparator要满足自反性，传递性，对称性，不然 Arrays.sort collections.sort 会报IllegalArgumentException 异常
    //说明
    //1 自反性： x，y 的比较结果和y，x的比较结果相反
    //2 传递性：x>y,y>z 则x>z.
    //3 对称性：x=y,则x,z比较结果和y，z比较结果相同。

    //反例  下例中没有处理相等情况，实际使用中可能会出现异常。
    public static void testComparator(){
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()>o2.length()?1:-1;
            }
        };

    }

    //推荐 集合初始化时候，尽量指定集合初始值大小 说明 ArrayList 尽量使用ArrayList（size）进行初始化
    //推荐 使用entrySet 遍历map类集合KV，而不是keySet方式进行遍历 说明 keyset其实是遍历了两次，一次是转为Iterator 对象，另一次是从hashMap中取出key所对应的value。
    //entrySet只是遍历了一次就把key和value全部取出来了

    //高度注意 Map 类集合 kv能不能存储null的情况 ， hashtable 和 concurrentHashMap 不允许为null， treemap value可以为null  hashmap kv 都可以为null
    //参考 利用 set 元素唯一的特性，可以快速对一个集合进行去重操作，避免使用List的contains方法进行遍历，对比，去重


    //并发处理
    //1 强制 获取单例对象需要保证线程安全，其中的方法也要保证线程安全  资源驱动类，工具类，单例工厂类都需要注意
    //2 强制 创建线程或线程池的时候 请指定有意义的线程名称，方便出错时回溯
    //例如　
    public class TimerThread extends  Thread{
        public TimerThread(){
            super.setName("TimerTaskThread");
        }
    }

    //强制 线程资源必须通过线程池提供,不允许在应用中自行显示创建线程
    //强制 线程池不允许使用Executors 去创建,而是通过ThreadPoolExecutor 的方式,这样的处理方式让写的同学更加明确线程池的运行规则,规避资源耗尽的风险
    //说明 Executors返回的线程池对象弊端如下:
    //fixedThreadPool 和 singleThreadPool
    //允许的请求队列长度为Integer.MAX_VALUE 可能会堆积大量的请求,从而导致oom
    //cachedthreadPool和scheduledThreadPool
    //允许创建的线程数最大为Integer.MAX_VALUE,可能会创建大量的线程,从而导致oom
    //强制 simpleDateFormat 是线程不安全类,一般不要定义为static 如果定义为static必须加锁,或者使用DateUtils工具类  或者如下处理

    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    //java8 的应用 可以 使用Instant 代替date , LocalDateTime 代替Calendar ,DateTimeFormatter代替SimpledateFormatter

    //强制 高并发时候,同步调用应该去考量锁的性能损耗,能用无锁结构就不用锁,能锁区块就不要锁整个方法体;能用对象锁,就不要用类锁

    //对多个资源,数据库表,对象同时加锁时,需要保持一致的加锁顺序,否则可能会造成死锁.

    //强制 并发修改同一记录时,避免更新丢失,赢么在应用层加锁,要么在缓存加锁,要么在数据库层使用乐观锁,使用version作为更新依据.  说明,如果每次访问冲突小于20%,推荐使用乐观锁,否则使用悲观锁.乐观锁重试次数不得小于3次

    //推荐 使用countDownlatch 进行异步转同步操作,每个线程退出前必须调用countdown方法,线程执行代码注意catch异常,确保countDown方法可以执行,避免主线程无法执行至countDown方法,直到超时才返回结果

    //推荐 避免Random实例被多线程使用,虽然共享该实例是线程安全的,但会因竞争同一seed导致性能下降
    //Random实例包括java.util.Random的实例或者Math.random()实例
    //在jdk7之后,可以直接使用api ThreadLocalRandom,在jdk7之前,可以做到每个线程一个实例

    //延迟初始化的 选择 见

    //参考  valatile解决多线程内存不可见问题,对于一写多读,可以解决同步问题,多写同样无法解决线程安全问题,如果是count++操作,使用
    //AtomicInteger count = new AtomicInteger; count.addAndGet(1);如果是jdk8 使用 LongAdder对象比atomicLong 性能好(减少乐观锁重试次数)


    //hashmap 在容量不够进行resize时由于高并发可能出现死链,导致cpu飙升.怎样规避风险 TODO

    //ThreadLocal 无法解决共享对象的更新问题,ThreadLocal对象建议使用 static 修饰,这个变量是针对一个线程内所有操作共有的,所以设置为静态变量,所有此类实例共享此静态变量;

    //控制语句
    //1 强制  在一个switch块内,每个case要么通过break/return等来终止,要么注释说明程序将继续执行到哪一个case为止,在一个switch块内,都必须包含一个default语句并且放在最后,
    //即使什么代码也没有

    //2 强制 在 if/else/for/while/do 语句中必须使用大括号,即使只有一行代码,避免使用if(condition) statements;
    //3 推荐 推荐尽量少用else if-else的方式可以换成
    //if(condition){return obj;} 如果非得使用if()...else if()....else 方式表达逻辑,强制  请勿超过3层,超过请使用状态设计模式 TODO
    //推荐 除了常用方法isxxx等外,不要再条件判断中执行其它复杂语句,将复杂逻辑判断的结果赋值给一个有意义的布尔变量名.

    //5推荐 循环体重的语句要考量性能,以下操作尽量移至循环外处理,如定义对象,变量,获取数据库连接,进行不必要的try-catch操作(这个try-catch是否可以移到外面)

    //6推荐 接口入参保护,这种场景常见的是用于做批量操作的接口

    //7参考 方法中需要进行参数校验的场景
    //1调用频次低的方法
    //2执行时间开销很大的方法,参数校验时间几乎可以忽略不计,但如果因为参数错误导致中间回退,或者错误,得不偿失.
    //需要极高稳定性和可用性的方法
    //对外提供的开放接口 不管是 rpc/api/http接口



    //8参考 方法中不需要校验的场景
    //1 极有可能被循环调用的方法,不建议对参数进行校验.但在方法说明里必须注明外部参数检查
    //2 底层方法调用频度比较高,一般不校验.毕竟是像纯净水过滤的最后一道,参数错误不太可能到底层才会暴露问题.一般DAO层与service 层都在一个应用中,dao参数校验可以省略
    //3 被声明成private只会被自己代码锁调用的方法,如果能够确定调用方法的代码传入参数已经做过检查或者肯定不会有问题,此时可以不做参数校验





    //注释规约
    //类 类属性  类方法,必须使用javadoc规范 使用/**内容**/ 不可使用 //xxx

    //对于抽象方法,接口 必须使用 javadoc 规范除了返回值,参数,异常说明 还必须指出 该方法做什么事情,实现什么功能

    //所有类必须创建创建者信息

    //方法内部单行注释使用//  多行注释使用/**内容**/

    //每个枚举类型必须有注释 并说明用途



    //任何数据结构初始构造,都应该指定大小,避免数据结构无限增长吃光内存






    //异常处理
    //不要捕获集成java继承自runtimeException 的异常,这类异常由程序员预检查来控制
    //异常不要做流程控制,异常做流程控制成本较高

    //大段代码try catch  是不负责任的表现,代码分为稳定代码和 非稳定代码,稳定代码什么情况下都不会出错,非稳定代码要区分错误类型  来处理


    //防止npe是基本修养,注意npe产生的场景
    //1 返回类型是包装数据类型,有可能是null,返回int时候注意判空
    //public int f(){return Integer 对象}如果为null 拆箱的时候 自动抛npe
    //2 数据库查询结果可能为null
    //3 集合元素即使isNotEmpty,取出的数据元素也可能为null
    //4 远程调用返回对象 一律判断npe
    //5 级联调用obj.getA().getB().getC()容易产生NPE



    //代码中是抛出异常 还是  使用错误码
    //公司外http/api 必须使用 错误码,应用内部使用 抛出异常, 跨rpc 使用 result ,isSucces 错误码

    //异常区分 checked/unchecked异常,避免使用 RuntimeException抛出,更不允许抛出Exception 或  Throwable,使用有业务含义的自定义异常,
    //例如 DAOException/ServiceException

    // 避免出现重复代码



    //日志规约
    //应用中不可直接使用日志系统中的api,应该使用slf4j中的api,使用日志框架有利维护各个类和个各类的日志处理方式统一











    public static void main(String[] args) {
        testArray();
    }
}
