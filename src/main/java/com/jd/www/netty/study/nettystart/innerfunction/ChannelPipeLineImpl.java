package com.jd.www.netty.study.nettystart.innerfunction;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by zhujinpeng on 16/9/14.
 */
public class ChannelPipeLineImpl  implements Iterable<String>{

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public String next() {
                return null;
            }
        };
    }


}
