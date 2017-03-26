package com.jd.www.book.algorithm.link;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/6 下午6:48</li>
 * <li>function:</li>
 * </ul>
 * 给定链表头结点，删除链表中间节点的函数
 */
public class DeleteMiddleAB {



    public static class NodeDM{
        public int value;
        public NodeDM next;

        public NodeDM(int value){
            this.value = value;
        }

        @Override
        public String toString() {
            return "NodeDM{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }
}
