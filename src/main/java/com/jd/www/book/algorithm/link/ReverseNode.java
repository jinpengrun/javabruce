package com.jd.www.book.algorithm.link;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/7 下午2:21</li>
 * <li>function:</li>
 * </ul>
 *
 * 分别实现反转单向链表和反转双向链表的函数
 * 如果链表长度为N 时间复杂度要求为oN ， 额外空间复杂度要求为o1
 */
public class ReverseNode {

    public static class NodeReverse{
        private int value;
        private NodeReverse next;

        public NodeReverse(int value){
            this.value = value;
        }

        @Override
        public String toString() {
            return "NodeReverse{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public static class DoubleNode{
        private int value;
        private DoubleNode next;
        private DoubleNode pre;

        public DoubleNode(int value){
            this.value = value;
        }


        @Override
        public String toString() {
            return "DoubleNode{" +
                    "value=" + value +
                    ", next=" + next +
                    ", pre=" + pre +
                    '}';
        }
    }




    //单向表反转
    public static NodeReverse reverseList(NodeReverse nodeReverse){
        //空间复杂度 为 o1
        NodeReverse pre = null;
        NodeReverse next = null;
        while(nodeReverse != null){
            next = nodeReverse.next;
            nodeReverse.next=pre;
            pre=nodeReverse;
            nodeReverse = next;
        }
        return pre;
    }


    //双向链表反转
    public static DoubleNode reverseList(DoubleNode nodeReverse){
        //空间复杂度 为 o1
        DoubleNode pre = null;
        DoubleNode next = null;
        while(nodeReverse != null){
            next = nodeReverse.next;
            nodeReverse.next = pre;
            nodeReverse.pre = next;
            pre = nodeReverse;
            nodeReverse = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        NodeReverse nodeReverse = new NodeReverse(12);
        nodeReverse.next = new NodeReverse(13);
        nodeReverse.next.next = new NodeReverse(14);

        System.out.println(reverseList(nodeReverse));
    }




}
