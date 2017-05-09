package com.jd.www.book.youhua.designoptimize.decorator;

/**
 * Created by zhujinpeng on 2017/4/16.
 */
public class PacketHttpHeaderCreator extends PacketDecorator {
    public PacketHttpHeaderCreator(IPacketCreator iPacketCreator){
        super(iPacketCreator);
    }
    @Override
    public String handleContent() {
        StringBuffer sb = new StringBuffer();
        sb.append("cache-control:no-cache\n");
        sb.append("date:mon,31.....");
        sb.append(componet.handleContent());
        return sb.toString();
    }

    public static void main(String[] args) {
        IPacketCreator iPacketCreator = new PacketHttpHeaderCreator(new PacketHtmlHeaderCreator(new PacketBodyCreator()));
        System.out.println(iPacketCreator.handleContent());
    }
}
