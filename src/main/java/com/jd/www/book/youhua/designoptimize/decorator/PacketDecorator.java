package com.jd.www.book.youhua.designoptimize.decorator;

/**
 * Created by zhujinpeng on 2017/4/16.
 */
public abstract class PacketDecorator implements IPacketCreator {
     IPacketCreator componet;
     public PacketDecorator(IPacketCreator c){
         componet =c;
     }

}
