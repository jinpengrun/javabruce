package www;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by zhujinpeng on 16/1/10.
 */
public class Caozuofu {
    public static void main(String[]args) throws Exception{
        System.out.println(SelectionKey.OP_ACCEPT);
        System.out.println(SelectionKey.OP_CONNECT);
        System.out.println(SelectionKey.OP_READ);
        System.out.println(SelectionKey.OP_WRITE);
        System.out.println("1 is 0001");
        System.out.println("4 is 0100");
        System.out.println("8 is 1000");
        System.out.println("16 is 10000");
        //二进制 只要有一个一个为1 则为1
        System.out.println(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        //不同为1 相同为0
        System.out.println(SelectionKey.OP_READ^SelectionKey.OP_WRITE);
        //二进制位都为1 为1  否则结果为0
        System.out.println(SelectionKey.OP_READ&SelectionKey.OP_CONNECT);
        //非运算符 取反
        System.out.println(~2);

        //创建一个选择器
        Selector selector = Selector.open();

        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        SelectionKey key = channel.register(selector, SelectionKey.OP_ACCEPT);



        System.out.println(key.interestOps());
        boolean s = (17&SelectionKey.OP_ACCEPT)==SelectionKey.OP_ACCEPT;
        System.out.println(s);



    }
}
