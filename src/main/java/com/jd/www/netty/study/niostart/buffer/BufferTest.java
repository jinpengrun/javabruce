package com.jd.www.netty.study.niostart.buffer;


import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

/**
 * Created by zhujinpeng on 15/12/14.
 * 理解bytebuffer的用法
 */
public class BufferTest {


    public static void mainn(String[] args) {
        System.out.println("----------Test allocate--------");
        System.out.println("before alocate:"
                + Runtime.getRuntime().freeMemory());

        // 如果分配的内存过小，调用Runtime.getRuntime().freeMemory()大小不会变化？
        // 要超过多少内存大小JVM才能感觉到？
        // 分配102m内存 heapbytebuffer
        ByteBuffer buffer = ByteBuffer.allocate(102400000);
        System.out.println("buffer = " + buffer);

        System.out.println("after alocate:"
                + Runtime.getRuntime().freeMemory());

        // 这部分直接用的系统内存，所以对JVM的内存没有影响
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(102400000);
        System.out.println("directBuffer = " + directBuffer);
        System.out.println("after direct alocate:"
                + Runtime.getRuntime().freeMemory());

        System.out.println("----------Test wrap--------");
        byte[] bytes = new byte[32];
        buffer = ByteBuffer.wrap(bytes);
        System.out.println(buffer);

        buffer = ByteBuffer.wrap(bytes, 10, 10);
        System.out.println(buffer);












        System.out.println("--------Test reset----------");
        buffer.clear();
        buffer.position(5);
        buffer.mark();
        buffer.position(10);
        System.out.println("before reset:" + buffer);
        buffer.reset();
        System.out.println("after reset:" + buffer);

        System.out.println("--------Test rewind--------");
        buffer.clear();
        buffer.position(10);
        buffer.limit(15);
        System.out.println("before rewind:" + buffer);
        buffer.rewind();
        System.out.println("before rewind:" + buffer);

        System.out.println("--------Test compact--------");
        buffer.clear();
        buffer.put("abcd".getBytes());
        System.out.println("before compact:" + buffer);
        System.out.println(new String(buffer.array()));
        buffer.flip();
        System.out.println("after flip:" + buffer);
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println("after three gets:" + buffer);
        System.out.println("\t" + new String(buffer.array()));
        buffer.compact();
        System.out.println("after compact:" + buffer);
        System.out.println("\t" + new String(buffer.array()));

        System.out.println("------Test get-------------");
        buffer = ByteBuffer.allocate(32);
        buffer.put((byte) 'a').put((byte) 'b').put((byte) 'c').put((byte) 'd')
                .put((byte) 'e').put((byte) 'f');
        System.out.println("before flip()" + buffer);
        // 转换为读取模式
        buffer.flip();
        System.out.println("before get():" + buffer);
        System.out.println((char) buffer.get());
        System.out.println("after get():" + buffer);
        // get(index)不影响position的值
        System.out.println((char) buffer.get(2));
        System.out.println("after get(index):" + buffer);
        byte[] dst = new byte[10];
        buffer.get(dst, 0, 2);
        System.out.println("after get(dst, 0, 2):" + buffer);
        System.out.println("\t dst:" + new String(dst));
        System.out.println("buffer now is:" + buffer);
        System.out.println("\t" + new String(buffer.array()));

        System.out.println("--------Test put-------");
        ByteBuffer bb = ByteBuffer.allocate(32);
        System.out.println("before put(byte):" + bb);
        System.out.println("after put(byte):" + bb.put((byte) 'z'));
        System.out.println("\t" + bb.put(2, (byte) 'c'));
        // put(2,(byte) 'c')不改变position的位置
        System.out.println("after put(2,(byte) 'c'):" + bb);
        System.out.println("\t" + new String(bb.array()));
        // 这里的buffer是 abcdef[pos=3 lim=6 cap=32]
        bb.put(buffer);
        System.out.println("after put(buffer):" + bb);
        System.out.println("\t" + new String(bb.array()));


    }




    private static void printMarkPositionLimitCapacity(String msg,ByteBuffer buff){
        System.out.println(msg+":mark:"+mark(buff)+",position:"+buff.position()+",limit:"+buff.limit()+",capacity:"+buff.capacity());
    }


    private static int mark(ByteBuffer buffer){
        try {
            Field field = buffer.getClass().getSuperclass().getSuperclass().getDeclaredField("mark");
            field.setAccessible(true);
            return field.getInt(buffer);
        }catch (Exception e){
            e.printStackTrace();

        }
        return -100;
    }







