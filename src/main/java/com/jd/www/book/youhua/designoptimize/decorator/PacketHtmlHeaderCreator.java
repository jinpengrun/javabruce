package com.jd.www.book.youhua.designoptimize.decorator;


/**
 * Created by zhujinpeng on 2017/4/16.
 */
public class PacketHtmlHeaderCreator extends PacketDecorator{

    public PacketHtmlHeaderCreator(IPacketCreator iPacketCreator){
        super(iPacketCreator);
    }

    @Override
    public String handleContent() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<body>");
        sb.append(componet.handleContent());
        sb.append("</body>");
        sb.append("</html>\n");
        return sb.toString();
    }
}
