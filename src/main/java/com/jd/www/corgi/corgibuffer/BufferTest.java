package com.jd.www.corgi.corgibuffer;

import java.nio.ByteBuffer;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/1/17 下午4:48</li>
 * <li>function:</li>
 * </ul>
 */
public class BufferTest {


    private static void replaceTest(int position,ByteBuffer byteBuffer,int length){
        //预分配100字节 buffer
        ByteBuffer bf = ByteBuffer.allocate(100);

        System.out.println("写入前 bf："+bf);
        //首先写入10个字节
        for(int i=0;i<10;i++){
           bf.put((byte)i);
        }

        System.out.println("写入10个字节后 bf："+bf);

        //切片 取 position - limit
        ByteBuffer bfb = bf.slice();

        System.out.println("slice bfb："+bfb);
        System.out.println("slice bf："+bf);

        bfb.put(byteBuffer.array(),0,length);

        System.out.println("bfb 写入后："+bfb);





    }


    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        for(int i=0;i<10;i++){
            byteBuffer.put((byte)i);
        }



        replaceTest(0,byteBuffer,5);
    }
}