    //测试第一个类  字节数组指针操作
    private static void  testFirst(){
        //
        ByteBuffer byteBuffer = ByteBuffer.allocate(110);
        printMarkPositionLimitCapacity("allocate 110 ",byteBuffer);

        for(int i=0;i<5;i++){
            byteBuffer.put((byte)i);
        }
        //put 5 个数据每次 每次position 都会加1
        printMarkPositionLimitCapacity("put 5 data ",byteBuffer);

        //要进行读操作 调用filp position 表示现在要读的位置， limit表示最大可读的 东西
        byteBuffer.flip();
        printMarkPositionLimitCapacity("flip ",byteBuffer);

        //读完5个之后
        for(int i=0;i<5;i++){
            byteBuffer.get();
        }

        printMarkPositionLimitCapacity("after read 5 ",byteBuffer);


        //恢复到最初状态 将之前的全部清空  重新 写入
        byteBuffer.clear();

        printMarkPositionLimitCapacity("after clear buffer ",byteBuffer);


        //使用数组一次性写入 5 个数据
        byte[] bs = new byte[5];
        for(int i=0;i<5;i++){
            bs[i] = (byte)(i+3);
        }
        byteBuffer.put(bs);

        printMarkPositionLimitCapacity("after put byte[] ",byteBuffer);


        //调用rewind方法 limit 没有改变 还是110   这样可能会出现脏读
        byteBuffer.rewind();
        printMarkPositionLimitCapacity("after rewind ",byteBuffer);

        byte[] data = new byte[9];
        byteBuffer.get(data);
        System.out.print("get new data:");


        //出现脏读  只能读到 第4个数据
        for(int i=0;i<9;i++){
            //System.out.println("the data "+i+" is "+data[i]);
        }

        printMarkPositionLimitCapacity("get new 5 data",byteBuffer);



        //调用mark 设置mark的值  mark=position
        byteBuffer.mark();
        printMarkPositionLimitCapacity("call mark ",byteBuffer);

        //调用reset 设置 position 设置为position 为 mark
        byteBuffer.reset();
        printMarkPositionLimitCapacity("call reset ",byteBuffer);

        //bytebuffer 容量剩余的方法 remaining 和 hasremaining
        boolean isRemain = byteBuffer.hasRemaining(); //判断position是否大于limit
        int remain = byteBuffer.remaining();

        System.out.println("isremain:"+isRemain+",remain:"+remain);
    }



    //内存分配功能解析
    //allocate 和 wrap
    //allocate 和 wrap 区别就是wrap在分配内存的时候就可以传递字节数组，相当于allocate+put操作，但是他有个特点就是，这个字节数据将和
    //bytebuffer相互影响，就是彼此之间的值改变会影响到对方因为wrap源码内部是直接将这个字节数组赋给了hb属性，所有在堆中都是一块内存。而且
    //wrap是堆中分配内存的
    private static void testSecond(){
        System.out.println("-----------test allocate--------");
        System.out.println("before allocate:"+Runtime.getRuntime().freeMemory());

        //分配内存  分配小了 jvm没有感知到
        ByteBuffer buffer =ByteBuffer.allocate(1024*1024);
        System.out.println("buffer = "+buffer);

        System.out.println("after allocate:"+Runtime.getRuntime().freeMemory());

        //bytebuffer 中的内容和数组bytes内容共享的，相互影响
        System.out.println("-----------test wrap-----------");
        byte[] bytes = new byte[32];
        for(int i=0;i<32;i++){
            bytes[i]=(byte)i;
        }

        buffer = ByteBuffer.wrap(bytes);
        printMarkPositionLimitCapacity("wrap:",buffer);
        //
        bytes[10]=-99;

        for(int i=0;i<32;i++){
            System.out.print(buffer.get());
            if(i!=31){
                System.out.print(",");
            }
        }

        System.out.println("");




        buffer = ByteBuffer.wrap(bytes,10,10);

        System.out.println("after set buffer :"+buffer);
    }


