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
package com.jd.www.netty.study.aiostart;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author lilinfeng
 * @date 2014年2月16日
 * @version 1.0
 *
 * 处理请求的 handler
 */
public class AcceptCompletionHandler implements
        //完成handler  异步的 socketchannel
	CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {


    //如果有新客户端接入 就会 回调 该方法， 因为 意味着 拷贝 已经完成
    @Override
    public void completed(AsynchronousSocketChannel result,
	    AsyncTimeServerHandler attachment) {
    //继续接受 新的连接   继续接受新的请求
	attachment.asynchronousServerSocketChannel.accept(attachment, this);
        //分配1m
	ByteBuffer buffer = ByteBuffer.allocate(1024);
        //                          读取操作
	result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
	exc.printStackTrace();


	attachment.latch.countDown();
    }

}
