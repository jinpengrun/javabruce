package com.jd.www.book.algorithm.link;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/6 下午5:04</li>
 * <li>function:</li>
 * </ul>
 *
 * 两个函数，一个删除单链表倒数第k个节点
 *  一个删除双链表 倒数第k个节点
 *
 *  链表长度 n 时间复杂度 为on  空间复杂度 o1
 *
 *
 *  解析
 *  倒数 第k 个  长度为n
 *  分为三种情况
 *  1 从第一个开始减  循环完这个 node k 变为0 说明 倒数k 就是第一个
 *  2 同上                         k 大于0  说明 没有 倒数k 这个node
 *  3 同上                         k < 0  说明 在循环一次 变为+1 直到 k =0 就是 应该弹出的 前一个节点
 */
public class DeleteLinkK {

    public static class NodeDelete{
        public int value;
        public NodeDelete next;

        public NodeDelete(int data){
            this.value = data;
        }

        @Override
        public String toString() {
            return "NodeDelete{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public static NodeDelete removeLastKthNode(NodeDelete nodeDelete,int lastth){
        if(nodeDelete == null || lastth < 1){
            return nodeDelete;
        }
        NodeDelete cur = nodeDelete;


        while(cur != null){
            lastth--;
            cur = cur.next;
        }

        if(lastth == 0){
            nodeDelete = nodeDelete.next;
        }


        if(lastth < 0){
            cur = nodeDelete;
            while(++lastth!=0){
                cur = cur.next;
            }

            cur.next = cur.next.next;

        }

        return nodeDelete;
    }


    /**
     * a/b处的节点的值
     * 例如 链表 1 -- 2 --3 --4 -5
     * a/b 值为r
     * r=0 不删除  0-1/5 删除节点1 * n(n为链表大小)
     * @param head
     * @param a
     * @param b
     * @return
     */
    public static  NodeDelete removeByRatio(NodeDelete head,int a,int b){
        if(a<1||a>b){
            return head;
        }

        int n = 0;
        NodeDelete cur = head;


        //一共有多少个
        while(cur!=null){
            n++;
            cur = cur.next;
        }

        n = (int)Math.ceil(((double)(a*n))/(double)b);

        //为1   删除节点1
        if(n == 1 ){
            head = head.next;
        }




        if(n>1){
            cur = head;
            while(--n!=1){
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }

        return head;

    }


    public static void main(String[] args) {
         NodeDelete nodeDelete = new NodeDelete(1);
         nodeDelete.next = new NodeDelete(2);
        nodeDelete.next.next = new NodeDelete(3);
        //System.out.println(nodeDelete);
        System.out.println(removeLastKthNode(nodeDelete, 2));


        System.out.println("-----------math.ceil--------------");
        System.out.println(Math.ceil(4.5)+"--"+Math.ceil(4.3)+"--"+Math.ceil(4.1)+"--"+Math.ceil(4.9));
    }
}