    //子buffer
    //bytebuffer中操作子buffer的方法大致四个：slice，duplicate，array，get
    //slice获取源bytebuffer的position-limit 之间的内容，和原内容相互影响
    //duplicate 获取源bytebuffer 所有内容包括mark,position,limit,capacity值，和原内容相互影响
    //array方法获取的是源bytebuffer的所有内容，只是存放到一个字节数组中，和源数据相互影响，
    //get方法获取的是bytebuffer的position-limit 之间的内容，存放到目标字节数组中，和原内容不影响，
    private static void testThird(){
        //分配10大小，然后存入10个数据，可以直接使用wrap方法
        ByteBuffer buff = ByteBuffer.allocate(10);
        for(int i=0;i<10;i++){
            buff.put((byte)i);
        }

        //手动的设置position和limit的值，就是设置bytebuffer中有效数据的范围
        //同时也是为下面slice方法做准备，这个方法就是截取position-limit之间的有效数据返回一个子bytebuffer
        //slice方法调用之后，原来bytebuffer的position，limit不受影响

        buff.position(2);
        buff.limit(4);

        System.out.println("before slice:"+buff);
        ByteBuffer slice = buff.slice();
        System.out.println("the slice buffer is "+slice);

        for(int i=0;i<2;i++){
            //使用get 会更新position
            System.out.print(slice.get()+",");
            //使用get（i）不会更新position
        }

        printMarkPositionLimitCapacity("get data the buff is ",buff);
        printMarkPositionLimitCapacity("slice:",slice);

        //修改子bytebuffer的内容，这时候就会影响到原bytebuffer内容
        //也就是说子buffer和源buffer之间是相互影响的




        //duplicate方法，直接拷贝源bytebuffer的一个副本，不仅把所有的内容拷贝过来，还把mark，position，limitcapacity一并拷贝
        //原来的position和limit 不改变
        //同样的duplicate和源bytebuffer之间内容是相互影响的

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for(int i=0;i<10;i++){
            byteBuffer.put(i,(byte)i);
        }

        //duplicate 和 原buffer 相互影响
        ByteBuffer duplicate = byteBuffer.duplicate();
        System.out.println("after duplicate "+duplicate);
        duplicate.put(2, (byte) -100);
        testPrint(0,10,duplicate);
        byteBuffer.put(2, (byte) 77);
        testPrint(0,10,byteBuffer);


        //array方法
        //array方法直接返回capacity大小的数组，就是bytebuffer所有的内容
        //而不是position-limit之间的有效内容，原来position 和 limit不发生改变
        //内容相互影响
        //影响值 但是没有影响bytebuffer 的position  limit






        System.out.println("-------------------array------------------");
        System.out.println();
        byte[] array = byteBuffer.array();
        System.out.println("array size:"+array.length+",");
        array[2] = -99;

        testPrint(0,10,byteBuffer);


        System.out.println("----------------get----------------");
        System.out.println("the current buffer:"+byteBuffer);

        array = new byte[byteBuffer.remaining()];
        // 影响 position 等 信息
        byteBuffer.get(array);

        array[1]=-88;
        //byteBuffer.position(2);
        printMarkPositionLimitCapacity("get:",byteBuffer);




        //slice方法获取 源 bytebuffer position-limit内容，和原内容相互影响 ，原内容position 和limit 不受影响
        //duplicate 方法获取 元数据 所有内容，包括源bytebuffer的mark ，position，limit，capacity 内容相互影响，元内容不受影响
        //array方法获取元数据bytebuffer所有内容，只是存放到一个字节数组中，和原内容相互影响，元position和limit不受影响
        //get（）获取所有内容，只存放一个字节数组中，和元内容相互影响 ， position 会影响 和 limit一样

    }

    private static void testPrint(int start,int end,ByteBuffer byteBuffer){
        for(int i=start;i<end;i++){
            System.out.print(byteBuffer.get(i)+",");
        }
        System.out.println("");
    }


    private static void printContent(String t,ByteBuffer byteBuffer){
        //byteBuffer.flip();
        System.out.print(t+":");
        while(byteBuffer.hasRemaining()){
            System.out.print(byteBuffer.get() + ",");
        }
        System.out.println("");
    }

    //数据压缩和其他类型之间的转换
    private static void testForth(){
        //压缩
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for(int i=0;i<10;i++){
            //不影响position
            //buffer.put(i,(byte)i);
            //影响position
            buffer.put((byte)i);
        }

        //

        printMarkPositionLimitCapacity("put:",buffer);

        buffer.position(3);
        buffer.limit(buffer.capacity());
        ByteBuffer compact = buffer.compact();
        printMarkPositionLimitCapacity("compact:",compact);
        printContent("compact",compact);
        buffer.clear();
        printContent("buffer",buffer);



        //getint 和 asintbuffer ， order方法 内存数据 高序 和 低序 大端序 和 小端序 java 默认 大端序


        ByteBuffer bint = ByteBuffer.allocate(10);
        for(int i=0;i<10;i++){
            bint.put((byte)i);
        }

        bint.rewind();
        ByteOrder order = bint.order();
        System.out.println("order:"+order);

        //本地jvm运行的硬件的字节顺序，使用和硬件一致的字节顺序可能使buffer更有效
       // bint.order(ByteOrder.nativeOrder());
        //小端序
        System.out.println(ByteOrder.nativeOrder());
        int val = bint.getInt();
        val = bint.getInt();
        //这里会报异常，应为一个integer是4个字节，在调用的话就超过了bytebuffer太小了
        //bint.getInt();
        //bint.getInt();

        System.out.println("val:"+Integer.toHexString(val));

        bint.clear();
        IntBuffer intAry = bint.asIntBuffer();
        System.out.println("int len:"+intAry);

        printContent("bint:",bint);
        intAry.put(10000);
        bint.flip();
        printContent("put 10000",bint);


    }


    public static void main(String[] args) {
       testThird();
    }

}
