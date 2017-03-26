package com.jd.www.base.study.zerocopy;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MmapTest {
    //private static final String fileName="/Users/zhujinpeng/bruce/software/mac-office.zip";
    private static final String fileName="/Users/zhujinpeng/IU-139.1117_zhujinpeng_04.03.2016_10.50.47.zip";

    private static void testIO()throws Exception{
        long start = System.currentTimeMillis();
        FileInputStream in = new FileInputStream(fileName);
        int n = -1;
        while((n=in.read())!=-1){

        }
        System.out.println(System.currentTimeMillis()-start+"ms");
    }


    private static void testBufferIO()throws Exception{
        long start = System.currentTimeMillis();
        FileInputStream in = new FileInputStream(fileName);
        BufferedInputStream bin = new BufferedInputStream(in);
        int n = bin.read(new byte[4096]);
        while(n!=-1){
            n = bin.read(new byte[4096]);
        }
        System.out.println(System.currentTimeMillis()-start+"ms");
    }


    private static void testCommonNIO()throws Exception{
        File file = new File(fileName);
        FileInputStream in = new FileInputStream(file);
        FileChannel channel = in.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int)channel.size());

        long begin = System.currentTimeMillis();
        while (channel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            byteBuffer.clear();
        }
        long end = System.currentTimeMillis();
        System.out.println("time is:" + (end - begin));
    }


    private static void testMmappIO()throws Exception{
        File file = new File(fileName);
        FileInputStream in = new FileInputStream(file);
        FileChannel channel = in.getChannel();
        MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY, 0,
                channel.size());


        int len = (int) file.length();

        byte[] b = new byte[len];

        long begin = System.currentTimeMillis();

//        for (int offset = 0; offset < len; offset += 4096) {
//
//            if (len - offset > 4096) {
//                buff.get(b);
//            } else {
//                buff.get(new byte[len - offset]);
//            }
//        }



        buff.get(b);



        long end = System.currentTimeMillis();
        System.out.println("time is:" + (end - begin));
    }


    public static void main(String[] args) throws Exception{
        //testIO();
        //testBufferIO();
        testCommonNIO();
        testMmappIO();
    }
}
