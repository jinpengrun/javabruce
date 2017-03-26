package com.jd.www.netty.study.javaserial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * Created by zhujinpeng on 16/1/15.
 */
public class UserInfo implements Serializable {
    private String userName;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public byte[] codeC(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.userName.getBytes();
        //buffer.putInt(value.length);
        buffer.put(value);

        buffer.putInt(this.userId);
        buffer.flip();
        value = null;

        byte[] result = new byte[buffer.remaining()];

        buffer.get(result);

        return result;
    }



    public static void main(String[]args)throws Exception{
        UserInfo info = new UserInfo();
        info.setUserId(100);
        info.setUserName("welcome to Netty");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);

        //jdk序列化大小
        os.writeObject(info);
        os.flush();;
        os.close();;
        byte[] b = bos.toByteArray();
        System.out.println("the jdk serializable legth is :"+b.length);


        bos.close();;
        //二进制序列化大小
        System.out.println("--------------------");
        System.out.println("the byte array serializable length is :"+info.codeC().length);
    }
}
