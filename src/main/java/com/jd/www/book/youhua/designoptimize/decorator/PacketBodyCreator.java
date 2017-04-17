package com.jd.www.book.youhua.designoptimize.decorator;

/**
 * Created by zhujinpeng on 2017/4/16.
 */
public class PacketBodyCreator implements IPacketCreator {
    @Override
    public String handleContent() {
        return "content of packet"; //构造核心数据，但不包括格式

    }
}
