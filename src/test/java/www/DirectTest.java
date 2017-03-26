package www;


import sun.misc.Unsafe;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujinpeng on 15/12/10.
 *
 */
public class DirectTest {
    /**
     * 测试DirectMemory和Heap读写速度。
     */

    public void testDirectMemoryWriteAndReadSpeed() {

                                        //分配直接内存 返回 DirectByteBuffer
        ByteBuffer buffer = ByteBuffer.allocateDirect(400);

        List<Long> direct = new ArrayList<Long>();
        //测试100次
        for(int i=0;i<100;i++) {
            long tsStart = System.currentTimeMillis();
            for (int x = 0; x < 100000; x++) {
                for (int j = 0; j < 100; j++) {
                    buffer.putInt(j);
                }
                buffer.flip();
                for (byte j = 0; j < 100; j++) {
                    buffer.getInt();
                }
                buffer.clear();
            }
            direct.add(System.currentTimeMillis() - tsStart);
        }
                            //分配堆内存 返回 HeapByteBuffer
        buffer = ByteBuffer.allocate(400);
        List<Long> heap = new ArrayList<Long>();
        for(int x=0;x<100;x++){


        long tsStart = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 100; j++) {
                buffer.putInt(j);
            }
            buffer.flip();
            for (byte j = 0; j < 100; j++) {
                buffer.getInt();
            }
            buffer.clear();
        }
         heap.add(System.currentTimeMillis()-tsStart);
        }
        long heaplong = 0 ;
        long direclong = 0 ;
        for(Long x : heap){
           heaplong+=x;
        }
        for(Long x:direct){
            direclong+=x;
        }
        System.out.println("heap time:"+heaplong/heap.size());
        System.out.println("direc time:"+direclong/direct.size());
    }

    /**
     * 测试DirectMemory和Heap内存申请速度。
     * 分配好堆外内存 读得比较多
     */

    public void testDirectMemoryAllocate() {
        long tsStart = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(400);

        }
        System.out.println("DirectMemory申请内存耗用： " + (System.currentTimeMillis() - tsStart) + " ms");
        tsStart = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            ByteBuffer buffer = ByteBuffer.allocate(400);
        }
        System.out.println("Heap申请内存耗用： " + (System.currentTimeMillis() - tsStart) + " ms");
    }


    public static void main(String[]args){

        new DirectTest().testDirectMemoryWriteAndReadSpeed();
    }
}
