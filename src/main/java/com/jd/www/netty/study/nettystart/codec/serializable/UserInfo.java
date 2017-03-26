/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jd.www.netty.study.nettystart.codec.serializable;

import org.msgpack.annotation.Message;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author Administrator
 * @date 2014年2月23日
 * @version 1.0
 * 使用java 原生的序列化
 */
@Message
public class UserInfo  implements Serializable{

    /**
     * 默认的序列号
     */
    private static final long serialVersionUID = 1L;

    private String userName;

    private String userID;

    public UserInfo buildUserName(String userName) {
	this.userName = userName;
	return this;
    }

    public UserInfo buildUserID(String userID) {
	this.userID = userID;
	return this;
    }

    /**
     * @return the userName
     */
    public final String getUserName() {
	return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public final void setUserName(String userName) {
	this.userName = userName;
    }

    /**
     * @return the userID
     */
    public final String getUserID() {
	return userID;
    }

    /**
     * @param userID
     *            the userID to set
     */
    public final void setUserID(String userID) {
	this.userID = userID;
    }

    public byte[] codeC()throws Exception{
        //分配1mbuffer 空间
	ByteBuffer buffer = ByteBuffer.allocate(1024);
	byte[] value = this.userName.getBytes();
        for(byte s : value){
            System.out.println(s);
        }
        //大小为几个字节 反序列化 先获得  长度 在从此里得到 string
        System.out.println(value.length);
       //4字节
	buffer.putInt(value.length);

     //9字节
	buffer.put(value);
        byte[] ids = this.getUserID().getBytes();
        buffer.putInt(ids.length);
	buffer.put(ids);
        //使其变为可读
	buffer.flip();
	value = null;
	byte[] result = new byte[buffer.remaining()];
	buffer.get(result);
	return result;
    }

    public byte[] codeC(ByteBuffer buffer) {
	buffer.clear();
	byte[] value = this.userName.getBytes();
	buffer.putInt(value.length);
	buffer.put(value);
	buffer.putInt(this.userID.getBytes().length);
        buffer.put(this.userID.getBytes());
	buffer.flip();
	value = null;
	byte[] result = new byte[buffer.remaining()];
	buffer.get(result);
	return result;
    }
}
